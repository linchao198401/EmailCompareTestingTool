package org.performance.web.dao.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
@Entity
public class TemplateSequence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 228819347073579377L;

	@Id
	@GeneratedValue
	private long id;

	private int sequence;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "template_id")
	private Template template;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "templateGroup_id")
	private TemplateGroup templateGroup;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public TemplateGroup getTemplateGroup() {
		return templateGroup;
	}

	public void setTemplateGroup(TemplateGroup templateGroup) {
		this.templateGroup = templateGroup;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}