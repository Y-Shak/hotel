package fr.formation.repository;

import fr.formation.entities.HotelEntity;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<HotelEntity,Integer> {
}
