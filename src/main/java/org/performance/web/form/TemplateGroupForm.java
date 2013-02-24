package org.performance.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TemplateGroupForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String domain;

	private String name;

	private List<Integer> templateIDs;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getTemplateIDs() {
		ArrayList<Integer> templateIDsNotNull = new ArrayList<Integer>();
		for (Integer templateID : templateIDs) {
			if (templateID != null) {
				templateIDsNotNull.add(templateID);
			}
		}
		return templateIDsNotNull;
	}

	public void setTemplateIDs(List<Integer> templateIDs) {
		this.templateIDs = templateIDs;

	}
}
