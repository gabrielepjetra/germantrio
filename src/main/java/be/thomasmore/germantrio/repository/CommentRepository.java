package be.thomasmore.germantrio.repository;

import be.thomasmore.germantrio.model.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByCarModelIdOrderByCreatedAtDesc(Long carModelId);

    @EntityGraph(attributePaths = {"appUser", "replies", "replies.appUser"})
    List<Comment> findByCarModelIdAndParentCommentIsNullOrderByCreatedAtDesc(Long carModelId);
}
