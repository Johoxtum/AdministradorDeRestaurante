package com.adminuser.service;

import com.adminuser.exceptions.ToDoExceptions;
import com.adminuser.persistence.entity.Admin;
import com.adminuser.persistence.repository.AdminRepository;
import com.adminuser.service.dto.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public AdminService(AdminRepository adminRepository ) {
        this.adminRepository = adminRepository;

    }

    public Admin createUser(Admin admin){
        String hashedPassword = passwordEncoder.encode(admin.getContrasena());
        admin.setContrasena(hashedPassword);
        return adminRepository.save(admin);
    }

    public Admin login(AuthDTO authDTO){
        Admin admin = adminRepository.findByEmail(authDTO.getEmail());

        if (admin != null && passwordEncoder.matches(authDTO.getPassword(),admin.getContrasena())){
            return admin;
        } else {
            throw new ToDoExceptions("Invalid credentials", HttpStatus.CONFLICT);
        }
    }

    @Transactional
    public Admin resetPassword(AuthDTO authDTO){

        Admin admin = adminRepository.findByEmail(authDTO.getEmail());

            String encryptedPass = passwordEncoder.encode(authDTO.getPassword());
            admin.setContrasena(encryptedPass);
            return  adminRepository.save(admin);

    }

}
