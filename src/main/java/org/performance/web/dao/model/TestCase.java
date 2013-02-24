package org.performance.web.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class TestCase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 228819347073579377L;

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private boolean isSuccess;

	@OneToOne(optional = true, fetch = FetchType.LAZY)
	private TemplateGroup templateGroup;

	@OneToOne(optional = true, fetch = FetchType.LAZY)
	private Email email;

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

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public TemplateGroup getTemplateGroup() {
		return templateGroup;
	}

	public void setTemplateGroup(TemplateGroup templateGroup) {
		this.templateGroup = templateGroup;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}