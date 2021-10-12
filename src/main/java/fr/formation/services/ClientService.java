package fr.formation.services;

import fr.formation.check.*;
import fr.formation.entities.ClientEntity;
import fr.formation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @Autowired
    private ClientRepository clientRepository;
    public Iterable<ClientEntity> getList(String search, String tri){
        if(tri != null && tri.length() > 0 && tri.equalsIgnoreCase("Desc")){
            System.out.println("je suis en desc ");
            return clientRepository.findAllByOrderByNomCompletDesc();
        }
        if(tri != null && tri.length() > 0 && tri.equalsIgnoreCase("Asc")){
            return clientRepository.findAllByOrderByNomCompletAsc();
        }
        if( search == null || search.length() == 0 ){
            return clientRepository.findAll();
        }else{
            return clientRepository.findByNomCompletContainingIgnoreCaseOrTelephoneContainingIgnoreCaseOrAdresseContainingIgnoreCase(search,search,search);
        }
    }
    public ClientEntity find(int id){
        return clientRepository.findById(id).get();
    }
    @Transactional
    public ClientEntity add(ClientEntity c) throws Exception {
        checkClient(c);
        return clientRepository.save(c);
    }

    private void checkClient(ClientEntity c) throws Exception {
        if(c.getNomComplet().length() < 2 ){
            throw new Exception("Invalid value pour nom");
        }

        List<Check> checks = new ArrayList<>();
        checks.add(new CheckEmailValid(c.getEmail()));
        checks.add(new CheckTelephoneValid(c.getTelephone()));
        checks.add(new CheckNamesValid(c.getNomComplet()));
        checks.add(new CheckAdresseValid(c.getAdresse()));
        for (Check check : checks) {
            check.execute();
        }

    }
    @Transactional
    public ClientEntity edit(ClientEntity client) throws Exception {
        checkClient(client);
        ClientEntity c = clientRepository.findById(client.getId()).get();
        c.setEmail(client.getEmail());
        c.setAdresse(client.getAdresse());
        c.setTelephone(client.getTelephone());
        c.setNomComplet(client.getNomComplet());
        return clientRepository.save(c);
    }
    @Transactional
    public void delete(int id ){
        clientRepository.deleteById(id);
    }
}
