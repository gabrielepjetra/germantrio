package be.thomasmore.germantrio.repository;

import be.thomasmore.germantrio.model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    List<AppUser> findAllByOrderByCreatedAtDesc();
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
