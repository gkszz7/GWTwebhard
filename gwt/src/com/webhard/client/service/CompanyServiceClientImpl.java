package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
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
	

	
	public CompanyList getCompanyList(List<CompanyDto> list){
		companys = list;
		return this.company;
	}
}
