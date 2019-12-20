package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class email {

	@GetMapping("/approve")
	public String sa(@RequestParam final String approvalRequestId) {
		
		
		return "You have Approved this Shipment.";
	}
	
	@GetMapping("/reject")
	public String re(@RequestParam final String approvalRequestId) {
		
		return "You have Rejected this Shipment.";
	}
}
