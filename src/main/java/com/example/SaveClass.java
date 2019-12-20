package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveClass {

	@GetMapping("/save")
	public String sa(@RequestParam final String userName) {
		//trigger the mail
		return "mail got Triggerd";
	}
}
