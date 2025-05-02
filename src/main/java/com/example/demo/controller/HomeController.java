package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/**
	 * 導向首頁 Home
	 * 
	 * @return 導向 "index.html"
	 */
	@GetMapping({ "/", "/#", "/Home", "/index" })
	public String home() {
		return "index";
	}
}
