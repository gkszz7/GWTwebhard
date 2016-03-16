package com.webhard.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webhard.client.dto.UserDto;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{

	int login(String id,String pwd);
}
