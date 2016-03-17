package com.webhard.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.webhard.client.GUI.MainPage;

public class MainSerivceClientImpl implements MainServiceClientInt{
	
	private MainServiceAsync mainAsync;
	private MainPage main;

	
	public MainSerivceClientImpl(String url) {
		
		this.mainAsync = GWT.create(MainService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.mainAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.main = new MainPage(this);
	}

	

	@Override
	public void MainPage() {
		// TODO Auto-generated method stub
		
	}
	public MainPage getMainPage(){
		return this.main;
	}
}
