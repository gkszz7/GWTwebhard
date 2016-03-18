package com.webhard.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.service.MainService;
import com.webhard.server.dao.CompanyDao;

public class MainServiceImpl extends RemoteServiceServlet implements MainService{
	
	@Override
	public List<CompanyDto> compList() {
		CompanyDao compDao = new CompanyDao();
		List<CompanyDto> compList = new ArrayList<CompanyDto>();
		compList = compDao.selectCompany();
		return compList;
	}

}
