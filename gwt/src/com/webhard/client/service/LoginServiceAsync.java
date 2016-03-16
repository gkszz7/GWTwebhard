package com.webhard.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.dto.UserDto;

public interface LoginServiceAsync {

	void login(String id, String pwd, AsyncCallback<Integer> callback);

}
