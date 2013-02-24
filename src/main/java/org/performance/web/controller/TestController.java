package org.performance.web.controller;

import org.performance.web.dao.model.Application;
import org.performance.web.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Vampiredx
 * 
 */
@Controller
@RequestMapping("test.do")
public class TestController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationService applicationService;

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping(params = "op=test")
    public String doTest(Model model, @RequestParam("s") String s) {
        Application app = applicationService.getApplication(1);
        if (app != null) {
            app.setCode("001");
            app.setName(s);
            applicationService.updateApplication(app);
        } else {
            app = new Application();
            app.setId(1);
            app.setCode("001");
            app.setName(s);
            applicationService.addApplication(app);
        }
        app = applicationService.getApplication(1);
        model.addAttribute("app", app);
        return "test";
    }

    @RequestMapping(params = "op=hello")
    public String doHello(Model model) {
        model.addAttribute("s", "hello");
        return "test";
    }

}
