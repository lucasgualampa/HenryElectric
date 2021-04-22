package com.henryelectric.receptor.base.service;

import com.henryelectric.receptor.base.model.*;
import com.henryelectric.receptor.base.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;
    private ClienteService clienteService;
    private MedidorService medidorService;
    private MedidaService medidaService;

    public DomicilioService(DomicilioRepository domicilioRepository, ClienteService clienteService, MedidorService medidorService, MedidaService medidaService) {
        this.domicilioRepository = domicilioRepository;
        this.clienteService = clienteService;
        this.medidorService = medidorService;
        this.medidaService = medidaService;
    }

    public Domicilio getDomicilio(Integer id){
        return domicilioRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public Domicilio addDomicilio(Domicilio domicilio){
        return domicilioRepository.save(domicilio);
    }

    public List<Domicilio> getDomicilios(){
        return domicilioRepository.findAll();
    }

    public void addClienteToDomicilio(Integer id, Integer clienteId){
        Domicilio domicilio = getDomicilio(id);
        Cliente cliente = clienteService.getCliente(clienteId);
        domicilio.setCliente(cliente);
        domicilioRepository.save(domicilio);
    }

    public void addMedidorToDomicilio(Integer id, Integer medidorId){
        Domicilio domicilio = getDomicilio(id);
        MedidorR medidor = medidorService.getMedidor(medidorId);
        domicilio.setMedidor(medidor);
        domicilioRepository.save(domicilio);
    }

    public void deleteDomicilio(Integer id){
        domicilioRepository.deleteById(id);
    }

    public Factura getFactura(Integer id, String mes, String anio){
        Domicilio domicilio = getDomicilio(id);
        Integer idMedidor = domicilio.getMedidor().getId();
        String diaInicial = null;
        String diaFinal = null;
        String mesAnterior = null;

        switch (mes) {
            case "01":
                diaInicial = "31";
                diaFinal = "31";
                mesAnterior = "12";
                break;
            case "02":
                diaInicial = "31";
                if (Integer.parseInt(año) % 4 == 0) {
                    diaFinal = "29";
                } else {
                    diaFinal = "28";
                }
                mesAnterior = "01";
                break;
            case "03":
                if (Integer.parseInt(año) % 4 == 0) {
                    diaInicial = "29";
                } else {
                    diaInicial = "28";
                }
                diaFinal = "31";
                mesAnterior = "02";
                break;
            case "04":
                diaInicial = "31";
                diaFinal = "30";
                mesAnterior = "03";
                break;
            case "05":
                diaInicial = "30";
                diaFinal = "31";
                mesAnterior = "04";
                break;
            case "06":
                diaInicial = "31";
                diaFinal = "30";
                mesAnterior = "05";
                break;
            case "07":
                diaInicial = "30";
                diaFinal = "31";
                mesAnterior = "06";
                break;
            case "08":
                diaInicial = "31";
                diaFinal = "31";
                mesAnterior = "07";
                break;
            case "09":
                diaInicial = "31";
                diaFinal = "30";
                mesAnterior = "08";
                break;
            case "10":
                diaInicial = "30";
                diaFinal = "31";
                mesAnterior = "09";
                break;
            case "11":
                diaInicial = "31";
                diaFinal = "30";
                mesAnterior = "10";
                break;
            case "12":
                diaInicial = "30";
                diaFinal = "31";
                mesAnterior = "11";
                break;
        }

        String fechaInicial = anio + "/" + mesAnterior + "/" + diaInicial;
        String fechaFinal = anio + "/" + mes + "/" + diaFinal;

        BodyFechas bodyFechas = new BodyFechas(fechaInicial, fechaFinal);

        Consumo consumo = medidaService.getConsumoBetweenDate(idMedidor, bodyFechas, domicilio.getTarifa());

        Factura factura = new Factura(domicilio.getDireccion(), domicilio.getTarifa(), domicilio.getCliente(), idMedidor, consumo.getMedidaInicial(), consumo.getFechaInicial(), consumo.getMedidaFinal(), consumo.getFechaFinal(), consumo.getKwh(), consumo.getCosto());

        return factura;
    }

    public List<MedidaR> getMedidasBetweenDate(Integer id, BodyFechas bodyFechas){
        Domicilio domicilio = getDomicilio(id);
        Integer idMedidor = domicilio.getMedidor().getId();
        return medidaService.getMedidasBetweenDate(idMedidor, bodyFechas);
    }

    public Consumo getConsumoBetweenDate(Integer id, BodyFechas bodyFechas){
        Domicilio domicilio = getDomicilio(id);
        Integer idMedidor = domicilio.getMedidor().getId();
        return medidaService.getConsumoBetweenDate(idMedidor, bodyFechas, domicilio.getTarifa());
    }
}
