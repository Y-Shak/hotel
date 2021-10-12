package fr.formation.repository;

import fr.formation.entities.HotelEntity;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<HotelEntity,Integer> {
    Iterable<HotelEntity> findByNomContainsOrVilleContains(String search, String search1);

    Iterable<HotelEntity> findByNomContainingIgnoreCaseOrVilleContainingIgnoreCaseOrAdresseContainingIgnoreCase(String search1, String search2,String search3);
}
