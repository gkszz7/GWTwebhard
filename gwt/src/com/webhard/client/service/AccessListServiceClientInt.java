package com.webhard.client.service;

public interface AccessListServiceClientInt {

	void accessUser(String id);
	void searchUserByName(String name);
	void searchUserBycompany(String company);
	void searchUserByPhone(String phone);
	void searchUserById(String id);
	void goMain();
}
