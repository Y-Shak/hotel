package fr.formation;

import fr.formation.entities.AdminEntity;
import fr.formation.entities.ClientEntity;
import fr.formation.entities.HotelEntity;
import fr.formation.entities.ResaEntity;
import fr.formation.repository.AdminRepository;
import fr.formation.repository.ClientRepository;
import fr.formation.repository.HotelRepository;
import fr.formation.repository.ResaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootApplication
public class HotelApplication implements CommandLineRunner {





    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
//        HotelEntity hotel = new HotelEntity(0,"Ibis",4,"5 rue de la liberté","ibis-nantes@contact.fr","097655788","Nantes");
//        hotel = hotelRepository.save(hotel);
//        AdminEntity user = new AdminEntity(0,"admin","1234","ROLE_ADMIN");
//        user = adminRepository.save(user);
//        ClientEntity client =new ClientEntity(0,"Amine Seth","098765332","seth@m2i.fr","Rue de la liberté Nantes ");
//        client = clientRepository.save(client);

//        ClientEntity client = clientRepository.findById(1).get();
//        HotelEntity hotel = hotelRepository.findById(1).get();
//        ResaEntity resa = new ResaEntity(0,client,hotel,null,null,1);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // registry.addMapping("/**").allowedOrigins("http://localhost:4200"); autorise l'accès depuis localhost:4200
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
                // * dans allow origins : çad autoriser toutes les machines
            }
        };
    }
}
