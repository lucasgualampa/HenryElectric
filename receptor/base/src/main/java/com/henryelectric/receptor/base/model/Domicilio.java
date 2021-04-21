package com.henryelectric.receptor.base.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Domicilio {

    @Id
    private Integer id;
    private String direccion;
    private Float tarifa;
}
