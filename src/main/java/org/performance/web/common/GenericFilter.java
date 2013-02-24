package org.performance.web.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
public class GenericFilter implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 6569795054527041603L;

	public static final String CURRENT_PAGE = "currentPage";

	public static final String MAX_COUNT_PER_PAGE = "maxCountPerPage";

	private String name;

	private List<Object> value;

	private Operation operation;

	public GenericFilter(String name, Object object) {
		this(name, object, Operation.EQUAL);
	}

	public static List<GenericFilter> getSimpleGenericFilter(Map<String, Object> paras) {
		ArrayList<GenericFilter> filters = new ArrayList<GenericFilter>();
		for (Map.Entry<String, Object> para : paras.entrySet()) {
			GenericFilter filter = new GenericFilter(para.getKey(), para.getValue());
			filters.add(filter);
		}
		GenericFilter page = new GenericFilter(GenericFilter.CURRENT_PAGE, 1);
		filters.add(page);
		GenericFilter max = new GenericFilter(GenericFilter.MAX_COUNT_PER_PAGE, Integer.MAX_VALUE);
		filters.add(max);

		return filters;
	}

	public static List<GenericFilter> getSimpleGenericFilter(String name, Object object) {
		HashMap<String, Object> paras = new HashMap<String, Object>();
		paras.put(name, object);
		return getSimpleGenericFilter(paras);
	}

	public GenericFilter(String name, Object object, Operation operation) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("name is not specified.");
		}
		this.name = name.trim();

		if (operation == null) {
			throw new IllegalArgumentException("operation is not specified.");
		}

		this.value = new ArrayList<Object>();
		if (object == null) {
			if (Operation.NOT_NULL == operation) {
				this.operation = Operation.NOT_NULL;
			}
			else {
				this.operation = Operation.IS_NULL;
			}
		}
		else if (object instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) object;
			this.value.addAll(collection);
			this.value.addAll((Collection<?>) object);
			if (collection.isEmpty()) {
				this.operation = Operation.IS_NULL;
				if (Operation.NOT_NULL == operation) {
					this.operation = Operation.NOT_NULL;
				}
				else {
					this.operation = Operation.IS_NULL;
				}
			}
			else {
				this.operation = operation;
			}
		}
		else {
			this.value.add(object);
			this.operation = operation;
		}
	}

	public List<Object> getValue() {
		return value;
	}

	public void setValue(List<Object> value) {
		this.value = value;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getObject() {
		return value.get(0);
	}

	public Object getObject(int i) {
		return value.get(i);
	}

	public int getInt() {
		return (Integer) value.get(0);
	}

	public boolean isNullValue() {
		if (value == null || value.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	public static enum Operation {
		EQUAL, GT, LT, BETWEEN, IN, GE, LE, LIKE, OR, NE, ASC, DESC, IS_NULL, NOT_NULL
	}
}
