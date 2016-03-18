package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.thirdparty.javascript.rhino.head.tools.debugger.Main;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.GUI.LoginUser;
import com.webhard.client.GUI.MainPage;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;

public class LoginSerivceClientImpl implements LoginServiceClientInt{
	
	private LoginServiceAsync loginAsync;
	private LoginUser loginuser;
	private int check = 0;
	

	
	public LoginSerivceClientImpl(String url) {
		
		
		
		this.loginAsync = GWT.create(LoginService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.loginAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.loginuser = new LoginUser(this);
	}

	@Override
	public int login(final String id, String pwd,final List<FolderDto> folderList, final FolderDto homefolder) {
			
		this.loginAsync.login(id, pwd, folderList, homefolder, new AsyncCallback<Integer>() {	
			@Override
			public void onSuccess(Integer result) {
				check = result;
				if(result == 1){
					
					Window.alert("로그인 성공");
					
					RootPanel.get().clear();
					
					MainSerivceClientImpl main = new MainSerivceClientImpl(GWT.getModuleBaseURL()+"Main");
					
					MainPage mainpage = new MainPage(main, folderList, homefolder);
					
					RootPanel.get().add(mainpage);
					
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
	public void folderList() {
		this.loginAsync.folderList(new AsyncCallback<List<FolderDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(List<FolderDto> result) {
				// TODO Auto-generated method stub
				
				loginuser.setfolderList(result);
			}			
		});
		
	}
	@Override
	public void homefolder() {
		this.loginAsync.homefolder(new AsyncCallback<FolderDto>() {
			
			@Override
			public void onSuccess(FolderDto result) {
				// TODO Auto-generated method stub
				loginuser.sethomefolder(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});	
	}
	
	public LoginUser getEntryUser(){
		return this.loginuser;
	}
}
