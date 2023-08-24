package com.adminuser.service;

import com.adminuser.exceptions.ToDoExceptions;
import com.adminuser.persistence.entity.Restaurant;
import com.adminuser.persistence.entity.TypeCousin;
import com.adminuser.persistence.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getList(){
        return restaurantRepository.findAll();
    }
    @Transactional
    public void updateRestaurant(Long id, String name, String address, String description, String phone, TypeCousin typeCousin){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()){
            Restaurant restaurant1 = restaurant.get();
            restaurant1.setNombre(name);
            restaurant1.setDireccion(address);
            restaurant1.setDescripcion(description);
            restaurant1.setCelular(phone);
            restaurant1.setTipo_cocina(typeCousin);
            restaurantRepository.save(restaurant1);
        } else {
            throw new ToDoExceptions("The restaurant not found",HttpStatus.NOT_FOUND);
        }


    }
    @Transactional
    public void deleteById(Long id){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isEmpty()){
            throw new ToDoExceptions("The restaurant not found", HttpStatus.NOT_FOUND);
        }
        restaurantRepository.deleteById(id);
    }
}
