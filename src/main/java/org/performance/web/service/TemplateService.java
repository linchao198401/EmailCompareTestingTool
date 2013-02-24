package org.performance.web.service;

import java.util.List;

import org.performance.web.dao.model.Template;

public interface TemplateService {

	public boolean addTemplate(Template tmplate);

	public boolean updateTemplate(Template template);

	public Template getTemplate(long id);

	public List<Template> getAllTemplate();

	public boolean deleteTemplate(long id);

	public List<Template> getAllTemplateByType(String type);
}
