package reConstructor.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reConstructor.domain.entities.menu.Dish;

import java.util.Optional;
import java.util.Set;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query(value = "SELECT * FROM dishes WHERE id = :id AND is_active = TRUE", nativeQuery = true)
    Optional<Dish> findActiveById(@Param("id") int id);

    boolean existsByName(String name);

    Dish findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `dish_section` (`dish_id`, `section_id`) VALUES (:dish_id, :section_id);",
            nativeQuery = true)
    void addDishToSection(@Param("dish_id") int dishId, @Param("section_id") int sectionId);

    @Query(value = "SELECT COUNT(*) FROM dish_section WHERE dish_id = :dish_id AND section_id = :section_id;",
            nativeQuery = true)
    int existRelationshipDishAndSection(@Param("dish_id") int dishId, @Param("section_id") int sectionId);

    @Query(value = "SELECT d.id, d.name, d.description, d.weight, d.price, d.is_active, d.image_url FROM dishes as d join dish_section " +
            "as ds on d.id=ds.dish_id where section_id=:section_id and is_active=true;", nativeQuery = true)
    Set<Dish> getDishesByMenuSection(@Param("section_id") int sectionId);
}
