package org.performance.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.performance.web.dao.model.Template;
import org.performance.web.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("template.do")
public class TemplateController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private TemplateService templateService;

	private String backToHome = "redirect:template.do?op=list";

	@Autowired
	public void setApplicationService(TemplateService templateService) {
		this.templateService = templateService;
	}

	@RequestMapping(params = "op=list")
	public String getTemplateList(Model model) {

		List<Template> allTemplate = templateService.getAllTemplate();
		Map<String, List<Template>> categoriedTemplates = new HashMap<String, List<Template>>();
		for (Template template : allTemplate) {
			String type = template.getType();
			List<Template> templates = categoriedTemplates.get(type);
			if (templates == null) {
				templates = new ArrayList<Template>();
				categoriedTemplates.put(type, templates);
			}
			templates.add(template);
		}
		model.addAttribute("categoriedTemplates", categoriedTemplates);
		return "templateList";
	}

	@RequestMapping(params = "op=add")
	public String addTemplate(HttpSession session, @ModelAttribute Template template, Model model) {
		if (template.getName() == null) {
			return "templateAdd";
		}
		templateService.addTemplate(template);
		return backToHome;
	}

	@RequestMapping(params = "op=update")
	public String updateTemplate(HttpSession session, @ModelAttribute Template template, Model model, @RequestParam(required = false, value = "testCaseID") Long testCaseID) {
		templateService.updateTemplate(template);
		if (testCaseID != null) {
			return "redirect:compare.do?op=comapre&testCaseId=" + testCaseID;
		}
		return backToHome;
	}

	@RequestMapping(params = "op=delete")
	public String deleteTemplte(@RequestParam("templateID") int templateID) {
		templateService.deleteTemplate(templateID);
		return backToHome;
	}

	@RequestMapping(params = "op=show")
	public String showTemplte(Model model, @RequestParam("templateID") int templateID, @RequestParam(required = false, value = "testCaseID") Long testCaseID) {
		Template template = templateService.getTemplate(templateID);
		model.addAttribute("template", template);
		model.addAttribute("testCaseID", testCaseID);
		return "templateShow";
	}
}
