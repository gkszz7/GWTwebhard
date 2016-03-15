package com.webhard.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EntryServiceAsync {
	void getData(String name, AsyncCallback<String> callback);
}
