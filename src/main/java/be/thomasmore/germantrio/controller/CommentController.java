package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.model.Comment;
import be.thomasmore.germantrio.model.Notification;
import be.thomasmore.germantrio.repository.AppUserRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import be.thomasmore.germantrio.repository.CommentRepository;
import be.thomasmore.germantrio.repository.NotificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class CommentController {

    private final CommentRepository commentRepository;
    private final CarModelRepository carModelRepository;
    private final AppUserRepository appUserRepository;
    private final NotificationRepository notificationRepository;

    public CommentController(CommentRepository commentRepository,
                             CarModelRepository carModelRepository,
                             AppUserRepository appUserRepository,
                             NotificationRepository notificationRepository) {
        this.commentRepository = commentRepository;
        this.carModelRepository = carModelRepository;
        this.appUserRepository = appUserRepository;
        this.notificationRepository = notificationRepository;
    }

    @PostMapping("/carmodels/{carId}/comments")
    public String addComment(@PathVariable long carId,
                             @RequestParam String content,
                             Principal principal) {
        if (principal == null || principal.getName() == null) {
            return "redirect:/login";
        }

        if (content == null || content.isBlank()) {
            return "redirect:/carmodels/" + carId;
        }

        Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());
        Optional<CarModel> carModel = carModelRepository.findById(carId);

        if (appUser.isEmpty()) {
            return "redirect:/login";
        }

        if (carModel.isEmpty()) {
            return "redirect:/";
        }

        Comment comment = new Comment();
        comment.setContent(content.trim());
        comment.setAppUser(appUser.get());
        comment.setCarModel(carModel.get());
        comment.setCreatedAt(LocalDateTime.now());

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

        if (content == null || content.isBlank()) {
            return "redirect:/carmodels/" + carId;
        }

        Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());
        Optional<CarModel> carModel = carModelRepository.findById(carId);
        Optional<Comment> parentComment = commentRepository.findById(commentId);

        if (appUser.isEmpty()) {
            return "redirect:/login";
        }

        if (carModel.isEmpty() || parentComment.isEmpty()) {
            return "redirect:/";
        }

        if (parentComment.get().getCarModel().getId() != carId) {
            return "redirect:/carmodels/" + carId;
        }

        Comment reply = new Comment();
        reply.setContent(content.trim());
        reply.setAppUser(appUser.get());
        reply.setCarModel(carModel.get());
        reply.setParentComment(parentComment.get());
        reply.setCreatedAt(LocalDateTime.now());

        commentRepository.save(reply);

        AppUser replyAuthor = appUser.get();
        AppUser parentAuthor = parentComment.get().getAppUser();

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
}
