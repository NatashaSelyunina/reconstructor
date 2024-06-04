package reConstructor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reConstructor.domain.entities.Check;

public interface CheckRepository extends JpaRepository<Check, Long> {

    void deleteById(Long checkId);
}
