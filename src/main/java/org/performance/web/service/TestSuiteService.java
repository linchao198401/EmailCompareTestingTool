package org.performance.web.service;

import org.performance.web.common.PageResult;
import org.performance.web.dao.model.TestSuite;

public interface TestSuiteService {

	public TestSuite getTestSuite(long id);

	public void addTestSuite(TestSuite testSuite);

	public void updateTestSuite(TestSuite testSuite);

	public void startTestSuite(long id);

	public void completeTestSuite(long id);

	public void deleteTestSuite(long id);

	public PageResult<TestSuite> findTestSuite(TestSuite testSuite,
			int maxCountPerPage, int currentPage);

}
