package org.performance.web.dao.impl.release;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.model.release.ReleaseChecklist;
import org.performance.web.dao.release.ReleaseDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ReleaseDAOImpl extends AbstractHibernateDAO<ReleaseChecklist> implements ReleaseDAO {

	public ReleaseDAOImpl() {
		setClazz(ReleaseChecklist.class);
	}
}
