package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;

@RemoteServiceRelativePath("Main")
public interface MainService extends RemoteService{

	List<CompanyDto> compList();
	List<UserDto> UserList();	
	List<UserDto> AccessList();
}
