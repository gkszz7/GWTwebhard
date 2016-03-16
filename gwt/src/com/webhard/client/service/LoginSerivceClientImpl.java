package com.webhard.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.webhard.client.GUI.LoginUser;

public class LoginSerivceClientImpl implements LoginServiceClientInt{
	
	private LoginServiceAsync loginAsync;
	private LoginUser loginuser;
	
	public LoginSerivceClientImpl(String url) {
				
		this.loginAsync = GWT.create(LoginService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.loginAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.loginuser = new LoginUser(this);
	}

	@Override
	public int logincheck(String id, String pwd) {
		this.loginAsync.logincheck(id, pwd,new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				//서버에서 성공
				// TODO Auto-generated method stub
				//로그인 성공
				if(result == 1){
					Window.alert("로그인 성공");
				//로그인 실패
				}else if(result == 0){
					Window.alert("비밀번호가 틀립니다."); 
				}else{
					Window.alert("아이디가 틀립니다.");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				//서버에서 실패
				// TODO Auto-generated method stub
				Window.alert("Error");
			}
		});
		return 0;
		// TODO Auto-generated method stub
		
	}
	public LoginUser getEntryUser(){
		return this.loginuser;
	}

}
