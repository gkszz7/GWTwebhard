package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.webhard.client.GUI.AccessList;
import com.webhard.client.GUI.UserList;
import com.webhard.client.model.UserDto;

public class AccessListServiceClientImpl implements AccessListServiceClientInt{
	
	private AccessListServiceAsync AccessAsync;
	private AccessList accessList;
	private List<UserDto> AccessList;
	
	public AccessListServiceClientImpl(String url) {
		
		this.AccessAsync = GWT.create(AccessListService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.AccessAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.accessList = new AccessList(this, AccessList);
	}
	@Override
	public void AccessList() {
		// TODO Auto-generated method stub
		
	}
	public AccessList getAccessList(){
		return this.accessList;
	}
}
