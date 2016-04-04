package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;


public interface MainServiceClientInt {
	
	void UserList();
	void AccessList();
	void compList();
	void entryCompany(String name, String addr, String phone);
	void createFolder(String name, int parentNum, int companyNum);
	void updateFolder(String name, int itemNum);
	void deleteFolder(int itemNum);
	void deletefile(int itemNum);
	void ItemInTable(int itemNum);
	void logout();
	void allFiles();
}
