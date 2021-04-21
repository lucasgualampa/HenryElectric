package com.henryelectric.receptor.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Factura {

    private String direccion;
    private Float tarifa, medicionInicial, medicionFinal, consumoKwh, total;
    private Cliente cliente;
    private Integer numMedidor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date fechaMedidaInicial, fechaMedidaFinal;

}
