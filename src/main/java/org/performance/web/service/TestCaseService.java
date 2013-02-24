package org.performance.web.service;

import org.performance.web.common.PageResult;
import org.performance.web.dao.model.TestCase;

public interface TestCaseService {

    public void addTestCase(TestCase testCase);

    public void updateTestCase(TestCase testCase);

    public TestCase getTestCase(long id);

    public void deleteTestCase(long id);

    public PageResult<TestCase> findTestCase(TestCase testCase, int maxCountPerPage, int currentPage);

}
