package com.webhard.client.service;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;

@RemoteServiceRelativePath("UserList")
public interface UserListService extends RemoteService{

	void UserList();
	List<UserDto> updateUser(String id,String name, String phone, String addr,String company);
	List<UserDto> deleteUser(String id);
	List<UserDto> searchUserByName(String name);
	List<UserDto> searchUserBycompany(String company);
	List<UserDto> searchUserByPhone(String phone);
	List<UserDto> searchUserById(String id);
	HashMap<String, Object> goMain() throws IllegalArgumentException;
	UserDto getUser();
} 