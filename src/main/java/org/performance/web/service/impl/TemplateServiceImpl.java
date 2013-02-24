package org.performance.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.performance.web.common.GenericFilter;
import org.performance.web.dao.TemplateDAO;
import org.performance.web.dao.model.Template;
import org.performance.web.service.BaseService;
import org.performance.web.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Throwable.class })
public class TemplateServiceImpl extends BaseService implements TemplateService {

	private TemplateDAO templateDAO;

	@Autowired
	public void setTemplateDAO(TemplateDAO templateDAO) {
		this.templateDAO = templateDAO;
	}

	@Override
	public boolean addTemplate(Template template) {
		templateDAO.save(template);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Template getTemplate(long id) {
		if (logger.isDebugEnabled()) {
			logger.debug("try to get Template, id is: " + id);
		}
		return templateDAO.findById(id);
	}

	@Override
	public boolean updateTemplate(Template app) {
		if (logger.isDebugEnabled()) {
			logger.debug("try to update Template, id is: " + app.getId());
		}
		templateDAO.update(app);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Template> getAllTemplate() {
		if (logger.isDebugEnabled()) {
			logger.debug("try to get all Template");
		}
		return templateDAO.findAll();
	}

	@Override
	public boolean deleteTemplate(long id) {
		templateDAO.deleteById(id);
		return true;
	}

	@Override
	public List<Template> getAllTemplateByType(String type) {
		ArrayList<GenericFilter> filters = new ArrayList<GenericFilter>();
		GenericFilter filter = new GenericFilter("type", type);
		filters.add(filter);
		GenericFilter page = new GenericFilter(GenericFilter.CURRENT_PAGE, 1);
		filters.add(page);
		GenericFilter max = new GenericFilter(GenericFilter.MAX_COUNT_PER_PAGE, Integer.MAX_VALUE);
		filters.add(max);
		return templateDAO.findPageResult(filters).getResult();
	}

}
