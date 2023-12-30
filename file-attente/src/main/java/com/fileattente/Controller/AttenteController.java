package com.fileattente.Controller;


import com.fileattente.Model.Attente;
import com.fileattente.Model.Block;
import com.fileattente.Service.AttenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class AttenteController {

    @Autowired
    private AttenteService attenteService;

    @PostMapping("/add")
    public Attente AddAttente(@RequestBody Attente attente){
        return attenteService.add(attente);
    }

    @GetMapping("/showAll")
    public ResponseEntity<Page<Attente>> getPaginatedData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Attente> entities = attenteService.showAll(page, size);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
    @GetMapping("/ByUUID/{uuid}")
    public Attente findByUUID(@PathVariable("uuid") String uuids){
        return attenteService.findByUUID(uuids);
    }
    @PutMapping("/update/{id}")
    public String updateAttente(@RequestBody Attente attente,@PathVariable("id") String id){

        Attente at = attenteService.findByUUID(id);
        if(at != null){
            at.setStatus(attente.getStatus());

            return attenteService.update(attente,id);

        }
        return  null;
    }

    @GetMapping("/bloquer/{id}")
    public boolean BloquerAttente(@PathVariable("id") String id){
        return attenteService.Bloquer(id);
    }


    @GetMapping("/ByService/{service}")
    public List<Attente> getByService(@PathVariable("service") String service){
        return attenteService.getAttenteByService(service);
    }

    @GetMapping("/ByCin/{cin}")
    public List<Attente> getByCin(@PathVariable("cin") String cin){
        return attenteService.getAttenteByCin(cin);
    }

    /*@PostMapping("/next/{id}")
    public void Next(@PathVariable("id") String id){
        attenteService.Next(id);
    }

     */

    @GetMapping("/telephones")
    public String getTel(){
        return attenteService.getClientTelephone();
    }

    @GetMapping("/blocked")
    public List<String> blockedCIN(){
        List<String> ListBlocke = attenteService.ListCINBlocked();
        return  ListBlocke;
    }

    @GetMapping("/debloquer/{cin}")
    public boolean debloquer(@PathVariable("cin") String cin){
        return  attenteService.deploquer(cin);
    }


    @GetMapping("/pdfUUID/{id}")
    public ResponseEntity<byte[]> getPdfUUID(@PathVariable("id") String id){
        return attenteService.getPdfByUUID(id);
    }

    @GetMapping("/isblocked/{cin}")
    public boolean isCinBlocked(@PathVariable("cin") String cin) {
        boolean result = attenteService.isBlocked(cin);
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/isUUIDvalid/{uuid}")
    public boolean isUUIDvalide(@PathVariable("uuid") String uuid){
        return attenteService.isUUIDvalide(uuid);
    }

    @GetMapping("/isCINvalid/{cin}")
    public boolean isCINvalide(@PathVariable("cin") String cin){
        return attenteService.isCINvalide(cin);
    }
}
