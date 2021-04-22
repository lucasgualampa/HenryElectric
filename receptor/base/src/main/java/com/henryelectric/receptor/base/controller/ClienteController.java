package com.henryelectric.receptor.base.controller;

import com.henryelectric.receptor.base.model.Cliente;
import com.henryelectric.receptor.base.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Todos los clientes")
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "cliente por id")
    public Cliente getCliente(@PathVariable Integer id){
        return clienteService.getCliente(id);
    }

    @PostMapping
    @Operation(summary = "crea un cliente")
    public String addCliente(@RequestBody Cliente cliente){
        Cliente newCliente = clienteService.addCliente(cliente);
        return ("se creo cliente: " + newCliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borra cliente por id")
    public String deleteCliente(@PathVariable Integer id){
        clienteService.deleteCliente(id);
        return ("Se borro al cliente id: " + id);
    }

}
