package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.GUI.LoginUser;
import com.webhard.client.GUI.MainPage;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;

public class LoginServiceClientImpl implements LoginServiceClientInt{
	
	private LoginServiceAsync loginAsync;
	private LoginUser loginuser;
	private int check = 0;
	private List<CompanyDto> list;

	
	public LoginServiceClientImpl(String url) {
		
		
		
		this.loginAsync = GWT.create(LoginService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.loginAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.loginuser = new LoginUser(this);
	}

	@Override
	public int login(final String id, String pwd) {
			
		this.loginAsync.login(id, pwd, new AsyncCallback<Integer>() {	
			@Override
			public void onSuccess(Integer result) {
				check = result;
				if(result == 1){
					
					Window.alert("로그인 성공");
					
//					RootPanel.get().clear();
//					
//					MainServiceClientImpl main = new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main");
//					
//					MainPage mainpage = new MainPage(main, folderList, homefolder);
//					
//					RootPanel.get().add(mainpage);
					
					RootPanel.get().clear();
					
					MainServiceClientImpl main = new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main");
					
					RootPanel.get().add(main.getMainPage());
					
				}else if(result == 0){
					Window.alert("비밀번호 실패");
					
				}else{
					Window.alert("존재하지않은 아이디");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		return check;
				
	}
	@Override
	public void comboList() {
		this.loginAsync.comboList(new AsyncCallback<List<CompanyDto>>() {
			
			@Override
			public void onSuccess(List<CompanyDto> result) {
				loginuser.setComList(result);
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
	}

	@Override
	public void userList() {
		this.loginAsync.userList(new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				loginuser.setUserList(result);
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public LoginUser getLoginUser(){
		return this.loginuser;
	}
}
