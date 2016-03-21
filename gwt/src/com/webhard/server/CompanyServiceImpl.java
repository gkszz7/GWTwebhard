package com.webhard.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.service.CompanyService;
import com.webhard.server.dao.CompanyDao;


/**
 * The server-side implementation of the RPC service.
 */

public class CompanyServiceImpl extends RemoteServiceServlet implements CompanyService {
	
	@Override
	public boolean compNameCheck(String name) {
		boolean checkId = false;
		boolean check = false;
		CompanyDao cDao = new CompanyDao();
		
		checkId = cDao.checkCompanyName(name);
		if(checkId == true){
			check = true;
		}else{
			check = false;
		}
		return check;
	}
	
	@Override
	public void updateCompany(String basicName, String name, String phone, String addr) {
		CompanyDao cDao = new CompanyDao();
		CompanyDto cDto = cDao.getCompanyDtoByName(basicName);
		cDao.updateCompany(cDto.getCompanyNum(), name, addr, phone);
	}
	
}
