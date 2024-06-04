package reConstructor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reConstructor.domain.entities.Tables;

import java.util.Optional;
import java.util.Set;

public interface TablesRepository extends JpaRepository<Tables, Integer> {
    @Query(value = "SELECT number FROM tables where restaurant_id=:id ORDER BY number DESC LIMIT 1",
            nativeQuery = true)
    Optional<String> lastNumber(@Param("id") long restaurantId);

    @Query(value = "SELECT * FROM tables where restaurant_id=:id and is_active=true;", nativeQuery = true)
    Set<Tables> findAllActive(@Param("id") long id);

    @Query(value = "SELECT * FROM tables where restaurant_id=:id and is_active=true and is_free=true;",
            nativeQuery = true)
    Set<Tables> findAllFree(@Param("id") long id);

    @Query(value = "SELECT * FROM tables WHERE id = :id AND is_active = TRUE", nativeQuery = true)
    Optional<Tables> findActiveById(@Param("id") int id);

    @Query(value = "SELECT * FROM tables WHERE id = :id", nativeQuery = true)
    Optional<Tables> findById(@Param("id") int id);

    @Query(value = "SELECT * FROM tables WHERE number = :number AND is_active = TRUE", nativeQuery = true)
    Optional<Tables> findActiveByNumber(@Param("number") String number);
}
