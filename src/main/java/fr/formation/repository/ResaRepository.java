package fr.formation.repository;

import fr.formation.entities.HotelEntity;
import fr.formation.entities.ResaEntity;
import org.springframework.data.repository.CrudRepository;

public interface ResaRepository extends CrudRepository<ResaEntity, Integer> {
    public Iterable<ResaEntity> findByNumChambreAndHotel(int numChambre, HotelEntity hotel);



    Iterable<ResaEntity> findByClientId(int parseInt);
}
