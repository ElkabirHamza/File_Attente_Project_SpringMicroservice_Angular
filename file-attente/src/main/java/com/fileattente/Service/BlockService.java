package com.fileattente.Service;


import com.fileattente.Model.Block;
import com.fileattente.Repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;



    public List<Block> getBlockedList(){
        return  blockRepository.findAll();
    }
}
