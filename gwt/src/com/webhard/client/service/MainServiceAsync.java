package com.webhard.client.service;

import java.io.File;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.model.FolderDto;

public interface MainServiceAsync {
	void compList(AsyncCallback<List<CompanyDto>> callback);

	void AccessList(AsyncCallback<List<UserDto>> callback);

	void UserList(AsyncCallback<List<UserDto>> callback);
	void entryCompany(String name, String addr, String phone, AsyncCallback callback);
	void folderList(AsyncCallback<List<FolderDto>> callback);
	void homeFolder(AsyncCallback<FolderDto> callback);
	
}
