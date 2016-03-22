package com.webhard.client.service;


public interface UserListServiceClientInt {
	
	void searchUserByName(String name);
	void searchUserBycompany(String company);
	void searchUserByPhone(String phone);
	void searchUserById(String id);
	void deleteUser(String id);
	void UserList();
	
}