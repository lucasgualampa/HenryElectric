package com.henry.electric.base.service;

import com.henry.electric.base.model.MedidaE;
import com.henry.electric.base.model.MedidorE;
import com.henry.electric.base.repository.MedidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MedidorService {

    @Autowired
    private MedidorRepository medidorRepository;

    @Autowired
    private MedidaService medidaService;

    public List<MedidorE> getMedidores() {
        return medidorRepository.findAll();
    }

    public MedidorE getMedidor(Integer id){
        return medidorRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public MedidorE addMedidor(MedidorE medidor){
        return medidorRepository.save(medidor);
    }

    public MedidorE editMedidor(MedidorE objMedidor){
        MedidorE auxMedidor = getMedidor(objMedidor.getId());
        MedidorE editedMedidor = new MedidorE();
        editedMedidor.setId(auxMedidor.getId());

        if (objMedidor.getMarca() != null) {
            editedMedidor.setMarca(objMedidor.getMarca());
        } else {
            editedMedidor.setMarca(auxMedidor.getMarca());
        }

        if (objMedidor.getMediciones() != null) {
            editedMedidor.setMediciones(objMedidor.getMediciones());
        } else {
            editedMedidor.setMediciones(auxMedidor.getMediciones());
        }

        if (objMedidor.getModelo() != null){
            editedMedidor.setModelo(objMedidor.getModelo());
        } else {
            editedMedidor.setModelo(auxMedidor.getModelo());
        }

        return medidorRepository.save(editedMedidor);
    }

    public void deleteMedidor(Integer id){
        medidorRepository.deleteById(id);
    }

    public void addMedidaToMedidor(Integer id, MedidaE medida){
        MedidorE medidor = getMedidor(id);
        MedidaE newMedida = medidaService.addMedida(medida);
        List<MedidaE> listaMediciones = medidor.getMediciones();
        listaMediciones.add(newMedida);
        medidorRepository.save(medidor);
    }

}
