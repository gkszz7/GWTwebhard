package com.webhard.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AccessList")
public interface AccessListService extends RemoteService{

	void AccessList();
	
}
