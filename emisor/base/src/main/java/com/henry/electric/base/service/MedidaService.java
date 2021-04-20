package com.henry.electric.base.service;

import com.henry.electric.base.model.MedidaE;
import com.henry.electric.base.repository.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MedidaService {

    @Autowired
    private MedidaRepository medidaRepository;

    public List<MedidaE> getMedidas() {
        return medidaRepository.findAll();
    }

    public MedidaE getMedida(Integer id){
        return medidaRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public MedidaE addMedida(MedidaE medida) {
        return medidaRepository.save(medida);
    }
}
