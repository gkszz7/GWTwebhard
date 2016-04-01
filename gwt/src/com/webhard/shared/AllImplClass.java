package com.webhard.shared;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.AccessListServiceClientImpl;
import com.webhard.client.service.CompanyServiceClientImpl;
import com.webhard.client.service.EntryServiceClientImpl;
import com.webhard.client.service.LoginServiceClientImpl;
import com.webhard.client.service.MainServiceClientImpl;
import com.webhard.client.service.UserListServiceClientImpl;
import com.webhard.server.dao.FolderDao;

public class AllImplClass {
	public AllImplClass() {
		// TODO Auto-generated constructor stub
	}

	public void allImpl(String history, List<CompanyDto> companys, List<UserDto> users, Tree tree, String compName, int homeNum, UserDto userDto, HistoryData historydata) {
		
//		if(History.getToken().equals("login")){
//    		LoginServiceClientImpl login = new LoginServiceClientImpl(GWT.getModuleBaseURL()+"login");
//            RootPanel.get().clear();
//            RootPanel.get().add(login.getLoginUser());
//    	}else if(History.getToken().equals("AccessList")){
//    		AccessListServiceClientImpl access = new AccessListServiceClientImpl(GWT.getModuleBaseURL()+"AccessList");
//    		RootPanel.get().clear();
//            RootPanel.get().add(access.getAccessList());
//    	}else if(History.getToken().equals("UserList")){
//    		UserListServiceClientImpl user = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
//    		RootPanel.get().clear();
//            RootPanel.get().add(user.getUserList());
//    	}else if(History.getToken().equals("company")){
//    		CompanyServiceClientImpl company = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company", companys);
//    		RootPanel.get().clear();
//            RootPanel.get().add(company.getCompanyList());
//    	}else if(History.getToken().equals("entry")){
//    		EntryServiceClientImpl entry = new EntryServiceClientImpl(GWT.getModuleBaseURL()+"entry", companys, users);
//    		RootPanel.get().clear();
//            RootPanel.get().add(entry.getEntryUser());
//    	}else if(History.getToken().equals("Main")){
//    		System.out.println(123);
//    		MainServiceClientImpl main = new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree, compName, homeNum, userDto ,historydata);
//    		RootPanel.get().clear();
//            RootPanel.get().add(main.getMainPage());
//    	}
		
	}
}
