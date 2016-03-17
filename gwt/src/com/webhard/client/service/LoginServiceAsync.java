package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;

public interface LoginServiceAsync {

	void login(String id, String pwd, AsyncCallback<Integer> callback);
	void comboList(AsyncCallback<List<CompanyDto>> callback);
}
