package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CompanyServiceAsync {
	void compNameCheck(String name, AsyncCallback<Boolean> callback);
	void updateCompany(String basicName, String name, String phone, String addr, AsyncCallback<List<CompanyDto>> callback);
	void deleteCompany(String name,  AsyncCallback<List<CompanyDto>> callback);
	void searchCompByName(String name, AsyncCallback<List<CompanyDto>> callback);
	void searchCompByAddr(String addr, AsyncCallback<List<CompanyDto>> callback);
	void searchCompByPhone(String phone, AsyncCallback<List<CompanyDto>> callback);
}
