package com.fileattente.Repository;

import com.fileattente.Model.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block,Integer> {
    Block findByCinBlocked(String cin);
}
