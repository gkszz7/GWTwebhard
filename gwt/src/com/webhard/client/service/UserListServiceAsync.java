package com.webhard.client.service;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.UserDto;

public interface UserListServiceAsync {

	void UserList(AsyncCallback<Void> callback);

	void searchUserByName(String name, AsyncCallback<List<UserDto>> callback);

	void searchUserBycompany(String company,
			AsyncCallback<List<UserDto>> callback);

	void searchUserByPhone(String phone, AsyncCallback<List<UserDto>> callback);

	void searchUserById(String id,AsyncCallback<List<UserDto>> callback);

	void deleteUser(String id, AsyncCallback<List<UserDto>> callback);

	void updateUser(String id,String name, String phone, String addr,String company, AsyncCallback<List<UserDto>> callback);
	void goMain(AsyncCallback<HashMap<String, Object>> callback);
	void getUser(AsyncCallback<UserDto> callback);
} 
