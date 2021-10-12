package fr.formation.api;

import fr.formation.entities.ClientEntity;
import fr.formation.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/client")
public class ClientAPIController {
    @Autowired
    ClientService clientService;

    @GetMapping(path = "", produces = "application/json")
    Iterable<ClientEntity> getAllClientApi(HttpServletRequest request){
        return clientService.getList(request.getParameter("search"));
    }
    @GetMapping(path = "/{id}", produces = "application/json")
    ClientEntity getClientByIdApi(@PathVariable(name = "id") int id){
        return clientService.find(id);
    }
    @PostMapping(path = "", produces = "application/json")
    ResponseEntity<ClientEntity> addClientApi(@RequestBody ClientEntity client){
        try{
            ClientEntity createClient = clientService.add(client);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createClient.getId())
                    .toUri();
            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createClient);
        }catch ( Exception e ){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<ClientEntity> updateClientApi(@PathVariable(name = "id") int id, @RequestBody  ClientEntity client){
        try {
            ClientEntity editClient  = clientService.edit(client);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(editClient.getId())
                    .toUri();
            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(editClient);
        }catch ( Exception e ){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }

    }
    @DeleteMapping(path = "/{id}")
    void deleteClientApi(@PathVariable(name = "id") int id) {
        clientService.delete(id);
    }
}
