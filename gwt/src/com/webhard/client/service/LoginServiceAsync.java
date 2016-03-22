package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;

public interface LoginServiceAsync {

	void login(String id, String pwd,List<FolderDto> folderList,FolderDto homefolder, List<CompanyDto> compList, AsyncCallback<Integer> callback);
	void comboList(AsyncCallback<List<CompanyDto>> callback);
	void folderList(AsyncCallback<List<FolderDto>> callback);
	void homefolder(AsyncCallback<FolderDto> callback);
	void userList(AsyncCallback<List<UserDto>> callback);
}
