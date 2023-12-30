package com.fileattente.Repository;

import com.fileattente.Model.Attente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttenteRepository extends JpaRepository<Attente,String> {

    public List<Attente> findByService(String service);
    //@Query("SELECT a FROM Attente a WHERE a.service = ?1")
    //public Attente findByServiceName(String service);

    //@Query("SELECT a FROM Attente a WHERE a.service = :serviceName")
    //Attente findTop1ByServiceName(@Param("serviceName") String name);
    public List<Attente> findByCin(String cin);
    //public Attente findByService(int id);
}
