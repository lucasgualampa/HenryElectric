package com.henryelectric.receptor.base.model;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.List;

public class MedidorR {

    @Id
    private Integer id;
    private String marca, modelo;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<MedidaR> mediciones;
}
