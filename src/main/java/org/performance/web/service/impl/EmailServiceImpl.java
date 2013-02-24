package org.performance.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.performance.web.common.Constance;
import org.performance.web.common.GenericFilter;
import org.performance.web.common.PageResult;
import org.performance.web.dao.EmailDAO;
import org.performance.web.dao.model.Email;
import org.performance.web.service.BaseService;
import org.performance.web.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Throwable.class })
public class EmailServiceImpl extends BaseService implements EmailService {

    private EmailDAO emailDAO;

    @Autowired
    public void setEmailDAO(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Email getEmail(long id) {
        return emailDAO.findById(id);
    }

    @Override
    public void addEmail(Email email) {
        emailDAO.save(email);
    }

    @Override
    public PageResult<Email> findEmail(Email email, int maxCountPerPage, int currentPage) {
        List<GenericFilter> filters = new ArrayList<GenericFilter>();
        if (currentPage < 1) {
            currentPage = 1;
        }
        filters.add(new GenericFilter(GenericFilter.CURRENT_PAGE, currentPage));
        if (maxCountPerPage < 1) {
            maxCountPerPage = Constance.DEFAULT_MAX_COUNT_PER_PAGE;
        }
        filters.add(new GenericFilter(GenericFilter.MAX_COUNT_PER_PAGE, maxCountPerPage));
        return emailDAO.findPageResult(filters);
    }

    @Override
    public void deleteEmail(long id) {
        emailDAO.deleteById(id);
    }

	@Override
	public void updateEmail(Email email) {
		emailDAO.update(email);
	}
}
