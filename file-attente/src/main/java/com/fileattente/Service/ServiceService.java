package com.fileattente.Service;
import com.fileattente.Enums.Status;
import com.fileattente.Model.Attente;
import com.fileattente.Repository.AttenteRepository;
import com.fileattente.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.fileattente.Model.Service;

import java.util.List;


@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    AttenteRepository attenteRepository;
    public boolean AddService(Service service){
        service.setNombreDansMachine(0);
        service.setNombreEnCaisse(0);
       serviceRepository.save(service);
            return true;
    }

    public boolean updateService(String s,int id){
        Service service = serviceRepository.findById(id).orElse(null);
        //service.setNombreDansMachine(s.getNombreDansMachine());
        //service.setNombreEnCaisse(s.getNombreEnCaisse());
        service.setName(s);
        serviceRepository.save(service);
        return true;
    }

    public List<Service> showAllService(){
        return serviceRepository.findAll();
    }

    public Service showByID(int id){

        return serviceRepository.findById(id).orElse(null);
    }

    public Service getServiceBynAME(String name){
        return serviceRepository.findByName(name);
    }

    public boolean NextClientByService(int id){
        Service service = serviceRepository.findById(id).orElse(null);
        //Attente attente = attenteRepository.findTop1ByServiceName(service.getName());
        service.setNombreEnCaisse(service.getNombreEnCaisse()+1);
        serviceRepository.save(service);


        return true;
    }

    public boolean remisialiser(int id){
        Service service = serviceRepository.findById(id).orElse(null);
        service.setNombreEnCaisse(0);
        service.setNombreDansMachine(0);
        serviceRepository.save(service);
        return true;
    }

public boolean delete(int id){
        serviceRepository.deleteById(id);
        return true;
}
}
