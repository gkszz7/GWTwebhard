package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.webhard.client.GUI.LoginUser;
import com.webhard.client.model.CompanyDto;

public class LoginSerivceClientImpl implements LoginServiceClientInt{
	
	private LoginServiceAsync loginAsync;
	private LoginUser loginuser;
	private List<CompanyDto> list;

	
	public LoginSerivceClientImpl(String url) {
		
		
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
				if(result == 1){
					Window.alert("로그인 성공");
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
		
		return 0;	
		
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
	public LoginUser getEntryUser(){
		return this.loginuser;
	}

}
