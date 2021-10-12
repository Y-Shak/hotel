package fr.formation.api;

import fr.formation.entities.ClientEntity;
import fr.formation.entities.ResaEntity;
import fr.formation.entities.ResaEntity;
import fr.formation.services.ResaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/api/resa")
public class ResaAPIController {
    @Autowired
    ResaService resaService;
    
    @GetMapping(path = "", produces = "application/json")
    Iterable<ResaEntity> getAllResaApi(HttpServletRequest request){

        return resaService.getList(  request.getParameter("client"));
    }
    @GetMapping(path = "/{id}", produces = "application/json")
    ResaEntity getResaByIdApi(@PathVariable(name = "id") int id){
        return resaService.find(id);
    }
    @PostMapping(path = "", produces = "application/json")
    ResponseEntity<ResaEntity> addResaApi(@RequestBody ResaEntity resa){
        try{
            ResaEntity createResa = resaService.add(resa);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createResa.getId())
                    .toUri();
            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createResa);
        }catch ( Exception e ){
            System.out.println(e);
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<ResaEntity> updateResaApi(@PathVariable(name = "id") int id, @RequestBody  ResaEntity resa){

        try {
            ResaEntity editResa  = resaService.edit(resa);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(editResa.getId())
                    .toUri();
            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(editResa);
        }catch ( Exception e ){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }
    @DeleteMapping(path = "/{id}")
    void deleteResaApi(@PathVariable(name = "id") int id) {
        resaService.delete(id);
    }
}
