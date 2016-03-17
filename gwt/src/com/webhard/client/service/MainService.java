package com.webhard.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Main")
public interface MainService extends RemoteService{

	void MainPage();
	
}
