package fr.formation.repository;

import fr.formation.entities.AdminEntity;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
    public AdminEntity findByUsername(String username);
}
