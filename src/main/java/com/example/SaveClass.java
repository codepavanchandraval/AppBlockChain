package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveClass {

	@GetMapping("/save")
	public String sa(@RequestParam final String userName) {
		// trigger the mail
		boolean flag = false;
		// call the mail method, if there is any error while creating any mailing event
		// the return 0 or return 1
		if (flag) {
			return "0";
		}
		return "1";
	}
}
