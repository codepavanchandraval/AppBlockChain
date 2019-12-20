package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.util.CounterChanges;

@RestController
public class ReadEmail {
	@GetMapping("/approve")
	public String sa(@RequestParam final String requestId, @RequestParam final String houseHold) {
		CounterChanges.doCountChnages(true, requestId);
		return "You have Approved this Shipment.";
	}

	@GetMapping("/reject")
	public String re(@RequestParam final String requestId, @RequestParam final String houseHold) {
		CounterChanges.doCountChnages(false, requestId);
		return "You have Rejected this Shipment.";
	}
}
