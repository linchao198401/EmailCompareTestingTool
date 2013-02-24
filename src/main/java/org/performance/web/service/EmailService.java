package org.performance.web.service;

import org.performance.web.common.PageResult;
import org.performance.web.dao.model.Email;

public interface EmailService {

    public Email getEmail(long id);

    public void addEmail(Email email);

    public void updateEmail(Email email);

    public void deleteEmail(long id);

    public PageResult<Email> findEmail(Email email, int maxCountPerPage, int currentPage);

}
