package org.performance.web.dao.impl;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.TemplateGroupDAO;
import org.performance.web.dao.model.TemplateGroup;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateGroupDAOImpl extends AbstractHibernateDAO<TemplateGroup> implements TemplateGroupDAO {

    public TemplateGroupDAOImpl() {
        setClazz(TemplateGroup.class);
    }
}
