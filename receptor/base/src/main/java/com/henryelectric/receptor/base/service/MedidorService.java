package com.henryelectric.receptor.base.service;

import com.henryelectric.receptor.base.model.MedidaR;
import com.henryelectric.receptor.base.model.MedidorR;
import com.henryelectric.receptor.base.repository.MedidorRepository;
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

    public List<MedidorR> getMedidores(){
        return medidorRepository.findAll();
    }

    public MedidorR getMedidor(Integer id){
        return medidorRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public MedidorR addMedidor(MedidorR medidor){
        return  medidorRepository.save(medidor);
    }

    public MedidorR editMedidor(MedidorR medidor){
        MedidorR auxMedidor = getMedidor(medidor.getId());
        MedidorR editedMedidor = new MedidorR();
        editedMedidor.setId(auxMedidor.getId());

        if (medidor.getMarca() != null){
            editedMedidor.setMarca(medidor.getMarca());
        } else {
            editedMedidor.setMarca(auxMedidor.getMarca());
        }
        if (medidor.getModelo() != null){
            editedMedidor.setModelo(medidor.getModelo());
        } else {
            editedMedidor.setModelo(auxMedidor.getModelo());
        }
        if (medidor.getMediciones() != null){
            editedMedidor.setMediciones(medidor.getMediciones());
        } else {
            editedMedidor.setMediciones(auxMedidor.getMediciones());
        }

        return medidorRepository.save(editedMedidor);
    }

    public void deleteMedidor(Integer id){
        medidorRepository.deleteById(id);
    }

    public void addMedidaToMedidor(Integer id, MedidaR medida){
        MedidorR newMedidor = getMedidor(id);
        medida.setIdMedidor(id);
        MedidaR newMedida = medidaService.addMedida(medida);
        List<MedidaR> listMediciones = newMedidor.getMediciones();
        listMediciones.add(newMedida);
        medidorRepository.save(newMedidor);
    }
}
