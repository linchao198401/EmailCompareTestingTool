package org.performance.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.performance.web.common.Constance;
import org.performance.web.common.PageResult;
import org.performance.web.dao.model.TestCase;
import org.performance.web.dao.model.TestSuite;
import org.performance.web.service.TestCaseService;
import org.performance.web.service.TestSuiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("testSuite.do")
public class TestSuiteController {

	protected static final transient Logger logger = LoggerFactory.getLogger(TestSuiteController.class);

	private TestSuiteService testSuiteService;

	private TestCaseService testCaseService;

	@Autowired
	public void setTestCaseService(TestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}

	@Autowired
	public void setTestSuiteService(TestSuiteService testSuiteService) {
		this.testSuiteService = testSuiteService;
	}

	@RequestMapping
	public String entry(@RequestParam(required = false, value = "id") Long id, Model model) {
		if (id != null && id > 0) {
			TestSuite testSuite = testSuiteService.getTestSuite(id);
			if (testSuite != null) {
				model.addAttribute("testSuite", testSuite);
			}
			return "testSuiteShow";
		}
		PageResult<TestCase> page = testCaseService.findTestCase(null, Integer.MAX_VALUE, 1);
		model.addAttribute("testCases", page.getResult());
		return "testSuite";
	}

	@RequestMapping(params = "op=addTestSuite")
	public String addtestSuite(@ModelAttribute TestSuite testSuite, @RequestParam(value = "items", required = false) List<Long> ids, Model model) {
		if (ids != null && !ids.isEmpty()) {
			Set<TestCase> testCases = new HashSet<TestCase>();
			for (long id : ids) {
				TestCase t = new TestCase();
				t.setId(id);
				testCases.add(t);
			}
			testSuite.setTestCases(testCases);
		}
		testSuiteService.addTestSuite(testSuite);
		return "redirect:testSuite.do?op=list";
	}

	@RequestMapping(params = "op=list")
	public String listTestSuite(@RequestParam(defaultValue = "1", value = "currentPage") Integer currentPage,
			@RequestParam(required = false, value = "maxCountPerPage") Integer maxCountPerPage, @ModelAttribute TestSuite testSuite, Model model) {

		if (currentPage == null || currentPage < 1) {
			currentPage = 1;
		}

		if (maxCountPerPage == null || maxCountPerPage < 1) {
			maxCountPerPage = Constance.DEFAULT_MAX_COUNT_PER_PAGE;
		}

		PageResult<TestSuite> page = testSuiteService.findTestSuite(testSuite, maxCountPerPage, currentPage);
		System.out.println(page.getResult());
		model.addAttribute("page", page);
		return "testSuiteList";
	}

	@RequestMapping(params = "op=deleteTestSuite")
	public String deleteTestSuite(@RequestParam(required = false, value = "id") Long id) {
		if (id != null && id > 0) {
			this.testSuiteService.deleteTestSuite(id);
		}
		return "redirect:testRunning.do?op=list";
	}

	@RequestMapping(params = "op=markTestSuiteComplete")
	public String markTestSuiteComplete(@RequestParam(required = false, value = "id") Long id) {
		if (id != null && id > 0) {
			testSuiteService.completeTestSuite(id);
		}
		return "redirect:testRunning.do?op=list";
	}

}
