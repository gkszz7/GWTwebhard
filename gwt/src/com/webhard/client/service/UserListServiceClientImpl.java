package com.webhard.client.service;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.webhard.client.GUI.UserList;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.CompanyServiceClientImpl.Images;

public class UserListServiceClientImpl implements UserListServiceClientInt{

	private UserListServiceAsync userListAsync;
	private List<UserDto> userlist;
	private List<CompanyDto> compList;
	private UserList userList;
	private Tree tree;
	private List<FileDto> files;
	public UserListServiceClientImpl(String url, Tree tree, List<FileDto> files){
		
		this.userListAsync = GWT.create(UserListService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.userListAsync;
		endPoint.setServiceEntryPoint(url);
		this.tree=tree;
		this.files=files;
		this.userList = new UserList(this,userlist,compList);
	}
	@Override
	public void searchUserByName(String name) {
		
		this.userListAsync.searchUserByName(name, new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				
				RootPanel.get().clear();
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList",tree,files);
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
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList",tree,files);
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
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList",tree,files);
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
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList",tree,files);
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
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList",tree,files);
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
				
				UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList",tree,files);
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
	public void goMain() {
		this.userListAsync.getUser(new AsyncCallback<UserDto>() {
			
			@Override
			public void onSuccess(UserDto result) {
				final UserDto user = result;
				userListAsync.goMain(new AsyncCallback<HashMap<String,Object>>() {
					
					@Override
					public void onSuccess(HashMap<String, Object> result) {
						String compName = (String)result.get("companyName");
						int homeFolderNum = Integer.parseInt((String)result.get("homeFolderNum"));
						UserDto userDto = (UserDto)result.get("userDto");
						
						RootPanel.get().clear();

						RootPanel.get().clear();
						
						MainServiceClientImpl main = 
								new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree,compName,homeFolderNum, user);
						
						RootPanel.get().add(main.getMainPage());
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
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
