package com.webhard.client.service;


import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.model.FolderDto;

@RemoteServiceRelativePath("Main")
public interface MainService extends RemoteService{
	
	List<CompanyDto> compList();
	List<UserDto> UserList();	
	List<UserDto> AccessList();
	ItemDto entryCompany(String name, String addr, String phone);
	ItemDto createFolder(String name, int parentNum, int companyNum);
	ItemDto updateFolder(String name, int itemNum);
	ItemDto deleteFolder(int itemNum);
	HashMap<String, Object> ItemInTable(int itemNum);	
	void logout();
	List<FileDto> allFiles();
	ItemDto deletefile(int itemNum);
}
