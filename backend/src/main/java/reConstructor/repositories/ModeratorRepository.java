package reConstructor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reConstructor.domain.entities.Moderator;

import java.util.Optional;

public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    @Query(value = "SELECT * FROM moderators WHERE id = :id AND is_active = TRUE", nativeQuery = true)
    Optional<Moderator> findActiveById(@Param("id") long id);

    @Query(value = "SELECT * FROM moderators WHERE is_active = TRUE and email = :email", nativeQuery = true)
    Optional<Moderator> findByEmail(@Param("email") String email);

    boolean existsByEmail(String email);
}
