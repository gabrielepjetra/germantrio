package be.thomasmore.germantrio.repository;

import be.thomasmore.germantrio.model.Notification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    @EntityGraph(attributePaths = {"relatedCarModel"})
    List<Notification> findByRecipientEmailOrderByCreatedAtDesc(String email);

    long countByRecipientEmailAndReadFalse(String email);

    List<Notification> findTop50ByRelatedCarModelIsNullOrderByCreatedAtDesc();
}
