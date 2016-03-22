package com.webhard.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.MainService;
import com.webhard.server.dao.CompanyDao;
import com.webhard.server.dao.UserDao;

public class MainServiceImpl extends RemoteServiceServlet implements MainService{
	
	@Override
	public List<CompanyDto> compList() {
		CompanyDao compDao = new CompanyDao();
		List<CompanyDto> compList = new ArrayList<CompanyDto>();
		compList = compDao.selectCompany();
		return compList;
	}

	@Override
	public List<UserDto> UserList() {
		List<UserDto> UserList = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		UserList = Dao.selectAllUser();
		return UserList;
	}
	
	@Override
	public List<UserDto> AccessList() {
		List<UserDto> Accesslist = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		Accesslist = Dao.selectAccessUser();
		return Accesslist;
	}
	
}
