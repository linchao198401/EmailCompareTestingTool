package org.performance.web.dao.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
@Entity
public class TemplateGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 228819347073579377L;

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private String domain;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE }, fetch = FetchType.EAGER, mappedBy = "templateGroup")
	@OrderBy("id")
	private Set<TemplateSequence> templateSequences;

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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Set<TemplateSequence> getTemplateSequences() {
		return templateSequences;
	}

	public void setTemplateSequences(Set<TemplateSequence> templateSequences) {
		this.templateSequences = templateSequences;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}