package org.performance.web.service.impl;

import java.util.List;
import java.util.Set;

import org.performance.web.dao.TemplateDAO;
import org.performance.web.dao.TemplateGroupDAO;
import org.performance.web.dao.model.Template;
import org.performance.web.dao.model.TemplateGroup;
import org.performance.web.dao.model.TemplateSequence;
import org.performance.web.service.BaseService;
import org.performance.web.service.TemplateGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Throwable.class })
public class TemplateGroupServiceImpl extends BaseService implements TemplateGroupService {

    private TemplateGroupDAO templateGroupDAO;
    private TemplateDAO templateDAO;

    @Autowired
    public void setTemplateGroupDAO(TemplateGroupDAO templateGroupDAO) {
        this.templateGroupDAO = templateGroupDAO;
    }

    @Autowired
    public void setTemplateDAO(TemplateDAO templateDAO) {
        this.templateDAO = templateDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public TemplateGroup getTemplateGroup(long id) {
        return templateGroupDAO.findById(id);
    }

    @Override
    public void addTemplateGroup(TemplateGroup templateGroup) {
        Set<TemplateSequence> sequences = templateGroup.getTemplateSequences();
        for (TemplateSequence sequence : sequences) {
            long templateID = sequence.getTemplate().getId();
            // update the template with the real object
            if (templateID > 0) {
                Template template = templateDAO.findById(templateID);
                sequence.setTemplate(template);
            }
        }
        templateGroupDAO.save(templateGroup);
    }

    @Override
    public List<TemplateGroup> getAllTemplateGroup() {
        return templateGroupDAO.findAll();
    }

    @Override
    public boolean deleteTemplateGroup(long id) {
        TemplateGroup templateGroup = templateGroupDAO.findById(id);
        templateGroupDAO.delete(templateGroup);
        return true;
    }

}
