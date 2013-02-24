package org.performance.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.performance.web.dao.ParameterDAO;
import org.performance.web.dao.model.Parameter;
import org.performance.web.service.BaseService;
import org.performance.web.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Throwable.class })
public class ParameterServiceImpl extends BaseService implements ParameterService {

	private ParameterDAO parameterDAO;

	@Autowired
	public void setParameterDAOEmailDAO(ParameterDAO paramterDAO) {
		this.parameterDAO = paramterDAO;
	}

	@Override
	public Map<String, Object> getAllParameters() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (Parameter paramter : parameterDAO.findAll()) {
			String value = paramter.getValue();
			if (value.startsWith("{")) {
				JSONObject jsonObject = JSONObject.fromObject(value);
				Object bean = JSONObject.toBean(jsonObject);
				map.put(paramter.getName(), bean);
			}
			else if (paramter.getName().toLowerCase().indexOf("date") != -1) {
				String dateString = paramter.getValue();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = simpleDateFormat.parse(dateString);
					map.put(paramter.getName(), date);
				} catch (ParseException e) {
					throw new RuntimeException("Date parse fail for " + paramter.getName());
				}
			}
			else {
				map.put(paramter.getName(), value);
			}
		}
		return map;
	}
}
