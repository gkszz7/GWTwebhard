package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.GUI.CompanyList;
import com.webhard.client.model.CompanyDto;


public class CompanyServiceClientImpl implements CompanyServiceClientInt{
	
	private CompanyServiceAsync companyAsync;
	private CompanyList company;
	private List<CompanyDto> companys;

	
	public CompanyServiceClientImpl(String url) {
		
		this.companyAsync = GWT.create(CompanyService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.companyAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.company = new CompanyList(this, companys);
	}
	
	@Override
	public void compNameCheck(String name) {
		this.companyAsync.compNameCheck(name, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result == true){
					Window.alert("존재하는 회사명 입니다.");
					company.setNameCheck(result);
				}else{
					Window.alert("수정 가능 한 회사명 입니다.");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void updateCompany(String basicName, String name, String phone,
			String addr) {
		this.companyAsync.updateCompany(basicName, name, phone, addr, new AsyncCallback() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("company edit error");
			}
			@Override
			public void onSuccess(Object result) {
				Window.alert("수정 되었습니다.");
				RootPanel.get().clear();
				
				CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company");
				CompanyList companyList = new CompanyList(compImpl, companys);
				RootPanel.get().add(companyList);
			}
		});
	}

	
//	public CompanyList getCompanyList(List<CompanyDto> list){
//		companys = list;
//		return this.company;
//	}
}
