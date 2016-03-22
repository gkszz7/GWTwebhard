package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;

public interface MainServiceAsync {
	void compList(AsyncCallback<List<CompanyDto>> callback);
	void entryCompany(String name, String addr, String phone, AsyncCallback callback);
	void folderList(AsyncCallback<List<FolderDto>> callback);
	void homeFolder(AsyncCallback<FolderDto> callback);
}
