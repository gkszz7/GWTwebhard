package com.webhard.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void login(String id, String pwd, AsyncCallback<Integer> callback);

}
