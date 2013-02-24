package org.performance.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.performance.web.common.GenericFilter;
import org.performance.web.common.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 * @param <T>
 *            the model class which is bound to table
 */
public abstract class AbstractHibernateDAO<T extends Serializable> implements GenericDAO<T> {

	protected static final String FROM = "from";

	protected static final String COUNT_PREFIX = "select count(*) ";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private Class<T> clazz;

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setClazz(Class<T> clazzToSet) {
		clazz = clazzToSet;
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return find("from " + clazz.getName());
	}

	@Override
	public void save(T entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public void update(T entity) {
		getCurrentSession().merge(entity);
	}

	@Override
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		T entity = findById(id);
		delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... values) {
		Query query = createQuery(hql);
		int i = 0;
		if (values != null) {
			for (Object value : values) {
				query.setParameter(i, value);
			}
		}
		return query.list();
	}
	
	public int runHQL(String hql, Object... values) {
		Query query = createQuery(hql);
		int i = 0;
		if (values != null) {
			for (Object value : values) {
				query.setParameter(i, value);
				i++;
			}
		}
		return query.executeUpdate();
	}

	@Override
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public Query createQuery(String hql) {
		return getCurrentSession().createQuery(hql);
	}

	public PageResult<T> findPageResult(List<GenericFilter> filters) {
		if (filters == null || filters.isEmpty()) {
			throw new IllegalArgumentException("No criterials");
		}
		int currentPage = 0;
		int maxCountPerPage = 0;
		for (GenericFilter filter : filters) {
			if (GenericFilter.CURRENT_PAGE.equals(filter.getName())) {
				currentPage = filter.getInt();
			}
			else if (GenericFilter.MAX_COUNT_PER_PAGE.equals(filter.getName())) {
				maxCountPerPage = filter.getInt();
			}
		}
		int totalResultCount = findTotalCount(filters);
		List<T> result = findPage(filters);
		return new PageResult<T>(totalResultCount, currentPage, maxCountPerPage, result);
	}

	public int findTotalCount(List<GenericFilter> filters) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		criteria.setProjection(Projections.rowCount());
		if (filters != null) {
			createCritia(criteria, filters, false);
		}
		return Integer.parseInt(criteria.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	private List<T> findPage(final List<GenericFilter> filters) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		if (filters != null) {
			createCritia(criteria, filters, true);
		}
		return criteria.list();
	}

	public PageResult<T> findPageResult(String hql, int currentPage, int maxCountPerPage, Object... values) {
		if (hql == null || hql.trim().length() == 0 || hql.indexOf(FROM) == -1 || currentPage < 1 || maxCountPerPage < 1) {
			throw new IllegalArgumentException("hql : " + hql + "\ncurrentPage : " + currentPage + "\nmaxCountPerPage : " + maxCountPerPage);
		}
		int totalResultCount = findTotalCount(hql, values);
		List<T> result = findPage(hql, currentPage, maxCountPerPage, values);
		return new PageResult<T>(totalResultCount, currentPage, maxCountPerPage, result);
	}

	private int findTotalCount(String hql, Object... values) {
		// use "select count (*) from ......" to find total count
		String countHql = COUNT_PREFIX + hql.substring(hql.indexOf(FROM));
		List<?> tmp = this.find(countHql, values);
		return Integer.parseInt(tmp.get(0).toString());
	}

	@SuppressWarnings("unchecked")
	private List<T> findPage(final String hql, final int currentPage, final int maxCountPerPage, final Object... values) {
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.setMaxResults(maxCountPerPage);
		int beginRow = getBeginRow(currentPage, maxCountPerPage);
		query.setFirstResult(beginRow);
		return query.list();
	}

	protected void createCritia(Criteria criteria, List<GenericFilter> filters, boolean isPaginated) {
		int currentPage = 0;
		int maxCountPerPage = 0;

		for (GenericFilter filter : filters) {
			String name = filter.getName();
			GenericFilter.Operation operation = filter.getOperation();
			if (GenericFilter.CURRENT_PAGE.equals(name)) {
				currentPage = filter.getInt();
			}
			else if (GenericFilter.MAX_COUNT_PER_PAGE.equals(name)) {
				maxCountPerPage = filter.getInt();
			}
			else {
				switch (operation) {
				case EQUAL:
					criteria.add(Restrictions.eq(name, filter.getObject()));
					break;
				case BETWEEN:
					criteria.add(Restrictions.between(name, filter.getObject(0), filter.getObject(1)));
					break;
				case GE:
					criteria.add(Restrictions.ge(name, filter.getObject()));
					break;
				case GT:
					criteria.add(Restrictions.gt(name, filter.getObject()));
					break;
				case IN:
					criteria.add(Restrictions.in(name, filter.getValue()));
					break;
				case LE:
					criteria.add(Restrictions.le(name, filter.getObject()));
					break;
				case LT:
					criteria.add(Restrictions.lt(name, filter.getObject()));
					break;
				case LIKE:
					criteria.add(Restrictions.like(name, filter.getObject()));
					break;
				case NE:
					criteria.add(Restrictions.ne(name, filter.getObject()));
					break;
				case OR:
					criteria.add(getOrCriterion(filter));
					break;
				case IS_NULL:
					criteria.add(Restrictions.isNull(name));
					break;
				case NOT_NULL:
					criteria.add(Restrictions.isNotNull(name));
					break;
				case ASC:
					if (isPaginated) {
						criteria.addOrder(Order.asc(name));
					}
					break;
				case DESC:
					if (isPaginated) {
						criteria.addOrder(Order.desc(name));
					}
					break;
				default:
					throw new IllegalArgumentException("Invlid operation while criteria ctreation.");
				}
			}

			if (isPaginated) {
				int beginRow = getBeginRow(currentPage, maxCountPerPage);
				criteria.setFirstResult(beginRow);
				criteria.setMaxResults(maxCountPerPage);
			}
		}
	}

	private Criterion getOrCriterion(GenericFilter filter) {
		String name = filter.getName();
		Object o1 = filter.getObject(0);
		Object o2 = filter.getObject(1);
		Criterion c1 = null;
		Criterion c2 = null;
		if (o1 == null) {
			c1 = Restrictions.isNull(name);
		}
		else {
			c1 = Restrictions.eq(name, o1);
		}
		if (o2 == null) {
			c2 = Restrictions.isNull(name);
		}
		else {
			c2 = Restrictions.eq(name, o2);
		}
		return Restrictions.or(c1, c2);
	}

	private int getBeginRow(int currentPage, int maxCountPerPage) {
		return (currentPage - 1) * maxCountPerPage;
	}

}
