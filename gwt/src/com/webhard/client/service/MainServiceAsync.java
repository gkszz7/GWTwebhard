package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;

public interface MainServiceAsync {
	void compList(AsyncCallback<List<CompanyDto>> callback);

	void AccessList(AsyncCallback<List<UserDto>> callback);

	void UserList(AsyncCallback<List<UserDto>> callback);
}
