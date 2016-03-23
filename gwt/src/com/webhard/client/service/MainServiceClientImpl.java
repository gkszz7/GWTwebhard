package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.GUI.MainPage;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;

public class MainServiceClientImpl implements MainServiceClientInt{
	
	private MainServiceAsync mainAsync;
	private MainPage main;
	
	public MainServiceClientImpl(String url) {
		
		this.mainAsync = GWT.create(MainService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.mainAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.main = new MainPage(this);
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
			}
		});
		
	}
	@Override
	public void folderList() {
		this.mainAsync.folderList(new AsyncCallback<List<FolderDto>>() {
			@Override
			public void onSuccess(List<FolderDto> result) {
				main.setFolderList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void homeFolder() {
		this.mainAsync.homeFolder(new AsyncCallback<FolderDto>() {
			@Override
			public void onSuccess(FolderDto result) {
				main.setHomeFolder(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void entryCompany(String name, String addr, String phone) {
		this.mainAsync.entryCompany(name, addr, phone, new AsyncCallback<List<CompanyDto>>() {
			
			@Override
			public void onSuccess(List<CompanyDto> result) {
				Window.alert("회사가 추가되었습니다.");
				
				RootPanel.get().clear();
				
				MainServiceClientImpl main = new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main");
				
				RootPanel.get().add(main.getMainPage());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
		
	}
	public void UserList() {
		
			this.mainAsync.UserList(new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				main.selectUser(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});	
	}
	
	public void AccessList() {

		this.mainAsync.AccessList(new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				// TODO Auto-generated method stub
				main.selectAccess(result);
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
