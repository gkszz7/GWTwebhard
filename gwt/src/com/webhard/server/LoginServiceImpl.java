package com.webhard.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.service.EntryService;
import com.webhard.client.service.LoginService;
import com.webhard.dao.UserDao;

/**
 * The server-side implementation of the RPC service.
 */


public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	@Override
	public int logincheck(String id,String pwd) {
		// TODO Auto-generated method stub
		UserDao dao = new UserDao();
		
		int check = dao.loginUser(id, pwd);
		
		return check;
	}

}
