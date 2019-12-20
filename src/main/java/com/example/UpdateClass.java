package com.example;



import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.MailSendingUtil;
import com.model.ShipmentBean;
import com.util.BlockchainManager;

@RestController
public class UpdateClass {

    @GetMapping("/update")
	public String up(@RequestParam final String privateKey) throws AddressException, MessagingException {
		if (privateKey == null) {
			return "Error";
		}
		ShipmentBean bean =BlockchainManager.getlastBlockChainForGivenKey(privateKey,1);
		ShipmentBean previousBean = BlockchainManager.getlastBlockChainForGivenKey(privateKey, 2);
		if (bean == null || previousBean == null) {
			return "Error";
		}
    	boolean flag = MailSendingUtil.sendMail(bean, previousBean);
		if (!flag) {
			return "0";
		}
		bean.setDate(LocalDateTime.now());
	    BlockchainManager.addToBlockChain(bean);
		return "1";
	}
}
