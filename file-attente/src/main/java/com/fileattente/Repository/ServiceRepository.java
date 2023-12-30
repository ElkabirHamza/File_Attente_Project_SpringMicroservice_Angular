package com.fileattente.Repository;

import com.fileattente.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Integer> {
    Service findByName(String name);
}
