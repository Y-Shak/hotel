package fr.formation.services;

import fr.formation.entities.AdminEntity;
import fr.formation.entities.HotelEntity;
import fr.formation.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public AdminEntity find(int id){
        return adminRepository.findById(id).get();
    }

    public AdminEntity findByUsername(String username){
        return adminRepository.findByUsername(username);
    }
}
