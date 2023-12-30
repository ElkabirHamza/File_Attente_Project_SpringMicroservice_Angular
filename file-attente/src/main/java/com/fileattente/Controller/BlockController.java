package com.fileattente.Controller;


import com.fileattente.Model.Block;
import com.fileattente.Service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {

    @Autowired
    private BlockService blockService;


    @GetMapping("/list")

    public List<Block> getBlocked(){
        return blockService.getBlockedList();
    }

}
