package com.webhard.client.service;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webhard.client.model.UserDto;

@RemoteServiceRelativePath("AccessList")
public interface AccessListService extends RemoteService{

	List<UserDto> accessUser(String id);
	List<UserDto> searchUserByName(String name);
	List<UserDto> searchUserBycompany(String company);
	List<UserDto> searchUserByPhone(String phone);
	List<UserDto> searchUserById(String id);
	HashMap<String, Object> goMain() throws IllegalArgumentException;
	UserDto getUser();
}
