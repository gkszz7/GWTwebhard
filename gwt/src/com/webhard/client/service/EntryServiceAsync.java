package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.webhard.client.model.CompanyDto;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EntryServiceAsync {
	void IdCheck(String id, AsyncCallback<Boolean> callback);
	void entryUser(String id, String pw, String name, String phone, String addr, String company, AsyncCallback callback);
	void comboList(AsyncCallback<List<CompanyDto>> callback);
}
