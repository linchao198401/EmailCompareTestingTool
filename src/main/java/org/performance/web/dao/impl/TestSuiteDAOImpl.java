package org.performance.web.dao.impl;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.TestSuiteDAO;
import org.performance.web.dao.model.TestSuite;
import org.springframework.stereotype.Repository;

@Repository
public class TestSuiteDAOImpl extends AbstractHibernateDAO<TestSuite> implements TestSuiteDAO {

    public TestSuiteDAOImpl() {
        setClazz(TestSuite.class);
    }

}
