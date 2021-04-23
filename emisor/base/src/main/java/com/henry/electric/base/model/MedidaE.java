package com.henry.electric.base.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class MedidaE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float valor;
    private Date fecha;



    // constructor sin id porq se autogenera
    public MedidaE(Float valor, Date fecha) {
        this.valor = valor;
        this.fecha = fecha;
    }

}
