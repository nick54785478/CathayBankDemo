package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

	/**
	 * 導向 About 頁面
	 * 
	 * @return 導向 "about.html" 頁面
	 */
	@GetMapping("/about")
	public String home() {
		return "about";
	}
}
