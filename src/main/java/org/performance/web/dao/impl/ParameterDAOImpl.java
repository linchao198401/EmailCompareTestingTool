package org.performance.web.dao.impl;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.ParameterDAO;
import org.performance.web.dao.model.Parameter;
import org.springframework.stereotype.Repository;

@Repository
public class ParameterDAOImpl extends AbstractHibernateDAO<Parameter> implements ParameterDAO {

	public ParameterDAOImpl() {
		setClazz(Parameter.class);
	}

}
