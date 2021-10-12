package fr.formation.services;

import fr.formation.check.Check;
import fr.formation.check.CheckAlreadyResaExist;
import fr.formation.check.CheckDateSupNow;
import fr.formation.check.CheckDatefinSupAuDatedeb;
import fr.formation.entities.ResaEntity;
import fr.formation.repository.ResaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResaService {
    @Autowired
    private ResaRepository resaRepository;

    public Iterable<ResaEntity> getList(String search){

        if( search == null || search.length() == 0 ){
            return resaRepository.findAll();
        }else{
            return resaRepository.findByClientId(Integer.parseInt(search));
        }
    }
    public ResaEntity find(int id){
        return resaRepository.findById(id).get();
    }
    @Transactional
    public ResaEntity add(ResaEntity r) throws Exception {
        checkResa(r);
        return resaRepository.save(r);
    }
    private void checkResa(ResaEntity r) throws Exception {
        List<Check> checks = new ArrayList<>();
        checks.add(new CheckDatefinSupAuDatedeb(r.getDatedeb(),r.getDatefin()));
        checks.add(new CheckAlreadyResaExist(r,resaRepository));
        checks.add(new CheckDateSupNow(r.getDatedeb()));
        checks.add(new CheckDateSupNow(r.getDatefin()));
        for (Check check : checks) {
            check.execute();
        }
    }
    @Transactional
    public ResaEntity edit(ResaEntity resa) throws Exception {
        checkResa(resa);
        ResaEntity r = resaRepository.findById(resa.getId()).get();
        r.setNumChambre(resa.getNumChambre());
        r.setDatedeb(resa.getDatedeb());
        r.setDatefin(resa.getDatefin());
        r.setClient(resa.getClient());
        r.setHotel(resa.getHotel());
        return resaRepository.save(r);
    }
    @Transactional
    public void delete(int id ){
        resaRepository.deleteById(id);
    }
}
