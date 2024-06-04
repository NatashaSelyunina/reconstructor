package reConstructor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reConstructor.domain.entities.CheckDetails;

public interface CheckDetailsRepository extends JpaRepository<CheckDetails, Long> {
    void deleteByCheckId(Long checkId);
}
