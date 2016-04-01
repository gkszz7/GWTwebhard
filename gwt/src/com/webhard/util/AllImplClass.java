package com.webhard.util;

import com.webhard.client.model.FolderDto;
import com.webhard.server.dao.FolderDao;

public class AllImplClass {
	public AllImplClass(String history) {
		
//		LoginServiceClientImpl login = new LoginServiceClientImpl(url);
//		AccessListServiceClientImpl access = new AccessListServiceClientImpl(url);
//		CompanyServiceClientImpl company = new CompanyServiceClientImpl(url, comps);
//		EntryServiceClientImpl entry = new EntryServiceClientImpl(url, list, userList);
//		FileUploadServiceClientImpl upload = new FileUploadServiceClientImpl();
//		UserListServiceClientImpl user = new UserListServiceClientImpl(url);
//		MainServiceClientImpl main = new MainServiceClientImpl(url, getTree, compName, homeNum, userDto);
		
	}
	public String hello(){
		FolderDao folderDao = new FolderDao();
		FolderDto folderDto = folderDao.selectHomeFolder();
		return folderDto.getName();
	}
}
