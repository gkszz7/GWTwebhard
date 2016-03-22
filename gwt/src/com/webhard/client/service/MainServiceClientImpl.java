package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.webhard.client.GUI.MainPage;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;

public class MainServiceClientImpl implements MainServiceClientInt{
	
	private MainServiceAsync mainAsync;
	private MainPage main;
	
	public MainServiceClientImpl(String url,FolderDto homefol,List<FolderDto> folList, List<CompanyDto> compList) {
		
		this.mainAsync = GWT.create(MainService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.mainAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.main = new MainPage(this, folList, homefol, compList);
	}
	
	@Override
	public void compList() {
		this.mainAsync.compList(new AsyncCallback<List<CompanyDto>>() {
			
			@Override
			public void onSuccess(List<CompanyDto> result) {
				main.setCompList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public MainPage getMainPage(){

		return this.main;
	}
}
