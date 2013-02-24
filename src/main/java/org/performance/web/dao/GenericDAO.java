package org.performance.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.performance.web.common.GenericFilter;
import org.performance.web.common.PageResult;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
public interface GenericDAO<T extends Serializable> {

	public T findById(Serializable id);

	public List<T> findAll();

	public void save(T entity);

	public void saveOrUpdate(T entity);

	public void update(T entity);

	public void delete(T entity);

	public void deleteById(Serializable id);

	public PageResult<T> findPageResult(List<GenericFilter> filters);
	
	public int runHQL(String hql, Object... values);
	
	public List<T> find(String hql, Object... values);
	
	public Query createQuery(String hql);
}
