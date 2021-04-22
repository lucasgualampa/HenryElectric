package com.henryelectric.receptor.base.controller;

import com.henryelectric.receptor.base.model.MedidaR;
import com.henryelectric.receptor.base.model.MedidorR;
import com.henryelectric.receptor.base.service.MedidorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medidores")
public class MedidorController {

    @Autowired
    private MedidorService medidorService;

    @GetMapping
    @Operation(summary = "los medidores")
    public List<MedidorR> getMedidores() {
        return medidorService.getMedidores();
    }

    @GetMapping("/{id}")
    @Operation(summary = "medidor por id")
    public MedidorR getMedidor(@PathVariable Integer id){
        return medidorService.getMedidor(id);
    }

    @PostMapping
    @Operation(summary = "crea un medidor")
    public String addMedidor(@RequestBody MedidorR medidor){
        MedidorR newMedidor = medidorService.addMedidor(medidor);
        return ("se creo el medidor: " + newMedidor);
    }

    @PostMapping("/{id}/medida")
    @Operation(summary = "crea una medida")
    public String addMedida(@RequestBody MedidaR medida, @PathVariable Integer id){
        medidorService.addMedidaToMedidor(id, medida);
        return ("se agrego la medida");
    }

    @PutMapping
    @Operation(summary = "edita el medidor")
    public String editMedidor(@RequestBody MedidorR medidor){
        MedidorR editedMedidor = medidorService.editMedidor(medidor);
        return ("se edito el medidor: " + editedMedidor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borra el medidor")
    public String deleteMedidor(@PathVariable Integer id){
        medidorService.deleteMedidor(id);
        return ("se borro el medidor con id: " + id);
    }

}
