package org.performance.web.service;

import org.performance.web.dao.model.Application;

public interface ApplicationService {

	public boolean addApplication(Application app);

	public boolean updateApplication(Application app);

	public Application getApplication(long id);

}
