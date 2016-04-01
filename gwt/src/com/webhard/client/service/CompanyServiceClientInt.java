package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;


public interface CompanyServiceClientInt {
	void compNameCheck(String name);
	void updateCompany(String basicName, String name, String phone, String addr);
	void deleteCompany(String name);
	void searchCompByName(String name);
	void searchCompByAddr(String addr);
	void searchCompByPhone(String phone);
	void goMain();
}
