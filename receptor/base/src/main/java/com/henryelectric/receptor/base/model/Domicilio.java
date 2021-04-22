package com.henryelectric.receptor.base.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Domicilio {

    @Id
    private Integer id;
    private String direccion;
    private Float tarifa;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Cliente cliente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medidorr_id")
    private MedidorR medidor;
}
