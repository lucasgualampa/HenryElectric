package com.henryelectric.receptor.base.controller;

import com.henryelectric.receptor.base.model.BodyFechas;
import com.henryelectric.receptor.base.model.Consumo;
import com.henryelectric.receptor.base.model.MedidaR;
import com.henryelectric.receptor.base.service.MedidaService;
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
    @Operation(summary = "las mediciones")
    public List<MedidaR> getMediciones(){
        return medidaService.getMediciones();
    }

    @GetMapping("/{id}")
    @Operation(summary = "una medida por id")
    public MedidaR getMedida(@PathVariable Integer id){
        return medidaService.getMedida(id);
    }

    @PostMapping("/{idMedidor}")
    @Operation(summary = "las mediciones entre fechas por medidor")
    public List<MedidaR> getMedicionesBetweenDates(@PathVariable Integer idMedidor, @RequestBody BodyFechas bodyFechas){
        return medidaService.getMedicionesBetweenDate(idMedidor, bodyFechas);
    }

    @PostMapping("/{idMedidor}/consumo/{tarifa}")
    @Operation(summary = "consumo entre fechas por medidor")
    public Consumo getConsumoBetweenDates(@PathVariable Integer idMedidor, @PathVariable Float tarifa, @RequestBody BodyFechas bodyFechas){
        return medidaService.getConsumoBetweenDate(idMedidor, bodyFechas, tarifa);
    }

    @PostMapping
    @Operation(summary = "crea una medida")
    public String addMedida(@RequestBody MedidaR medida){
        MedidaR newMedida = medidaService.addMedida(medida);
        return ("Se creo el medidor: " + newMedida);
    }

}
