package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Tree;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;

public interface LoginServiceAsync {

	void login(String id, String pwd,AsyncCallback<Integer> callback);
	void comboList(AsyncCallback<List<CompanyDto>> callback);
	void userList(AsyncCallback<List<UserDto>> callback);
	void itemTree(AsyncCallback<ItemDto> callback);
}
