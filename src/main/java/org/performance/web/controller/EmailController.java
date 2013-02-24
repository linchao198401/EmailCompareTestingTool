package org.performance.web.controller;

import org.performance.web.common.Constance;
import org.performance.web.dao.model.Email;
import org.performance.web.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("email.do")
public class EmailController {

	protected static final transient Logger logger = LoggerFactory.getLogger(EmailController.class);

	private EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	// @RequestMapping
	// public String entry(@RequestParam(required = false, value = "id") Long id, Model model) {
	// if (id != null && id > 0) {
	// Email email = this.emailService.getEmail(id);
	// if (email != null) {
	// model.addAttribute("email", email);
	// }
	// }
	// return "email";
	// }

	@RequestMapping(params = "op=add")
	public String addEmail(@ModelAttribute Email email, Model model) {
		if (email.getName() == null) {
			return "emailAdd";
		}
		this.emailService.addEmail(email);
		return "redirect:email.do?op=list";
	}
	@RequestMapping(params = "op=update")
	public String updateEmail(@ModelAttribute Email email, Model model, @RequestParam(required = false, value = "testCaseID") Long testCaseID) {
		this.emailService.updateEmail(email);
		if (testCaseID != null) {
			return "redirect:compare.do?op=comapre&testCaseId=" + testCaseID;
		}
		return "redirect:email.do?op=list";
	}
	@RequestMapping(params = "op=show")
	public String showEmail(Model model, @RequestParam(required = false, value = "id") Long id, @RequestParam(required = false, value = "testCaseID") Long testCaseID) {
		Email email = this.emailService.getEmail(id);
		model.addAttribute("email", email);
		model.addAttribute("testCaseID", testCaseID);
		return "emailShow";
	}

	@RequestMapping(params = "op=list")
	public String listEmail(@RequestParam(defaultValue = "1", value = "currentPage") Integer currentPage,
			@RequestParam(required = false, value = "maxCountPerPage") Integer maxCountPerPage, Model model) {

		if (currentPage == null || currentPage < 1) {
			currentPage = 1;
		}

		if (maxCountPerPage == null || maxCountPerPage < 1) {
			maxCountPerPage = Constance.DEFAULT_MAX_COUNT_PER_PAGE;
		}

		model.addAttribute("page", emailService.findEmail(null, maxCountPerPage, currentPage));

		return "emailList";
	}

	@RequestMapping(params = "op=delete")
	public String deleteEmail(@RequestParam(required = false, value = "id") Long id) {
		if (id != null && id > 0) {
			this.emailService.deleteEmail(id);
		}
		return "redirect:email.do?op=list";
	}

}
