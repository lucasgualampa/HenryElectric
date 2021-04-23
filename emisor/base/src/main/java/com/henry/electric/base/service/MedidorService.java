package com.henry.electric.base.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.electric.base.model.MedidaE;
import com.henry.electric.base.model.MedidorE;
import com.henry.electric.base.repository.MedidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

    @Scheduled(fixedRate = 50)
    public void sendMedicion() throws IOException, InterruptedException {
        MedidaE medida;
        Float valor;
        Date date;
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        if (getMedidor(1111).getMediciones().size() == 0){
            valor = 0.0f;
            date = new Date();
            medida = new MedidaE(valor, date);
        } else {
            valor = getMedidor(1111).getMediciones().get(getMedidor(1111).getMediciones().size()-1).getValor() + 0.5f;
            Date date2 = getMedidor(1111).getMediciones().get(getMedidor(1111).getMediciones().size()-1).getFecha();
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(date2);
            calendario.add(Calendar.HOUR, 12);
            date = calendario.getTime();
            medida = new MedidaE(valor, date);
        }

        HttpClient client = HttpClient.newBuilder().build();

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(medida);

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8082/medidores/1111/medida"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.discarding());

        MedidaE addMedida = medidaService.addMedida(medida);
        MedidorE medidor = getMedidor(1111);
        medidor.getMediciones().add(medida);
        medidorRepository.save(medidor);

        System.out.println("Se envio la medida: " + addMedida);
    }

}
