package org.performance.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.performance.web.common.Constance;
import org.performance.web.common.GenericFilter;
import org.performance.web.common.GenericFilter.Operation;
import org.performance.web.common.PageResult;
import org.performance.web.dao.EmailDAO;
import org.performance.web.dao.TemplateGroupDAO;
import org.performance.web.dao.TestCaseDAO;
import org.performance.web.dao.model.Email;
import org.performance.web.dao.model.TemplateGroup;
import org.performance.web.dao.model.TestCase;
import org.performance.web.service.BaseService;
import org.performance.web.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Throwable.class })
public class TestCaseServiceImpl extends BaseService implements TestCaseService {

    private TestCaseDAO testCaseDAO;

    private EmailDAO emailDAO;

    private TemplateGroupDAO templateGroupDAO;

    @Autowired
    public void setEmailDAO(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    @Autowired
    public void setTemplateGroupDAO(TemplateGroupDAO templateGroupDAO) {
        this.templateGroupDAO = templateGroupDAO;
    }

    @Autowired
    public void setTestCaseDAO(TestCaseDAO testCaseDAO) {
        this.testCaseDAO = testCaseDAO;
    }

    @Override
    public void addTestCase(TestCase testCase) {
        Email email = testCase.getEmail();
        if (email != null) {
            email = emailDAO.findById(email.getId());
            testCase.setEmail(email);
        }
        TemplateGroup group = testCase.getTemplateGroup();
        if (group != null) {
            group = templateGroupDAO.findById(group.getId());
            testCase.setTemplateGroup(group);
        }
        testCaseDAO.save(testCase);
    }

    @Override
    @Transactional(readOnly = true)
    public TestCase getTestCase(long id) {
        TestCase testCase = testCaseDAO.findById(id);
        logger.debug(testCase.toString());
        return testCase;
    }

    @Override
    public void deleteTestCase(long id) {
        testCaseDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<TestCase> findTestCase(TestCase testCase, int maxCountPerPage, int currentPage) {
        List<GenericFilter> filters = new ArrayList<GenericFilter>();
        if (currentPage < 1) {
            currentPage = 1;
        }
        filters.add(new GenericFilter(GenericFilter.CURRENT_PAGE, currentPage));
        if (maxCountPerPage < 1) {
            maxCountPerPage = Constance.DEFAULT_MAX_COUNT_PER_PAGE;
        }
        filters.add(new GenericFilter(GenericFilter.MAX_COUNT_PER_PAGE, maxCountPerPage));

        String name = null;
        if (testCase != null) {
            name = testCase.getName();
        }
        if (StringUtils.hasText(name)) {
            filters.add(new GenericFilter("name", name + "%", Operation.LIKE));
        }
        PageResult<TestCase> pageResult = testCaseDAO.findPageResult(filters);
        logger.debug(pageResult.getResult().toString());
        return pageResult;
    }

    @Override
    public void updateTestCase(TestCase testCase) {
        testCaseDAO.update(testCase);
    }
}
