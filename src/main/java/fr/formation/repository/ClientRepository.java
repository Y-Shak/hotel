package fr.formation.repository;

import fr.formation.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    Iterable<ClientEntity> findByNomCompletContainingIgnoreCaseOrTelephoneContainingIgnoreCaseOrAdresseContainingIgnoreCase(String search, String search1, String search2);

    Iterable<ClientEntity> findAllByOrderByNomCompletDesc();

    Iterable<ClientEntity> findAllByOrderByNomCompletAsc();
}
