package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;

public interface LoginServiceAsync {

	void login(String id, String pwd,List<FolderDto> folderList,FolderDto homefolder,AsyncCallback<Integer> callback);
	void comboList(AsyncCallback<List<CompanyDto>> callback);
	void folderList(AsyncCallback<List<FolderDto>> callback);
	void homefolder(AsyncCallback<FolderDto> callback);
}
