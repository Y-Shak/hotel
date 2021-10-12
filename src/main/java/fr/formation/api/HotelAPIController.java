package fr.formation.api;

import fr.formation.entities.HotelEntity;
import fr.formation.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/hotel")
public class HotelAPIController {
    @Autowired
    HotelService hotelService;

    @GetMapping(path = "", produces = "application/json")
    Iterable<HotelEntity> getAllHotelApi(HttpServletRequest request){
        return hotelService.getList(request.getParameter("search"));
    }
    @GetMapping(path = "/{id}", produces = "application/json")
    HotelEntity getHotelByIdApi(@PathVariable(name = "id") int id){
        return hotelService.find(id);
    }
    @PostMapping(path = "", produces = "application/json")
    ResponseEntity<HotelEntity> addHotelApi(@RequestBody HotelEntity hotel){
        try{
            HotelEntity createHotel = hotelService.add(hotel);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createHotel.getId())
                    .toUri();
            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createHotel);
        }catch ( Exception e ){
            e.printStackTrace();
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<HotelEntity> updateHotelApi(@PathVariable(name = "id") int id, @RequestBody  HotelEntity hotel){
//        return hotelService.edit(hotel);
        try{
            HotelEntity editHotel = hotelService.edit(hotel);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(editHotel.getId())
                    .toUri();
            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(editHotel);
        }catch ( Exception e ){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }
    @DeleteMapping(path = "/{id}")
    void deleteHotelApi(@PathVariable(name = "id") int id) {
        hotelService.delete(id);
    }
}
