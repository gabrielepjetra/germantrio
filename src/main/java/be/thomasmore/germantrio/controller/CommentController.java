package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.model.Comment;
import be.thomasmore.germantrio.model.Notification;
import be.thomasmore.germantrio.repository.AppUserRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import be.thomasmore.germantrio.repository.CommentRepository;
import be.thomasmore.germantrio.repository.NotificationRepository;
import jakarta.validation.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class CommentController {

    private static final int MAX_CONTENT_LENGTH = 1000;

    private final CommentRepository commentRepository;
    private final CarModelRepository carModelRepository;
    private final AppUserRepository appUserRepository;
    private final NotificationRepository notificationRepository;
    private final Validator validator;

    public CommentController(CommentRepository commentRepository,
                             CarModelRepository carModelRepository,
                             AppUserRepository appUserRepository,
                             NotificationRepository notificationRepository,
                             Validator validator) {
        this.commentRepository = commentRepository;
        this.carModelRepository = carModelRepository;
        this.appUserRepository = appUserRepository;
        this.notificationRepository = notificationRepository;
        this.validator = validator;
    }

    @PostMapping("/carmodels/{carId}/comments")
    public String addComment(@PathVariable long carId,
                             @RequestParam String content,
                             Principal principal) {
        if (principal == null || principal.getName() == null) {
            return "redirect:/login";
        }

        if (isInvalidContent(content)) {
            return "redirect:/carmodels/" + carId;
        }

        Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());
        Optional<CarModel> carModel = carModelRepository.findById(carId);

        if (appUser.isEmpty()) {
            return "redirect:/login";
        }

        AppUser user = appUser.get();
        if (isCurrentlyMuted(user)) {
            return "redirect:/carmodels/" + carId + "?muted";
        }
        clearExpiredMute(user);

        if (carModel.isEmpty()) {
            return "redirect:/";
        }

        Comment comment = new Comment();
        comment.setContent(content.trim());
        comment.setAppUser(user);
        comment.setCarModel(carModel.get());
        comment.setCreatedAt(LocalDateTime.now());

        if (!validator.validate(comment).isEmpty()) {
            return "redirect:/carmodels/" + carId;
        }

        commentRepository.save(comment);

        return "redirect:/carmodels/" + carId;
    }

    @PostMapping("/carmodels/{carId}/comments/{commentId}/replies")
    public String addReply(@PathVariable long carId,
                           @PathVariable long commentId,
                           @RequestParam String content,
                           Principal principal) {
        if (principal == null || principal.getName() == null) {
            return "redirect:/login";
        }

        if (isInvalidContent(content)) {
            return "redirect:/carmodels/" + carId;
        }

        Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());
        Optional<CarModel> carModel = carModelRepository.findById(carId);
        Optional<Comment> answeredComment = commentRepository.findById(commentId);

        if (appUser.isEmpty()) {
            return "redirect:/login";
        }

        AppUser user = appUser.get();
        if (isCurrentlyMuted(user)) {
            return "redirect:/carmodels/" + carId + "?muted";
        }
        clearExpiredMute(user);

        if (carModel.isEmpty() || answeredComment.isEmpty()) {
            return "redirect:/";
        }

        Comment directlyAnsweredComment = answeredComment.get();
        if (directlyAnsweredComment.getCarModel().getId() != carId) {
            return "redirect:/carmodels/" + carId;
        }

        Comment topLevelParent = findTopLevelParent(directlyAnsweredComment);
        Comment reply = new Comment();
        reply.setContent(content.trim());
        reply.setAppUser(user);
        reply.setCarModel(carModel.get());
        reply.setParentComment(topLevelParent);
        reply.setCreatedAt(LocalDateTime.now());

        if (!validator.validate(reply).isEmpty()) {
            return "redirect:/carmodels/" + carId;
        }

        commentRepository.save(reply);

        AppUser replyAuthor = user;
        AppUser parentAuthor = directlyAnsweredComment.getAppUser();

        if (replyAuthor.getId() != parentAuthor.getId()) {
            Notification notification = new Notification();
            notification.setMessage(replyAuthor.getUsername() + " replied to your comment on " + carModel.get().getName() + ".");
            notification.setRecipient(parentAuthor);
            notification.setRelatedCarModel(carModel.get());
            notification.setCreatedAt(LocalDateTime.now());
            notification.setRead(false);
            notificationRepository.save(notification);
        }

        return "redirect:/carmodels/" + carId;
    }

    private boolean isInvalidContent(String content) {
        return content == null || content.isBlank() || content.trim().length() > MAX_CONTENT_LENGTH;
    }

    private Comment findTopLevelParent(Comment comment) {
        Comment currentComment = comment;
        while (currentComment.getParentComment() != null) {
            currentComment = currentComment.getParentComment();
        }
        return currentComment;
    }

    private boolean isCurrentlyMuted(AppUser appUser) {
        return appUser.getMutedUntil() != null && appUser.getMutedUntil().isAfter(LocalDateTime.now());
    }

    private void clearExpiredMute(AppUser appUser) {
        if (appUser.getMutedUntil() != null && !appUser.getMutedUntil().isAfter(LocalDateTime.now())) {
            appUser.setMutedUntil(null);
            appUser.setMuteReason(null);
            appUserRepository.save(appUser);
        }
    }
}
