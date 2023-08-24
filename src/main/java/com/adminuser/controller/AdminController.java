package com.adminuser.controller;

import com.adminuser.persistence.entity.Admin;
import com.adminuser.service.AdminService;
import com.adminuser.service.dto.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:4200/","http://localhost:8080"})
public class AdminController {

    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public Admin createUser(@RequestBody Admin admin){
        return adminService.createUser(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody AuthDTO authDTO){
        try {
            Admin admin = adminService.login(authDTO);
            return ResponseEntity.ok(admin);
        } catch (HttpClientErrorException.Unauthorized e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    @PatchMapping("/reset")
    public ResponseEntity<Admin> resetPassword(@RequestBody AuthDTO authDTO){
        try {
            Admin admin = adminService.resetPassword(authDTO);
            return ResponseEntity.ok(admin);
        } catch (Exception e ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
