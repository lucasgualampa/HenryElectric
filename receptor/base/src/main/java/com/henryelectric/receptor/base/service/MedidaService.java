package com.henryelectric.receptor.base.service;

import com.henryelectric.receptor.base.model.BodyFechas;
import com.henryelectric.receptor.base.model.Consumo;
import com.henryelectric.receptor.base.model.MedidaR;
import com.henryelectric.receptor.base.repository.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MedidaService {

    @Autowired
    private MedidaRepository medidaRepository;

    public List<MedidaR> getMediciones(){
        return medidaRepository.findAll();
    }

    public MedidaR getMedida(Integer id){
        return medidaRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<MedidaR> getMedicionesBetweenDate(Integer id, BodyFechas bodyFechas){
        List<MedidaR> listMediciones = medidaRepository.findByDateBetween(id, bodyFechas.getFrom(), bodyFechas.getTo());
    }

    public Consumo getConsumoBetweenDate(Integer id, BodyFechas bodyFechas, Float tarifa){
        List<MedidaR> listMediciones1 = medidaRepository.findByDateBetween(id, bodyFechas.getFrom(), bodyFechas.getFrom() + " 23:59:59.999");
        List<MedidaR> listMediciones2 = medidaRepository.findByDateBetween(id, bodyFechas.getTo(), bodyFechas.getTo() + " 23:59:59.999");

        Float consumo = listMediciones2.get(listMediciones2.size()-1).getValor() - listMediciones1.get(listMediciones1.size() - 1).getValor();
        Float costo = consumo * tarifa;
        return new Consumo(consumo, costo, listMediciones1.get(listMediciones1.size() - 1).getFecha(), listMediciones1.get(listMediciones1.size() - 1).getValor(), listMediciones2.get(listMediciones2.size() - 1).getFecha(), listMediciones2.get(listMediciones2.size() - 1).getValor());
    }

    public MedidaR addMedida(MedidaR medida){
        return medidaRepository.save(medida);
    }

}
