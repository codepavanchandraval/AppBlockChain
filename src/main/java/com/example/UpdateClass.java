package com.example;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.mailUtil;
import com.model.ShipmentBean;
import com.util.BlockchainManager;
import com.util.IdGenerator;

@RestController
public class UpdateClass {

    @GetMapping("/update")
	public String up(@RequestParam final String privateKey) {
		// trigger the mail and create a block with the privateKey
    	
    	String hashKey = IdGenerator.getSHA256Hash(privateKey);
		ShipmentBean bean =BlockchainManager.getlastBlockChainForGivenKey(hashKey,1);
		ShipmentBean previousBean = BlockchainManager.getlastBlockChainForGivenKey(hashKey, 2);
    	
    	boolean flag = mailUtil.sendMail();
		// call the mail method, if there is any error while creating mailing event then
		// return 0 or return the privateKey whichever is coming
		if (!flag) {
			return "0";
		}
	    BlockchainManager.addToBlockChain(bean);
		return "1";
	}
}
