package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.GUI.AccessList;
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
	public void searchUserByName(String name) {
		this.AccessAsync.searchUserByName(name, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				AccessListServiceClientImpl AccessListImpl = new AccessListServiceClientImpl(GWT.getModuleBaseURL()+"AccessList");
				AccessList Accesslist = new AccessList(AccessListImpl, result);
				RootPanel.get().add(Accesslist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
				
			}
		});
		
	}
	@Override
	public void searchUserBycompany(String company) {
		this.AccessAsync.searchUserBycompany(company, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				AccessListServiceClientImpl AccessListImpl = new AccessListServiceClientImpl(GWT.getModuleBaseURL()+"AccessList");
				AccessList Accesslist = new AccessList(AccessListImpl, result);
				RootPanel.get().add(Accesslist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
				
			}
		});
		
	}
	@Override
	public void searchUserByPhone(String phone) {
		this.AccessAsync.searchUserByPhone(phone, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				AccessListServiceClientImpl AccessListImpl = new AccessListServiceClientImpl(GWT.getModuleBaseURL()+"AccessList");
				AccessList Accesslist = new AccessList(AccessListImpl, result);
				RootPanel.get().add(Accesslist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
				
			}
		});
		
	}
	@Override
	public void searchUserById(String id) {
		this.AccessAsync.searchUserById(id, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				RootPanel.get().clear();
				
				AccessListServiceClientImpl AccessListImpl = new AccessListServiceClientImpl(GWT.getModuleBaseURL()+"AccessList");
				AccessList Accesslist = new AccessList(AccessListImpl, result);
				RootPanel.get().add(Accesslist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
				
			}
		});
		
	}
	@Override
	public void accessUser(String id) {
		this.AccessAsync.accessUser(id,new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				Window.alert("인증 되었습니다.");
				RootPanel.get().clear();
				
				AccessListServiceClientImpl AccessListImpl = new AccessListServiceClientImpl(GWT.getModuleBaseURL()+"AccessList");
				AccessList Accesslist = new AccessList(AccessListImpl, result);
				RootPanel.get().add(Accesslist);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public AccessList getAccessList(){
		return this.accessList;
	}
	
}
