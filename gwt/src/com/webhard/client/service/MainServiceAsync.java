package com.webhard.client.service;


import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.model.FolderDto;

public interface MainServiceAsync {
	void compList(AsyncCallback<List<CompanyDto>> callback);
	void AccessList(AsyncCallback<List<UserDto>> callback);
	void UserList(AsyncCallback<List<UserDto>> callback);
	void entryCompany(String name, String addr, String phone, AsyncCallback<ItemDto> callback);
	void createFolder(String name, int parentNum, int companyNum, AsyncCallback<ItemDto> callback);
	void updateFolder(String name, int itemNum, AsyncCallback<ItemDto> callback);
	void deleteFolder(int itemNum, AsyncCallback<ItemDto> callback);
	
	void ItemInTable(int itemNum, AsyncCallback<HashMap<String, Object>> callback);

}
