package com.webhard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.service.LoginServiceClientImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
			
		LoginServiceClientImpl login = new LoginServiceClientImpl(GWT.getModuleBaseURL()+"login");
		
		RootPanel.get().add(login.getLoginUser());
		

	}

}
