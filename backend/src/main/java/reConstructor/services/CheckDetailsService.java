package reConstructor.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import reConstructor.domain.entities.CheckDetails;
import reConstructor.repositories.CheckDetailsRepository;

@Service
public class CheckDetailsService {
    private CheckDetailsRepository repository;

    public CheckDetailsService(CheckDetailsRepository repository) {
        this.repository = repository;
    }

    public CheckDetails save(CheckDetails checkDetails) {
        return repository.save(checkDetails);
    }

    public CheckDetails update(CheckDetails checkDetails) {
        if (repository.existsById(checkDetails.getId())) {
            return repository.save(checkDetails);
        } else {
            throw new EntityNotFoundException("Check details with id " + checkDetails.getId() + " is not found");
        }
    }

    public void delete(Long checkDetailsId) {
        if (repository.existsById(checkDetailsId)) {
            repository.deleteById(checkDetailsId);
        } else {
            throw new EntityNotFoundException("Check details with id " + checkDetailsId + " is not found");
        }
    }

    public void deleteByCheckId(Long checkId) {
        repository.deleteByCheckId(checkId);
    }
}
