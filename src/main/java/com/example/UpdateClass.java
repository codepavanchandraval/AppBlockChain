package com.example;



import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.MailSendingUtil;
import com.model.ShipmentBean;
import com.util.BlockchainManager;
import com.util.IdGenerator;

@RestController
public class UpdateClass {

    @GetMapping("/update")
	public String up(@RequestParam final String privateKey) throws AddressException, MessagingException {
		// trigger the mail and create a block with the privateKey
    	
    	String hashKey = IdGenerator.getSHA256Hash(privateKey);
		ShipmentBean bean =BlockchainManager.getlastBlockChainForGivenKey(hashKey,1);
		ShipmentBean previousBean = BlockchainManager.getlastBlockChainForGivenKey(hashKey, 2);
    	
    	boolean flag = MailSendingUtil.sendMail(bean, previousBean);
		// call the mail method, if there is any error while creating mailing event then
		// return 0 or return the privateKey whichever is coming
		if (!flag) {
			return "0";
		}
	    BlockchainManager.addToBlockChain(bean);
		return "1";
	}
}
