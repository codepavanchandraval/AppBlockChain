package com.example;



import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.MailSendingUtil;
import com.model.ShipmentBean;
import com.service.ServiceUtil;
import com.util.BlockchainManager;
import com.util.IdGenerator;

@RestController
public class UpdateClass {

    @GetMapping("/update")
	public String up(@RequestParam final String privateKey) throws AddressException, MessagingException {
		if (privateKey == null) {
			return "Error";
		}
		String hashCode = IdGenerator.getSHA256Hash(privateKey);
		ShipmentBean bean = ServiceUtil.getShipmentBean(privateKey);
		bean.setPrivateKey(hashCode);
		bean.setHouseNo(privateKey);
		ShipmentBean previousBean =BlockchainManager.getlastBlockChainForGivenKey(hashCode,1);
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
