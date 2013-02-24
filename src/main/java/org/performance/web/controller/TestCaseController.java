package org.performance.web.controller;

import java.util.List;

import org.performance.web.common.Constance;
import org.performance.web.common.PageResult;
import org.performance.web.dao.model.Email;
import org.performance.web.dao.model.TemplateGroup;
import org.performance.web.dao.model.TestCase;
import org.performance.web.service.EmailService;
import org.performance.web.service.TemplateGroupService;
import org.performance.web.service.TestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("testCase.do")
public class TestCaseController {

	protected static final transient Logger logger = LoggerFactory.getLogger(EmailController.class);

	private TestCaseService testCaseService;

	private EmailService emailService;

	private TemplateGroupService templateGroupService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@Autowired
	public void setTemplateGroupService(TemplateGroupService templateGroupService) {
		this.templateGroupService = templateGroupService;
	}

	@Autowired
	public void setTestCaseService(TestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}

	@RequestMapping
	public String entry(@RequestParam(required = false, value = "id") Long id, Model model) {

		PageResult<Email> page = emailService.findEmail(null, Integer.MAX_VALUE, 1);
		model.addAttribute("emails", page.getResult());

		List<TemplateGroup> templateGroups = templateGroupService.getAllTemplateGroup();
		model.addAttribute("templateGroups", templateGroups);

		if (id != null && id > 0) {
			TestCase testCase = this.testCaseService.getTestCase(id);
			if (testCase != null) {
				model.addAttribute("testCase", testCase);
			}
			return "testCaseShow";
		}

		return "testCase";
	}

	@RequestMapping(params = "op=add")
	public String addTestCase(@ModelAttribute TestCase testCase, Model model, @RequestParam(value = "templateGroupId", required = false) Long templateGroupId,
			@RequestParam(value = "emailId", required = false) Long emailId) {
		if (testCase.getName() == null) {
			if (templateGroupId != null) {
				TemplateGroup templateGroup = templateGroupService.getTemplateGroup(templateGroupId);
				model.addAttribute("selectedTemplateGroup", templateGroup);
			}

			PageResult<Email> page = emailService.findEmail(null, Integer.MAX_VALUE, 1);
			model.addAttribute("emails", page.getResult());

			List<TemplateGroup> templateGroups = templateGroupService.getAllTemplateGroup();
			model.addAttribute("templateGroups", templateGroups);
			return "testCaseAdd";
		}

		TemplateGroup t = new TemplateGroup();
		t.setId(templateGroupId);
		testCase.setTemplateGroup(t);

		Email email = new Email();
		email.setId(emailId);
		testCase.setEmail(email);
		this.testCaseService.addTestCase(testCase);
		return "redirect:testCase.do?op=list";
	}

	@RequestMapping(params = "op=list")
	public String listTestCase(@RequestParam(defaultValue = "1", value = "currentPage") Integer currentPage,
			@RequestParam(required = false, value = "maxCountPerPage") Integer maxCountPerPage, @ModelAttribute TestCase testCase, Model model) {

		if (currentPage == null || currentPage < 1) {
			currentPage = 1;
		}

		if (maxCountPerPage == null || maxCountPerPage < 1) {
			maxCountPerPage = Constance.DEFAULT_MAX_COUNT_PER_PAGE;
		}

		model.addAttribute("page", testCaseService.findTestCase(testCase, maxCountPerPage, currentPage));

		return "testCaseList";
	}

	@RequestMapping(params = "op=delete")
	public String deleteTestCase(@RequestParam(required = false, value = "id") Long id) {
		if (id != null && id > 0) {
			this.testCaseService.deleteTestCase(id);
		}
		return "redirect:testCase.do?op=list";
	}

}
