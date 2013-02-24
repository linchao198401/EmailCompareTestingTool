package org.performance.web.controller;

import java.util.Map;
import java.util.Set;

import org.performance.web.common.util.FreeMarkerUtil;
import org.performance.web.controller.form.CompareForm;
import org.performance.web.dao.model.Email;
import org.performance.web.dao.model.TemplateGroup;
import org.performance.web.dao.model.TemplateSequence;
import org.performance.web.dao.model.TestCase;
import org.performance.web.service.EmailService;
import org.performance.web.service.ParameterService;
import org.performance.web.service.TemplateGroupService;
import org.performance.web.service.TemplateService;
import org.performance.web.service.TestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("compare.do")
public class CompareController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private TemplateService templateService;
    private TemplateGroupService templateGroupService;
    private ParameterService parameterService;
    private EmailService emailService;
    private TestCaseService testCaseService;

    private final String backToHome = "redirect:template.do?op=list";

    @Autowired
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    @Autowired
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Autowired
    public void setTemplateGroupService(TemplateGroupService templateGroupService) {
        this.templateGroupService = templateGroupService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setTestCaseService(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @RequestMapping(params = "op=showTemplateGroup")
    public String showTemplateGroup(Model model, @RequestParam("templateGroupID") Long templateGroupID) {
        TemplateGroup templateGroup = templateGroupService.getTemplateGroup(templateGroupID);
        Set<TemplateSequence> sequences = templateGroup.getTemplateSequences();
        String[] contents = new String[sequences.size()];
        for (TemplateSequence sequence : sequences) {
            int order = sequence.getSequence();
            contents[order - 1] = sequence.getTemplate().getContent();
        }
        StringBuilder all = new StringBuilder();
        for (String part : contents) {
            all.append(part);
        }

        Map<String, Object> map = parameterService.getAllParameters();
        model.addAttribute("all", FreeMarkerUtil.filterByFreeMarker(all.toString(), map));
        return "templateGroupShow";
    }

    @RequestMapping(params = "op=showEmail")
    public String showEmail(Model model, @RequestParam("emailID") Long emailID) {
        Email email = emailService.getEmail(emailID);
        String content = email.getContent();
        model.addAttribute("email", content);
        return "emailShow";
    }

    @RequestMapping(params = "op=comapre")
    public String comapre(Model model, @RequestParam("testCaseId") Long testCaseId) {

        TestCase testCase = testCaseService.getTestCase(testCaseId);

        long templateGroupID = testCase.getTemplateGroup().getId();
        TemplateGroup templateGroup = templateGroupService.getTemplateGroup(templateGroupID);
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
        compareForm.setTemplate(FreeMarkerUtil.filterByFreeMarker(all.toString(), map));

        long emailID = testCase.getEmail().getId();
        Email email = emailService.getEmail(emailID);
        String content = email.getContent();
        compareForm.setEmail(content);
        model.addAttribute("form", compareForm);
        model.addAttribute("testCaseID", testCase.getId());
        model.addAttribute("emailID", email.getId());

        return "compare";
    }

}
