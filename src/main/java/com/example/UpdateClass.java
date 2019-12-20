package com.example;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.ShipmentBean;
import com.util.BlockchainManager;

@RestController
public class UpdateClass {

    @GetMapping("/update")
    public String up(@RequestParam final String privateKey) {
    	//trigger the mail and create a block with the privateKey
    	
    	ShipmentBean bean = new ShipmentBean();
    	BlockchainManager.addToBlockChain(bean);
    	
        return "update mail got Triggred";
    }
}
