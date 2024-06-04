package reConstructor.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reConstructor.domain.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "SELECT * FROM restaurants WHERE id = :id AND is_active = TRUE", nativeQuery = true)
    Optional<Restaurant> findActiveById(@Param("id") long id);

    @Query(value = "SELECT * FROM restaurants WHERE is_active = TRUE and moderator_id = :id", nativeQuery = true)
    Set<Restaurant> findAllActive(@Param("id") long moderatorId);

    @Query(value = "SELECT code FROM restaurants;", nativeQuery = true)
    List<String> getCodes();
}
