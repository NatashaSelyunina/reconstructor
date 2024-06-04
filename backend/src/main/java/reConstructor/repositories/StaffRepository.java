package reConstructor.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reConstructor.domain.entities.Staff;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    boolean existsByCode(String code);

    @Query(value = "SELECT * FROM staff WHERE restaurant_id = :id ", nativeQuery = true)
    List<Staff> findAllByRestaurantId(@Param("id") long restaurantId);

    @Query(value = "SELECT * FROM staff WHERE code = :code AND is_working = TRUE", nativeQuery = true)
    Optional<Staff> findByCode(@Param("code") String code);
}
