package com.fileattente.Service;


import com.fileattente.Enums.Status;
import com.fileattente.Model.Attente;
import com.fileattente.Model.Block;
import com.fileattente.Repository.AttenteRepository;
import com.fileattente.Repository.BlockRepository;
import com.fileattente.Repository.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttenteService {


    List<String> blocked = new ArrayList<>();


    private final AttenteRepository attenteRepository;

    private final ServiceRepository serviceRepository;
    private final RestTemplate restTemplate;
    private final BlockRepository blockRepository;
    public AttenteService(AttenteRepository attenteRepository, ServiceRepository serviceRepository, RestTemplate restTemplate, BlockRepository blockRepository){
        this.attenteRepository=attenteRepository;
        this.serviceRepository = serviceRepository;
        this.restTemplate = restTemplate;
        this.blockRepository = blockRepository;
    }
    public boolean Bloquer(String cin){
        List<Block> blocks = blockRepository.findAll();
        List<String> cins = new ArrayList<>();
        for (Block b:blocks){
            cins.add(b.getCinBlocked());
        }


        for (String c : cins ){
            if (c.equals(cin)){
                return false;
            }
        }
        Block block1 = new Block();
        block1.setCinBlocked(cin);
        blockRepository.save(block1);
        return true;

    }

    public List<String> ListCINBlocked(){
        List<Block> blockes = blockRepository.findAll();
        List<String> cins = new ArrayList<>();
        for (Block block : blockes){
            cins.add(block.getCinBlocked());

        }
        return cins;
        //return null;

    }

    public Attente add(Attente attente) {
        List<String> cins = new ArrayList<>();
        List<Block> blocks = blockRepository.findAll();
        for (Block block : blocks) {
            cins.add(block.getCinBlocked());
        }
        String cin = attente.getCin();
        String str = "";
        for (String c : cins) {
            if (c.equals(cin)) {
                return null;
            }
        }
        UUID id = UUID.randomUUID();
        String idAttente = id.toString();
        attente.setId(idAttente);
       //attente.setStatus(Status.ENATTENTE);
        attente.setDateCreation(LocalDateTime.now());
        String name = attente.getService();
        com.fileattente.Model.Service service = serviceRepository.findByName(name);
        service.setNombreDansMachine(service.getNombreDansMachine() + 1);
        attente.setNb(service.getNombreDansMachine());
        serviceRepository.save(service);
        if (attente.getNb()>service.getNombreEnCaisse()){
            attente.setStatus(Status.ENATTENTE);
            attenteRepository.save(attente);
        }
        else if (attente.getNb()==service.getNombreEnCaisse()){
            attente.setStatus(Status.ENCOURS);
            attenteRepository.save(attente);
        }else
            attente.setStatus(Status.TERMINER);
            attenteRepository.save(attente);

        return attente;
        //String url = "http://localhost:8091/qr/generatePdf/"+attente.getId();
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        //ResponseEntity<String> responseEntity = restTemplate.exchange(
          //      url,
            //    HttpMethod.POST,
              //  requestEntity,
          //      String.class
        //);
        //if (responseEntity.getStatusCode().is2xxSuccessful()) {
          //  String responseBody = responseEntity.getBody();
            //return responseBody;
        //} else {
          //  return null;
        }


    public Page<Attente> showAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return attenteRepository.findAll(pageable);
    }

    public Attente findByUUID(String uuid){
        return attenteRepository.findById(uuid).orElse(null);
    }

    public String update(Attente attente,String uuid){
        Attente attente1 = attenteRepository.findById(uuid).orElse(null);
        attente1.setStatus(attente.getStatus());
        attenteRepository.save(attente1);
        return "Succes !";
    }

    public List<Attente> getAttenteByService(String service){
        return attenteRepository.findByService(service);
    }
    public List<Attente> getAttenteByCin(String cin){
        return attenteRepository.findByCin(cin);
    }

    public void Next(String srv){
        com.fileattente.Model.Service service = serviceRepository.findByName(srv);
        service.setNombreEnCaisse(service.getNombreEnCaisse()+1);

        //com.fileattente.Model.Service service1 = serviceRepository.findByName(srv);
       // Attente attente = attenteRepository.findByService(service1.getId());
//        if (service.getNombreDansMachine()==service.getNombreEnCaisse()){
//            attente.setStatus(Status.ENCOURS);
//        }
//        if (service.getNombreEnCaisse()>service.getNombreDansMachine()){
//            attente.setStatus(Status.TERMINER);
//        }

        serviceRepository.save(service);


    }

    public String getClientTelephone(){
        List<Attente> attentes = attenteRepository.findAll();
        List<com.fileattente.Model.Service> services = serviceRepository.findAll();
        String telephones = "";
        for(com.fileattente.Model.Service service : services){
            for (Attente attente : attentes){
                if(service.getName().equals(attente.getService())){
                    if(service.getNombreEnCaisse()+2 == attente.getNb()){
                        telephones = attente.getTel();
                    }
                }
            }
        }
        return telephones;
    }


    public boolean deploquer(String cin){
        String str = "Le CIN :";
        for (Block block : blockRepository.findAll()){
            if(block.getCinBlocked().equals(cin)){
                //blocked.remove(s);
                blockRepository.delete(block);
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<byte[]> getPdfByUUID(String id){
        String url = "http://localhost:8091/qr/pdf/"+id;
        Attente attente = attenteRepository.findById(id).orElse(null);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            return new ResponseEntity<>(responseBody.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    public boolean isBlocked(String cin) {
        Block block = blockRepository.findByCinBlocked(cin);
        if (block == null) {
            return false;
        } else {
            return true;
        }
    }

public boolean isUUIDvalide(String uuid){
        Attente attente = attenteRepository.findById(uuid).orElse(null);
        if (attente!=null){
            return true;
        }
        else return false;
}
    public boolean isCINvalide(String cin){
        List<Attente> attente = attenteRepository.findByCin(cin);
        if (attente.isEmpty()){
            return false;
        }
        else return true;
    }




}