package org.performance.web.dao.impl.release;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.model.release.ReleaseItem;
import org.performance.web.dao.release.ReleaseItemDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ReleaseItemDAOImpl extends AbstractHibernateDAO<ReleaseItem> implements ReleaseItemDAO {

	public ReleaseItemDAOImpl() {
		setClazz(ReleaseItem.class);
	}
}
