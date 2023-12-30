package com.fileattente.Controller;


import com.fileattente.Model.Attente;
import com.fileattente.Model.Service;
import com.fileattente.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ser")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceController {
    @Autowired
    ServiceService serviceService;


    @DeleteMapping("/delete/{id}")
    public boolean deleteService(@PathVariable("id") int id){
        return serviceService.delete(id);
    }

    @PostMapping("/addService")
    public boolean addService(@RequestBody Service service){
        return  serviceService.AddService(service);
    }
    @GetMapping("/showAllService")
    public List<Service> findAllService(){
        return serviceService.showAllService();
    }
    @PutMapping("/updateService/{id}")
    public boolean updateService(@RequestBody String name , @PathVariable("id") int id){
        Service srv = serviceService.showByID(id);
        if(srv != null){
           // srv.setNombreDansMachine(service.getNombreDansMachine());
            //srv.setNombreEnCaisse(service.getNombreEnCaisse());

            return serviceService.updateService(name,id);

        }
        return  false;

    }
    @GetMapping("/nextClient/{id}")
    public boolean NextClientByService(@PathVariable("id") int id) {
        return serviceService.NextClientByService(id);
    }

    @GetMapping("/getServByid/{id}")
    public Service getById(@PathVariable("id") int id){
        return serviceService.showByID(id);
    }

    @GetMapping("/findbyname/{name}")
    public Service findbyname(@PathVariable("name") String name){
        return serviceService.getServiceBynAME(name);
    }

    @PostMapping("/inisialiser/{id}")
    public boolean inisialiser(@PathVariable("id") int id){
        return serviceService.remisialiser(id);
    }
}
