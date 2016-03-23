package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.GUI.UserList;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;

public class UserListServiceClientImpl implements UserListServiceClientInt{

	private UserListServiceAsync userListAsync;
	private List<UserDto> userlist;
	private List<CompanyDto> compList;
	private UserList userList;
	
	public UserListServiceClientImpl(String url){
		
		this.userListAsync = GWT.create(UserListService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.userListAsync;
		endPoint.setServiceEntryPoint(url);

		this.userList = new UserList(this,userlist,compList);
	}
	@Override
	public void searchUserByName(String name) {
		
		this.userListAsync.searchUserByName(name, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				
				RootPanel.get().clear();
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
				UserList userlist = new UserList(userImpl, result,compList);
				RootPanel.get().add(userlist);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
			}
		});
		
	}
	@Override
	public void searchUserBycompany(String company) {
		this.userListAsync.searchUserBycompany(company, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
				UserList userlist = new UserList(userImpl, result,compList);
				RootPanel.get().add(userlist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
			}
		});	
	}
	@Override
	public void searchUserByPhone(String phone) {
		this.userListAsync.searchUserByPhone(phone,new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
				UserList userlist = new UserList(userImpl, result,compList);
				RootPanel.get().add(userlist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
			}
		});
		
	}
	@Override
	public void searchUserById(String id) {
		this.userListAsync.searchUserById(id,new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
				UserList userlist = new UserList(userImpl, result,compList);
				RootPanel.get().add(userlist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
			}
		});		
	}
	@Override
	public void UserList() {
		
	}
	
	@Override
	public void deleteUser(String id) {
		this.userListAsync.deleteUser(id, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
				UserList userlist = new UserList(userImpl, result,compList);
				RootPanel.get().add(userlist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void updateUser(String id,String name, String phone,String addr, String company) {
		this.userListAsync.updateUser(id, name, phone, addr, company, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
				UserList userlist = new UserList(userImpl, result,compList);
				RootPanel.get().add(userlist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}	
	public UserList getUserList(){
		return this.userList;
	}
}
