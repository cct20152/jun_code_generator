package com.fc.v2.controller;

import com.fc.v2.common.base.BaseController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController extends BaseController{

	@GetMapping("/")
	public String index() {
		return "redirect:/autoCodeController/global";
	}
}
