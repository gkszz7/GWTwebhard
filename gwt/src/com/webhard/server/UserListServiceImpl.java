package com.webhard.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.UserListService;
import com.webhard.server.dao.UserDao;

public class UserListServiceImpl extends RemoteServiceServlet implements UserListService{

	@Override
	public void UserList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserDto> searchUserByName(String name) {
		
		List<UserDto> searchName = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		searchName = Dao.searchUserByUserName(name);
		return searchName;
	}

	@Override
	public List<UserDto> searchUserBycompany(String company) {
		
		List<UserDto> searchcompany = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		searchcompany = Dao.searchUserByCompany(company);
		return searchcompany;
		
	}

	@Override
	public List<UserDto> searchUserByPhone(String phone) {
		
		List<UserDto> searchphone = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		searchphone = Dao.searchUserByUserPhone(phone);
		
		return searchphone;
	}

	@Override
	public List<UserDto> searchUserById(String id) {
		
		List<UserDto> searchId = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		searchId = Dao.searchUserByUserId(id);
		
		return searchId;
	}

	@Override
	public List<UserDto> deleteUser(String id) {
		
		List<UserDto> users = new ArrayList<UserDto>();
		UserDao uDao = new UserDao();
		
		uDao.deleteUser(id);
		users = uDao.selectAllUser();
		
		return users;
	}
 
	@Override
	public List<UserDto> updateUser(String id,String name, String phone, String addr, String company) {

		List<UserDto> users = new ArrayList<UserDto>();
		UserDao userDao = new UserDao();
		UserDto uDto = userDao.getUserData(id);
		uDto = userDao.updateUser(id, name, addr, phone, company);
		users = userDao.selectAllUser();
		return users;
	}
}
