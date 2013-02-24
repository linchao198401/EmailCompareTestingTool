package org.performance.web.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
@Entity
public class Template implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 228819347073579377L;

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private String type;

	@Column(length = 5000)
	private String content;

	@Column(length = 15000)
	private String en;

	@Column(length = 15000)
	private String fr;

	@Column(length = 15000)
	private String de;

	/**
	 * Such as generic, accor, hilton
	 */
	private String domain;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}
}