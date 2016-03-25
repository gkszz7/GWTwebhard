package com.webhard.client.service;


public interface MainServiceClientInt {
	
	void UserList();
	void AccessList();
	void compList();
	void entryCompany(String name, String addr, String phone);
	void createFolder(String name, int parentNum, int companyNum);
	void updateFolder(String name, int itemNum);
	void deleteFolder(int itemNum);
}
