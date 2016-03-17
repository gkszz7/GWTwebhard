package com.webhard.client.service;


public interface EntryServiceClientInt {
	void IdCheck(String id);
	void entryUser(String id, String pw, String name, String phone, String addr, String company);
	void comboList();
}
