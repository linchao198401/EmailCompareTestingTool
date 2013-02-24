package org.performance.web.service;

import java.util.List;

import org.performance.web.dao.model.TemplateGroup;

public interface TemplateGroupService {

	public TemplateGroup getTemplateGroup(long id);

	public void addTemplateGroup(TemplateGroup templateGroup);

	public List<TemplateGroup> getAllTemplateGroup();

	public boolean deleteTemplateGroup(long id);

}
