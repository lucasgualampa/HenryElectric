package com.henryelectric.receptor.base.controller;

import com.henryelectric.receptor.base.model.*;
import com.henryelectric.receptor.base.service.DomicilioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    @GetMapping
    @Operation(summary = "Todos los domicilios")
    public List<Domicilio> getDomicilios(){
        return domicilioService.getDomicilios();
    }

    @GetMapping("{id}/factura")
    @Operation(summary = "La factura mensual")
    public Factura getFactura(@PathVariable Integer id, @RequestParam(value = "mes") String mes, @RequestParam(value = "anio") String anio){
        return domicilioService.getFactura(id, mes, anio);
    }

    @PostMapping
    @Operation(summary = "Crea un domicilio")
    public String addDomicilio(@RequestBody Domicilio domicilio){
        Domicilio newDomicilio = domicilioService.addDomicilio(domicilio);
        return ("Se creo el domicilio: " + newDomicilio);
    }

    @PutMapping("/{id}/cliente/{idCliente}")
    @Operation(summary = "crea un cliente en domicilio")
    private String addCliente(@PathVariable Integer id, @PathVariable Integer idCliente){
        domicilioService.addClienteToDomicilio(id, idCliente);
        return ("Creado cliente con id: " + idCliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borra el domicilio por id")
    public String deleteDomicilio(@PathVariable Integer id){
        domicilioService.deleteDomicilio(id);
        return ("Se borro el domicilio de id: " + id);
    }

    @PostMapping("/{id}/mediciones")
    @Operation(summary = "las mediciones entre fechas por domicilio")
    public List<MedidaR> getMedicionesBetweenDate(@PathVariable Integer id, @RequestBody BodyFechas bodyFechas){
        return domicilioService.getMedicionesBetweenDate(id, bodyFechas);
    }

    @PostMapping("/{id}/consumo")
    @Operation(summary = "el consumo entre fechas por domicilio")
    public Consumo getConsumoBetweenDates(@PathVariable Integer id, @RequestBody BodyFechas bodyFechas){
        return domicilioService.getConsumoBetweenDate(id, bodyFechas);
    }

}
