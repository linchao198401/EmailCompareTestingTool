package org.performance.web.dao.impl;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.TestCaseDAO;
import org.performance.web.dao.model.TestCase;
import org.springframework.stereotype.Repository;

@Repository
public class TestCaseDAOImpl extends AbstractHibernateDAO<TestCase> implements TestCaseDAO {
    public TestCaseDAOImpl() {
        setClazz(TestCase.class);
    }
}
