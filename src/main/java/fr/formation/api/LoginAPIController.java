package fr.formation.api;

import fr.formation.entities.AdminEntity;
import fr.formation.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/login")
public class LoginAPIController {
    @Autowired
    AdminRepository adminRepository;
    @PostMapping(path = "", produces = "application/json")
    ResponseEntity<AdminEntity> checkLogin(@RequestBody AdminEntity userv ){
        System.out.println( userv.getUsername() );

        try{
            AdminEntity user = adminRepository.findByUsername( userv.getUsername() );
            user.setPassword("");
            return ResponseEntity.ok() // ok => 200 
                    .body(user);

        }catch ( Exception e ){
            System.out.println("Je suis ici");
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() ); // KO : 400 
        }

    }


}
