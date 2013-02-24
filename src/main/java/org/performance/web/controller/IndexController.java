package org.performance.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index.do")
public class IndexController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(params = "op=index")
	public String goToIndex(Model model) {
		return "index";
	}
}
