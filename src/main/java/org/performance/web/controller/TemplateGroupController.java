package org.performance.web.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.performance.web.dao.model.Template;
import org.performance.web.dao.model.TemplateGroup;
import org.performance.web.dao.model.TemplateSequence;
import org.performance.web.form.TemplateGroupForm;
import org.performance.web.service.TemplateGroupService;
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
@RequestMapping("templateGroup.do")
public class TemplateGroupController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static String backToHome = "redirect:templateGroup.do?op=list";

	private TemplateGroupService templateGroupService;

	private TemplateService templateService;

	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	@Autowired
	public void setApplicationService(TemplateGroupService templateGroupService) {
		this.templateGroupService = templateGroupService;
	}

	@RequestMapping(params = "op=list")
	public String getTemplateList(Model model) {

		List<TemplateGroup> templateGroups = templateGroupService.getAllTemplateGroup();
		model.addAttribute("templateGroups", templateGroups);
		return "templateGroupList";
	}

	@RequestMapping(params = "op=add")
	public String addTemplate(HttpSession session, @ModelAttribute TemplateGroupForm form, Model model) {
		if (form.getName() == null) {
			List<Template> headers = templateService.getAllTemplateByType("Header");
			List<Template> bodys = templateService.getAllTemplateByType("Body");
			List<Template> footers = templateService.getAllTemplateByType("Footer");
			model.addAttribute("headers", headers);
			model.addAttribute("bodys", bodys);
			model.addAttribute("footers", footers);
			return "templateGroupAdd";
		}
		TemplateGroup templateGroup = new TemplateGroup();
		templateGroup.setName(form.getName());
		templateGroup.setDomain(form.getDomain());
		HashSet<TemplateSequence> sequences = new HashSet<TemplateSequence>();
		templateGroup.setTemplateSequences(sequences);
		int i = 1;
		for (int templateID : form.getTemplateIDs()) {
			TemplateSequence sequence = new TemplateSequence();
			sequence.setSequence(i);
			i++;
			Template template = new Template();
			template.setId(templateID);
			sequence.setTemplate(template);
			sequence.setTemplateGroup(templateGroup);
			sequences.add(sequence);
		}
		templateGroupService.addTemplateGroup(templateGroup);
		return backToHome;
	}

	@RequestMapping(params = "op=delete")
	public String deleteTemplteGroup(@RequestParam("templateGroupID") int templateGroupID) {
		templateGroupService.deleteTemplateGroup(templateGroupID);
		return backToHome;
	}

	@RequestMapping(params = "op=setGroupName")
	public String setTemplateGroupName(Model model, @RequestParam(value = "headerID", required = false) Integer headerID,
			@RequestParam(value = "bodyID", required = false) Integer bodyID, @RequestParam(value = "footerID", required = false) Integer footerID) {
		model.addAttribute("headerID", headerID);
		model.addAttribute("bodyID", bodyID);
		model.addAttribute("footerID", footerID);
		if (headerID != null) {
			model.addAttribute("tgHeader", templateService.getTemplate(headerID));
		}
		if (bodyID != null) {
			model.addAttribute("tgBody", templateService.getTemplate(bodyID));
		}
		if (footerID != null) {
			model.addAttribute("tgFooter", templateService.getTemplate(footerID));
		}

		return "templateGroupSetName";
	}
}
