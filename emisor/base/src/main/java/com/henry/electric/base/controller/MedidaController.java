package com.henry.electric.base.controller;

import com.henry.electric.base.model.MedidaE;
import com.henry.electric.base.service.MedidaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mediciones")
public class MedidaController {

    @Autowired
    private MedidaService medidaService;

    @GetMapping
    @Operation(summary = "todas las medidas")
    public List<MedidaE> getMedidas() {
        return medidaService.getMedidas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "la medida por id")
    public MedidaE getMedida(@PathVariable Integer id) {
        return medidaService.getMedida(id);
    }

    @PostMapping
    @Operation(summary = "alta de una medida")
    public String addMedida(@RequestBody MedidaE medida){
        MedidaE newMedida = medidaService.addMedida(medida);
        return ("Se creo la medida: " + newMedida);
    }

}
