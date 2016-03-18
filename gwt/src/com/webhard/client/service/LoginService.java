package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{

	int login(String id,String pwd,List<FolderDto> folderList,FolderDto homefolder);
	List<CompanyDto> comboList();
	List<FolderDto> folderList();
	FolderDto homefolder();
}
