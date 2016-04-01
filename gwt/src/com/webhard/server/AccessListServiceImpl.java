package com.webhard.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.AccessListService;
import com.webhard.server.dao.FolderDao;
import com.webhard.server.dao.UserDao;

public class AccessListServiceImpl extends RemoteServiceServlet implements AccessListService{

	@Override
	public List<UserDto> searchUserByName(String name) {
		
		List<UserDto> searchName = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		searchName = Dao.searchAccessByUserName(name);
		
		return searchName;
	}

	@Override
	public List<UserDto> searchUserBycompany(String company) {
		
		List<UserDto> searchcompany = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		searchcompany = Dao.searchAccessByCompany(company);
		
		return searchcompany;
		
	}

	@Override
	public List<UserDto> searchUserByPhone(String phone) {
		
		List<UserDto> searchphone = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		searchphone = Dao.searchAccessByPhone(phone);
		
		return searchphone;
	}

	@Override
	public List<UserDto> searchUserById(String id) {
		
		List<UserDto> searchId = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		searchId = Dao.searchAccessByUserId(id);
		
		return searchId;
	}

	@Override
	public List<UserDto> accessUser(String id) {
		
		List<UserDto> AccessList = new ArrayList<UserDto>();
		UserDao uDao = new UserDao();
		
		uDao.permitAccessUser(id);
		AccessList = uDao.selectAccessUser();
		
		return AccessList;
	}
	@Override
	public HashMap<String, Object> goMain() throws IllegalArgumentException {
		UserDao userDao = new UserDao();
		FolderDao folDao = new FolderDao();
		UserDto userDto = new UserDto();
		
		int homeNum = folDao.selectHomeFolder().getItemNum();
		String companyName = null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
	    
		int compNum = userDao.selectcompany(userDto.getUserId());
		companyName = userDao.selectcompanyname(compNum);
		String homeFolderNum = Integer.toString(homeNum);
		
		map.put("companyName", companyName);
	    map.put("homeFolderNum", homeFolderNum);
		return map;
	}
	@Override
	public UserDto getUser() {
		UserDto userDto = new UserDto();
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    
	    userDto = (UserDto)session.getAttribute("user");
		return userDto;
	}
}
