package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.GUI.EntryUser;
import com.webhard.client.model.CompanyDto;

public class EntryServiceClientImpl implements EntryServiceClientInt {
	
	private EntryServiceAsync entryAsync;
	private EntryUser entryUser;
	private List<CompanyDto> list;
	
	public EntryServiceClientImpl(String url) {
		
		this.entryAsync = GWT.create(EntryService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.entryAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.entryUser = new EntryUser(this, list);
	}
	
	@Override
	public void IdCheck(String id) {
		
		this.entryAsync.IdCheck(id, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result == true){
					Window.alert("이미 사용 중인 아이디 입니다.");
					entryUser.setIdCheck(result);
				}else{
					Window.alert("사용 가능 한 아이디 입니다.");
				}				
			}			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("idCheck error");
				
			}
		});
	}
	@Override
	public void entryUser(String id, String pw, String name, String phone,
			String addr, String company) {
		this.entryAsync.entryUser(id, pw, name, phone, addr, company, new AsyncCallback() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("entry error");
			}
			@Override
			public void onSuccess(Object result) {
				Window.alert("회원가입이 되셨습니다.");
				RootPanel.get().clear();
				LoginSerivceClientImpl login = new LoginSerivceClientImpl(GWT.getModuleBaseURL()+"login");
				
				RootPanel.get().add(login.getEntryUser());
			}
		});
	}

	public EntryUser getEntryUser(List<CompanyDto> result){
		list = result;
		return this.entryUser;
	}
	
}
