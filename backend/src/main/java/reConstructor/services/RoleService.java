package reConstructor.services;

import org.springframework.stereotype.Service;
import reConstructor.domain.entities.Role;
import reConstructor.repositories.RoleRepository;

@Service
public class RoleService {
    private RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role findByName(String name){
        return repository.findByName(name);
    }
}
