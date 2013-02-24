package org.performance.web.dao.impl;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.EmailDAO;
import org.performance.web.dao.model.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDAOImpl extends AbstractHibernateDAO<Email> implements EmailDAO {

    public EmailDAOImpl() {
        setClazz(Email.class);
    }

}
