package com.henry.electric.base.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class MedidorE {

    @Id
    private Integer id;

    private String marca, modelo;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<MedidaE> mediciones;
    
}
