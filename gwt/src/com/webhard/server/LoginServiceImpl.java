package com.webhard.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.LoginService;
import com.webhard.server.dao.CompanyDao;
import com.webhard.server.dao.FolderDao;
import com.webhard.server.dao.UserDao;

/**
 * The server-side implementation of the RPC service.
 */


public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	@Override
	public int login(String id,String pwd,List<FolderDto> folderList,FolderDto homefolder, List<CompanyDto> compList) {
	
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		int chack = userDao.loginUser(id, pwd);
		
		if(chack == 1){
			userDto = userDao.getData(id);
			
			HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		    HttpSession session = httpServletRequest.getSession(true);
		    session.setAttribute("user", userDto);
		    
			return chack;
			
		}else if(chack == 0){
			return chack;
		}else{
			return chack;
		}
	}
	@Override
	public List<CompanyDto> comboList() {
		List<CompanyDto> comList = new ArrayList<CompanyDto>();
		CompanyDao dao = new CompanyDao();
		comList = dao.selectCompany();
		
		return comList;
	}
	
	@Override
	public List<FolderDto> folderList() {
		List<FolderDto> folderList = new ArrayList<FolderDto>();
		FolderDao dao = new FolderDao();
		folderList = dao.selectFolderTree();
		return folderList;
	}
	@Override
	public List<UserDto> userList() {
		List<UserDto> userList = new ArrayList<UserDto>();
		UserDao dao = new UserDao();
		userList = dao.selectAllUser();
		return userList;
	}
	
	@Override
	public FolderDto homefolder() {
		FolderDto homeFolder = new FolderDto();
		FolderDao folderDao = new FolderDao();
		homeFolder = folderDao.selectHomeFolder();
		return homeFolder;
	}

}
