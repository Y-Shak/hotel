package fr.formation.services;

import fr.formation.check.*;
import fr.formation.entities.HotelEntity;
import fr.formation.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    public Iterable<HotelEntity> getList(){
        return hotelRepository.findAll();
    }
    public HotelEntity find(int id){
        return hotelRepository.findById(id).get();
    }
    @Transactional
    public HotelEntity add(HotelEntity hotel) throws Exception {
        checkHotel(hotel);
        return hotelRepository.save(hotel);
    }

    private void checkHotel(HotelEntity hotel) throws Exception {
        List<Check> checks = new ArrayList<>();
        checks.add(new CheckEmailValid(hotel.getEmail()));
        checks.add(new CheckTelephoneValid(hotel.getTelephone()));
        checks.add(new CheckNamesValid(hotel.getNom()));
        checks.add(new CheckAdresseValid(hotel.getAdresse()));
        checks.add(new CheckEtoilesHotelValid(hotel.getEtoiles()));
        for (Check check : checks) {
            check.execute();
        }
    }
    @Transactional
    public HotelEntity edit(HotelEntity h) throws Exception {
        checkHotel(h);
        HotelEntity hotel = hotelRepository.findById(h.getId()).get();
        hotel.setAdresse(h.getAdresse());
        hotel.setEmail(h.getEmail());
        hotel.setEtoiles(h.getEtoiles());
        hotel.setNom(h.getNom());
        hotel.setTelephone(h.getTelephone());
        hotel.setVille(h.getVille());
        return hotelRepository.save(hotel);
    }
    @Transactional
    public void delete(int id ){
        hotelRepository.deleteById(id);
    }
}
