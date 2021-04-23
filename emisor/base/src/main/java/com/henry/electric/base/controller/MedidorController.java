package com.henry.electric.base.controller;

import com.henry.electric.base.model.MedidaE;
import com.henry.electric.base.model.MedidorE;
import com.henry.electric.base.service.MedidorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medidores")
public class MedidorController {

    @Autowired
    private MedidorService medidorService;

    @GetMapping
    @Operation(summary = "Todos los medidores")
    public List<MedidorE> getMedidores(){
        return medidorService.getMedidores();
    }

    @GetMapping("/{id}")
    @Operation(summary = "medidor por id")
    public MedidorE getMedidor(@PathVariable Integer id){
        return medidorService.getMedidor(id);
    }

    @PostMapping
    @Operation(summary = "alta de medidor")
    public String addMedidor(@RequestBody MedidorE medidor){
        MedidorE newMedidor = medidorService.addMedidor(medidor);
        return ("Se creo medidor: " + newMedidor);
    }

    @PostMapping("/{id}/medida")
    @Operation(summary = "crea una medida por id de medidor")
    public String addMedida(@RequestBody MedidaE medida, @PathVariable Integer id){
        medidorService.addMedidaToMedidor(id, medida);
        return ("Se agrego la medida");
    }

    @PutMapping
    @Operation(summary = "edita el medidor")
    public String editMedidor(@RequestBody MedidorE medidor){
        MedidorE newMedidor = medidorService.editMedidor(medidor);
        return ("Se edito el medidor: " + newMedidor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borra medidor por id")
    public String deleteMedidor(@PathVariable Integer id){
        medidorService.deleteMedidor(id);
        return ("Se borro el medidor");
    }
}
