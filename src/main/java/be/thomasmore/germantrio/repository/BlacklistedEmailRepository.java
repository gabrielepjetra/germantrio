package be.thomasmore.germantrio.repository;

import be.thomasmore.germantrio.model.BlacklistedEmail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BlacklistedEmailRepository extends CrudRepository<BlacklistedEmail, Long> {
    boolean existsByEmailIgnoreCase(String email);
    List<BlacklistedEmail> findAllByOrderByCreatedAtDesc();
    Optional<BlacklistedEmail> findByEmailIgnoreCase(String email);
}
