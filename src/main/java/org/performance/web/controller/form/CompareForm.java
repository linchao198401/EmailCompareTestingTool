package org.performance.web.controller.form;

import java.io.Serializable;

public class CompareForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    long testCaseId;

    long testSuiteId;

    String template;

    String email;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public long getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(long testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

}
