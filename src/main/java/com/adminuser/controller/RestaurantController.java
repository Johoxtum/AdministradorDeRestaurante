package com.adminuser.controller;

import com.adminuser.persistence.entity.Restaurant;
import com.adminuser.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = {"http://localhost:4200/","http://localhost:8080"})
public class RestaurantController {

    @Autowired
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/create")
    public Restaurant createUsers(@RequestBody Restaurant restaurant){
        return restaurantService.createRestaurant(restaurant);
        }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getUsers() {
        List<Restaurant> users = restaurantService.getList();

        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") Long id,
                                                 @RequestBody Restaurant restaurant){
        restaurantService.updateRestaurant(id,restaurant.getNombre(),
                restaurant.getDireccion(),restaurant.getDescripcion(),
                restaurant.getCelular(),restaurant.getTipo_cocina());
        return ResponseEntity.ok(restaurant);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long id){
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
