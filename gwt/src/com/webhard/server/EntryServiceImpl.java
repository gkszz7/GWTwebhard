package com.webhard.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.service.EntryService;
import com.webhard.server.dao.CompanyDao;
import com.webhard.server.dao.UserDao;

/**
 * The server-side implementation of the RPC service.
 */

public class EntryServiceImpl extends RemoteServiceServlet implements EntryService {

	@Override
	public boolean IdCheck(String id) {
		     
		boolean checkId = false;
		boolean check = false;
		UserDao uDao = new UserDao();
		
		checkId = uDao.checkUserId(id);
		if(checkId == true){
			check = true;
		}else{
			check = false;
		}
		
		
		return check;
	}
	
	@Override
	public void entryUser(String id, String pw, String name, String phone,
			String addr, String company) {
		
		UserDao uDao = new UserDao();
		CompanyDao cDao = new CompanyDao();
		int comNum = cDao.selectCompanyNum(company);
		uDao.entryNewUser(id, pw, name, phone, addr, comNum);
	}
	
}
