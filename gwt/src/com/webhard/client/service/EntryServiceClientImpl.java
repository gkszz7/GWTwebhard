package com.webhard.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.webhard.client.GUI.EntryUser;

public class EntryServiceClientImpl implements EntryServiceClientInt {
	
	private EntryServiceAsync entryAsync;
	private EntryUser entryUser;
	
	public EntryServiceClientImpl(String url) {
		System.out.println(url);
		this.entryAsync = GWT.create(EntryService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.entryAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.entryUser = new EntryUser(this);
	}
	
	@Override
	public void getData(String name) {
		this.entryAsync.getData(name, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error");
				
			}
			@Override
			public void onSuccess(String result) {
				if(result instanceof String){
					String edit = (String)result;
					entryUser.editID(edit);
				}
				
			}
			
		});
		
	}
	
	public EntryUser getEntryUser(){
		return this.entryUser;
	}
	
	
}
