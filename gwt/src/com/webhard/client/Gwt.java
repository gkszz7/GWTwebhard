package com.webhard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.service.EntryServiceClientImpl;
import com.webhard.client.service.LoginSerivceClientImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		LoginSerivceClientImpl login = new LoginSerivceClientImpl(GWT.getModuleBaseURL()+"login");
		
		RootPanel.get().add(login.getEntryUser());
		
		
	}
	
}
