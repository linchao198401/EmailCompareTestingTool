package org.performance.web.dao.impl.release;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.model.release.Owner;
import org.performance.web.dao.release.OwnerDAO;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerDAOImpl extends AbstractHibernateDAO<Owner> implements OwnerDAO {

	public OwnerDAOImpl() {
		setClazz(Owner.class);
	}
}
