package org.performance.web.dao.impl;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.TemplateDAO;
import org.performance.web.dao.model.Template;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateDAOImpl extends AbstractHibernateDAO<Template> implements TemplateDAO {

	public TemplateDAOImpl() {
		setClazz(Template.class);
	}
}
