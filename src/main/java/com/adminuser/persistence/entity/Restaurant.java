package com.adminuser.persistence.entity;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "administrador_restaurante")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String descripcion;
    private String direccion;
    private TypeCousin tipo_cocina;
    private String celular;

}
