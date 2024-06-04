package reConstructor.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reConstructor.domain.entities.menu.MenuSection;

import java.util.List;
import java.util.Optional;

public interface MenuSectionRepository extends JpaRepository<MenuSection, Integer> {
    boolean existsByName(String name);

    Optional<MenuSection> findByName(String name);

    @Query(value = "SELECT * FROM menu_sections where is_active = true and parent_section_id = 0 and name = :name",
            nativeQuery = true)
    MenuSection existByNameMainMenuSection(@Param("name") String name);

    @Query(value = "SELECT * FROM menu_sections where is_active = true and parent_section_id = :id and name = :name",
            nativeQuery = true)
    MenuSection findByNameNestedMenuSection(@Param("name") String name, @Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `restaurant_menusection` (`restaurant_id`, `menusection_id`) VALUES (" +
            ":restaurant_id, :menusection_id);", nativeQuery = true)
    void addSectionToRestaurant(@Param("restaurant_id") long restaurantId, @Param("menusection_id") int sectionId);
    @Query(value = "SELECT ms.id, ms.name, ms.is_active, ms.parent_section_id FROM menu_sections as ms join restaurant_menusection as rm on ms.id=" +
            "rm.menusection_id where rm.restaurant_id=:restaurant_id and ms.is_active=true;", nativeQuery = true)
    List<MenuSection> getMainMenuSection(@Param("restaurant_id") long restaurantId);

    @Query(value = "SELECT * FROM menu_sections where parent_section_id=:parent_section_id and is_active=true;",
            nativeQuery = true)
    List<MenuSection> getNestedMenuSection(@Param("parent_section_id") int parentSectionId);

    @Query(value = "WITH RECURSIVE MenuHierarchy AS (SELECT ms.id AS section_id, ms.parent_section_id, ms.name " +
            "AS section_name FROM menu_sections ms WHERE ms.parent_section_id = 0 and ms.restaurant_id =:id UNION ALL " +
            "SELECT ms.id AS section_id, ms.parent_section_id, ms.name AS section_name FROM menu_sections ms " +
            "INNER JOIN MenuHierarchy mh ON ms.parent_section_id = mh.section_id) SELECT mh.section_id, " +
            "mh.parent_section_id, mh.section_name, d.id AS dish_id, d.name AS dish_name, d.description, d.weight, " +
            "d.price, d.image_url FROM MenuHierarchy mh LEFT JOIN dish_section ds ON mh.section_id = ds.section_id " +
            "LEFT JOIN dishes d ON ds.dish_id = d.id;", nativeQuery = true)
    List<Object[]> getMenu(@Param("id") long restaurantId);
}
