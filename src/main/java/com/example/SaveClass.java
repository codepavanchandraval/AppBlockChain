package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.mailUtil;
import com.model.ShipmentBean;
import com.service.ServiceUtil;
import com.util.BlockchainManager;
import com.util.IdGenerator;

@RestController
public class SaveClass {

	@GetMapping("/save")
	public String sa(@RequestParam final String privateKey) {
		// trigger the mail
		ShipmentBean bean = ServiceUtil.getShipmentBean(privateKey);
		bean.setHouseNo(privateKey);
		
		boolean flag = mailUtil.sendMail();
		// call the mail method, if there is any error while creating any mailing event
		// the return 0 or return 1
		if (!flag) {
			return "0";
		}
		String hashCode = IdGenerator.getSHA256Hash(privateKey);
		bean.setPrivateKey(hashCode);
		BlockchainManager.addToBlockChain(bean);
		return hashCode;
	}
}
