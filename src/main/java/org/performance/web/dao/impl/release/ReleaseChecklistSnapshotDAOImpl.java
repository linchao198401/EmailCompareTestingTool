package org.performance.web.dao.impl.release;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.model.release.ReleaseChecklistSnapshot;
import org.performance.web.dao.model.release.ReleaseItem;
import org.performance.web.dao.release.ReleaseChecklistSnapshotDAO;
import org.performance.web.dao.release.ReleaseItemDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ReleaseChecklistSnapshotDAOImpl extends AbstractHibernateDAO<ReleaseChecklistSnapshot> implements ReleaseChecklistSnapshotDAO {

	public ReleaseChecklistSnapshotDAOImpl() {
		setClazz(ReleaseChecklistSnapshot.class);
	}
}
