package org.performance.web.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
@Entity
public class Parameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 228819347073579377L;

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}