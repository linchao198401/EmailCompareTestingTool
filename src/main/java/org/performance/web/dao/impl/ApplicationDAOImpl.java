package org.performance.web.dao.impl;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.ApplicationDAO;
import org.performance.web.dao.model.Application;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
@Repository
public class ApplicationDAOImpl extends AbstractHibernateDAO<Application> implements ApplicationDAO {

    public ApplicationDAOImpl() {
        setClazz(Application.class);
    }
}
