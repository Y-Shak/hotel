package fr.formation.security;

import fr.formation.entities.AdminEntity;
import fr.formation.repository.AdminRepository;
import fr.formation.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AdminService adminService;

    @Override
    public UserDetailsImpl loadUserByUsername(String usernameField ) throws
            UsernameNotFoundException {
        AdminEntity user = adminService.findByUsername(usernameField);
        if(user == null) {
            throw new UsernameNotFoundException("No user named " + usernameField);
        } else {
            return new UserDetailsImpl(user);
        }
    }
}
