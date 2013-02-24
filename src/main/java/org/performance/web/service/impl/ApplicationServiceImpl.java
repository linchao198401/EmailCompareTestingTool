package org.performance.web.service.impl;

import org.performance.web.dao.ApplicationDAO;
import org.performance.web.dao.model.Application;
import org.performance.web.service.ApplicationService;
import org.performance.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Throwable.class })
public class ApplicationServiceImpl extends BaseService implements ApplicationService {

    private ApplicationDAO applicationDAO;

    @Autowired
    public void setApplicationDAO(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public boolean addApplication(Application app) {
        applicationDAO.save(app);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Application getApplication(long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("try to get Application, id is: " + id);
        }
        return applicationDAO.findById(id);
    }

    @Override
    public boolean updateApplication(Application app) {
        if (logger.isDebugEnabled()) {
            logger.debug("try to update Application, id is: " + app.getId());
        }
        applicationDAO.update(app);
        return true;
    }

}
