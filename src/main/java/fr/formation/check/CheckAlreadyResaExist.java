package fr.formation.check;

import fr.formation.entities.ResaEntity;
import fr.formation.repository.ResaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CheckAlreadyResaExist implements Check{

    ResaRepository resaRepository;
    ResaEntity resa;

    public CheckAlreadyResaExist(ResaEntity resa,ResaRepository resaRepository) {
        this.resa = resa;
        this.resaRepository = resaRepository;
    }

    @Override
    public void execute() throws Exception {
        Iterable<ResaEntity> list = resaRepository.findByNumChambreAndHotel(resa.getNumChambre(),resa.getHotel());
        LocalDate datdeb = resa.getDatedeb();
        LocalDate datfin = resa.getDatefin();
        for(ResaEntity r : list){
//            if(datdeb.isAfter(r.getDatedeb()) && datdeb.isBefore(r.getDatefin())){
//                throw new Exception("cette chambre est reservée pour cette date de debut");
//            }
//            if(datfin.isAfter(r.getDatedeb()) && datfin.isBefore(r.getDatefin())){
//                throw new Exception("cette chambre est reservée pour cette date de fin");
//            }
            if(isDateBetweenOrEqualToOneOfThem(datdeb,r.getDatedeb(),r.getDatefin())){
                throw new Exception("cette chambre est reservée pour cette date de debut");
            }
            if(isDateBetweenOrEqualToOneOfThem(datfin,r.getDatedeb(),r.getDatefin())){
                throw new Exception("cette chambre est reservée pour cette date de fin");
            }
            // ici pour calculer si il y a une reservation dans l'interval choisi ( qu'on veut enregistrer)
//
//            if(r.getDatedeb().isEqual(datdeb) || r.getDatefin().isEqual(datfin)){
//                throw new Exception("Cette chambre est deja reservé qlq jours durant cet intervalle");
//            }
//
            if(r.getDatedeb().isAfter(datdeb) && r.getDatefin().isBefore(datfin)){
                throw new Exception("Cette chambre est deja reservé qlq jours durant cet intervalle");
            }
        }
    }

    private Boolean isDateBetweenOrEqualToOneOfThem(LocalDate d, LocalDate dDeb, LocalDate dFin ){
        if(d.isEqual(dDeb)){return true;}
        if(d.isEqual(dDeb)){return true;}
        if(d.isAfter(dDeb) && d.isBefore(dFin)){return true;}
        return false;
    }
}
