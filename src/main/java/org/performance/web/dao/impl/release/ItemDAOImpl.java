package org.performance.web.dao.impl.release;

import org.performance.web.dao.AbstractHibernateDAO;
import org.performance.web.dao.model.release.Item;
import org.performance.web.dao.release.ItemDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAOImpl extends AbstractHibernateDAO<Item> implements ItemDAO {

	public ItemDAOImpl() {
		setClazz(Item.class);
	}
}
