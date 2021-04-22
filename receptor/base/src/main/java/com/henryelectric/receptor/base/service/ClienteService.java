package com.henryelectric.receptor.base.service;

import com.henryelectric.receptor.base.model.Cliente;
import com.henryelectric.receptor.base.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    public Cliente addCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente getCliente(Integer id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void deleteCliente(Integer id){
        clienteRepository.deleteById(id);
    }

}
