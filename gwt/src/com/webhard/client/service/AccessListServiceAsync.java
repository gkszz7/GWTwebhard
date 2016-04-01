package com.webhard.client.service;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.UserDto;

public interface AccessListServiceAsync {

	void searchUserByName(String name, AsyncCallback<List<UserDto>> callback);
	void searchUserBycompany(String company,AsyncCallback<List<UserDto>> callback);
	void searchUserByPhone(String phone, AsyncCallback<List<UserDto>> callback);
	void searchUserById(String id, AsyncCallback<List<UserDto>> callback);

	void accessUser(String id, AsyncCallback<List<UserDto>> callback);
	void goMain(AsyncCallback<HashMap<String, Object>> callback);
	void getUser(AsyncCallback<UserDto> callback);

}
