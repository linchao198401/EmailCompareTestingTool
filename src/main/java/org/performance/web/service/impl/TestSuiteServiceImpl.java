package org.performance.web.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.performance.web.common.Constance;
import org.performance.web.common.GenericFilter;
import org.performance.web.common.PageResult;
import org.performance.web.dao.TestCaseDAO;
import org.performance.web.dao.TestSuiteDAO;
import org.performance.web.dao.model.TestCase;
import org.performance.web.dao.model.TestSuite;
import org.performance.web.service.BaseService;
import org.performance.web.service.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Throwable.class })
public class TestSuiteServiceImpl extends BaseService implements TestSuiteService {

    private TestSuiteDAO testSuiteDAO;

    private TestCaseDAO testCaseDAO;

    @Autowired
    public void setTestSuiteDAO(TestSuiteDAO testSuiteDAO) {
        this.testSuiteDAO = testSuiteDAO;
    }

    @Autowired
    public void setTestCaseDAO(TestCaseDAO testCaseDAO) {
        this.testCaseDAO = testCaseDAO;
    }

    @Override
    public TestSuite getTestSuite(long id) {
        TestSuite testSuite = testSuiteDAO.findById(id);
        logger.debug(testSuite.toString());
        return testSuite;
    }

    @Override
    public void addTestSuite(TestSuite testSuite) {
        Set<TestCase> testCases = testSuite.getTestCases();
        if (testCases != null && !testCases.isEmpty()) {
            Set<TestCase> ts = new HashSet<TestCase>();
            for (TestCase t : testCases) {
                TestCase e = testCaseDAO.findById(t.getId());
                ts.add(e);
            }
            testSuite.setTestCases(ts);
        }
        testSuiteDAO.save(testSuite);
    }

    @Override
    public void deleteTestSuite(long id) {
        testSuiteDAO.deleteById(id);
    }

    @Override
    public PageResult<TestSuite> findTestSuite(TestSuite testSuite, int maxCountPerPage, int currentPage) {
        List<GenericFilter> filters = new ArrayList<GenericFilter>();
        if (currentPage < 1) {
            currentPage = 1;
        }
        filters.add(new GenericFilter(GenericFilter.CURRENT_PAGE, currentPage));
        if (maxCountPerPage < 1) {
            maxCountPerPage = Constance.DEFAULT_MAX_COUNT_PER_PAGE;
        }
        filters.add(new GenericFilter(GenericFilter.MAX_COUNT_PER_PAGE, maxCountPerPage));
        PageResult<TestSuite> page = testSuiteDAO.findPageResult(filters);
        logger.debug(page.getResult().toString());
        return page;
    }

    @Override
    public void updateTestSuite(TestSuite testSuite) {
        testSuiteDAO.update(testSuite);
    }

	@Override
	public void startTestSuite(long id) {
		String hql = "update TestSuite set isStart = true WHERE id = " + id;
		Query createQuery = testSuiteDAO.createQuery(hql);
		createQuery.executeUpdate();
	}

	@Override
	public void completeTestSuite(long id) {
		String hql = "update TestSuite set isComplete = true WHERE id = " + id;
		Query createQuery = testSuiteDAO.createQuery(hql);
		createQuery.executeUpdate();
	}

}
