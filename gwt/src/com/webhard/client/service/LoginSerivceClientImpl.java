package com.webhard.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.webhard.client.GUI.EntryUser;
import com.webhard.client.GUI.LoginUser;

public class LoginSerivceClientImpl implements LoginServiceClientInt{
	
	private LoginServiceAsync loginAsync;
	private LoginUser loginuser;
	
	public LoginSerivceClientImpl(String url) {
		
		System.out.println(url);
		
		this.loginAsync = GWT.create(LoginService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.loginAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.loginuser = new LoginUser(this);
	}

	@Override
	public void getData() {
		// TODO Auto-generated method stub
		
	}
	public LoginUser getEntryUser(){
		return this.loginuser;
	}
}
