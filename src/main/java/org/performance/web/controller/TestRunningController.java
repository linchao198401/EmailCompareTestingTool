package org.performance.web.controller;

import java.util.Map;
import java.util.Set;

import org.performance.web.common.Constance;
import org.performance.web.common.PageResult;
import org.performance.web.common.util.FreeMarkerUtil;
import org.performance.web.controller.form.CompareForm;
import org.performance.web.dao.model.Email;
import org.performance.web.dao.model.TemplateGroup;
import org.performance.web.dao.model.TemplateSequence;
import org.performance.web.dao.model.TestCase;
import org.performance.web.dao.model.TestSuite;
import org.performance.web.service.EmailService;
import org.performance.web.service.ParameterService;
import org.performance.web.service.TemplateGroupService;
import org.performance.web.service.TestCaseService;
import org.performance.web.service.TestSuiteService;
import org.performance.web.service.release.ReleaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("testRunning.do")
public class TestRunningController {

	protected static final transient Logger logger = LoggerFactory
			.getLogger(TestSuiteController.class);

	private TestSuiteService testSuiteService;
	
	private ReleaseService releaseService;

	private TestCaseService testCaseService;

	private EmailService emailService;

	private TemplateGroupService templateGroupService;

	private ParameterService parameterService;

	@Autowired
	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@Autowired
	public void setTemplateGroupService(
			TemplateGroupService templateGroupService) {
		this.templateGroupService = templateGroupService;
	}

	@Autowired
	public void setTestCaseService(TestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}

	@Autowired
	public void setReleaseService(ReleaseService releaseService) {
		this.releaseService = releaseService;
	}

	@Autowired
	public void setTestSuiteService(TestSuiteService testSuiteService) {
		this.testSuiteService = testSuiteService;
	}

	@RequestMapping(params = "op=run")
	public String run(@RequestParam(required = false, value = "id") Long id,
			Model model) {
		if (id != null && id > 0) {
			testSuiteService.startTestSuite(id);
			TestSuite testSuite = testSuiteService.getTestSuite(id);
			model.addAttribute("testSuite", testSuite);
			return "testCaseRunList";
		} else {
			return "redirect:testRunning.do?op=list";
		}
	}

	@RequestMapping(params = "op=comapre")
	public String comapre(Model model,
			@RequestParam("testCaseId") Long testCaseId,
			@RequestParam("testSuiteId") Long testSuiteId) {

		TestCase testCase = testCaseService.getTestCase(testCaseId);

		long templateGroupID = testCase.getTemplateGroup().getId();
		TemplateGroup templateGroup = templateGroupService
				.getTemplateGroup(templateGroupID);
		Set<TemplateSequence> sequences = templateGroup.getTemplateSequences();
		String[] contents = new String[sequences.size()];
		for (TemplateSequence sequence : sequences) {
			int order = sequence.getSequence();
			contents[order - 1] = sequence.getTemplate().getEn();
			if (sequence.getTemplate().getType().equals("Header")) {
				model.addAttribute("headerID", sequence.getTemplate().getId());
			}
			if (sequence.getTemplate().getType().equals("Body")) {
				model.addAttribute("bodyID", sequence.getTemplate().getId());
			}
			if (sequence.getTemplate().getType().equals("Footer")) {
				model.addAttribute("footerID", sequence.getTemplate().getId());
			}
		}
		StringBuilder all = new StringBuilder();
		for (String part : contents) {
			all.append(part);
		}
		Map<String, Object> map = parameterService.getAllParameters();
		CompareForm compareForm = new CompareForm();
		compareForm.setTemplate(FreeMarkerUtil.filterByFreeMarker(
				all.toString(), map));

		long emailID = testCase.getEmail().getId();
		Email email = emailService.getEmail(emailID);
		String content = email.getContent();
		compareForm.setEmail(content);
		model.addAttribute("form", compareForm);
		model.addAttribute("testCaseID", testCase.getId());
		model.addAttribute("emailID", email.getId());
		model.addAttribute("allOwners", releaseService.getAllOwners(false));

		compareForm.setTestCaseId(testCaseId);
		compareForm.setTestSuiteId(testSuiteId);

		return "runCompare";
	}

	@RequestMapping(params = "op=list")
	public String list(
			@RequestParam(defaultValue = "1", value = "currentPage") Integer currentPage,
			@RequestParam(required = false, value = "maxCountPerPage") Integer maxCountPerPage,
			@ModelAttribute TestSuite testSuite, Model model) {

		if (currentPage == null || currentPage < 1) {
			currentPage = 1;
		}

		if (maxCountPerPage == null || maxCountPerPage < 1) {
			maxCountPerPage = Constance.DEFAULT_MAX_COUNT_PER_PAGE;
		}

		PageResult<TestSuite> page = testSuiteService.findTestSuite(testSuite,
				maxCountPerPage, currentPage);
		System.out.println(page.getResult());
		model.addAttribute("page", page);
		return "testRuningList";
	}

	@RequestMapping(params = "op=succeed")
	public String succeed(@RequestParam("testCaseId") Long testCaseId,
			@RequestParam("testSuiteId") Long testSuiteId) {

		TestSuite testSuite = this.testSuiteService.getTestSuite(testSuiteId);
		boolean b = true;
		for (TestCase testCase : testSuite.getTestCases()) {
			if (testCase.getId() == testCaseId) {
				testCase.setSuccess(true);
				testCaseService.updateTestCase(testCase);
			}
			if (!testCase.isSuccess()) {
				b = false;
			}
		}
		if (testSuite.isSuccess() != b) {
			testSuite.setSuccess(b);
			testSuiteService.updateTestSuite(testSuite);
		}
		return "redirect:testRunning.do?op=run&id=" + testSuiteId;
	}

	@RequestMapping(params = "op=fail")
	public String fail(@RequestParam("testCaseId") Long testCaseId,
			@RequestParam("testSuiteId") Long testSuiteId) {
		TestSuite testSuite = this.testSuiteService.getTestSuite(testSuiteId);
		for (TestCase testCase : testSuite.getTestCases()) {
			if (testCase.getId() == testCaseId) {
				testCase.setSuccess(false);
				testCaseService.updateTestCase(testCase);
			}
		}
		testSuite.setSuccess(false);
		testSuiteService.updateTestSuite(testSuite);
		return "redirect:testRunning.do?op=run&id=" + testSuiteId;
	}

}
