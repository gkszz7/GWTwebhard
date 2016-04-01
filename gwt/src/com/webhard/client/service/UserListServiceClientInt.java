package com.webhard.client.service;


public interface UserListServiceClientInt {
	
	void updateUser(String id,String name, String phone, String addr,String company);
	void searchUserByName(String name);
	void searchUserBycompany(String company);
	void searchUserByPhone(String phone);
	void searchUserById(String id);
	void deleteUser(String id);
	void UserList();
	void goMain();
}