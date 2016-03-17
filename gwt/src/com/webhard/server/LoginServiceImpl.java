package com.webhard.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.LoginService;
import com.webhard.server.dao.UserDao;

/**
 * The server-side implementation of the RPC service.
 */


public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	@Override
	public int login(String id,String pwd) {
	
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		int chack = userDao.loginUser(id, pwd);
		
		if(chack == 1){
			userDto = userDao.getData(id);
			
			HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		    HttpSession session = httpServletRequest.getSession(true);
		    session.setAttribute("user", userDto);
		    
		    System.out.println(((UserDto)session.getAttribute("user")).getUserId());
		    
			return chack;
			
		}else{
			return chack;
		}
	}

}
