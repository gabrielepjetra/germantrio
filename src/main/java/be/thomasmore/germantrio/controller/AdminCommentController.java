package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.Comment;
import be.thomasmore.germantrio.repository.CommentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminCommentController {

    private final CommentRepository commentRepository;

    public AdminCommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/admin/comments")
    public String comments(Model model) {
        model.addAttribute("comments", commentRepository.findAllByOrderByCreatedAtDesc());
        return "admin-comments";
    }

    @Transactional
    @PostMapping("/admin/comments/{id}/delete")
    public String deleteComment(@PathVariable long id) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isEmpty()) {
            return "redirect:/admin/comments?error";
        }

        deleteCommentAndReplies(comment.get());
        return "redirect:/admin/comments?deleted";
    }

    private void deleteCommentAndReplies(Comment comment) {
        List<Comment> replies = commentRepository.findByParentCommentId(comment.getId());
        for (Comment reply : replies) {
            deleteCommentAndReplies(reply);
        }

        commentRepository.delete(comment);
    }
}
