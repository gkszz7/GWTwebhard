package com.webhard.client.service;

import java.util.List;

import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;


public interface LoginServiceClientInt {
	int login(String id, String pwd,List<FolderDto> folderList,FolderDto homefolder);
	void comboList();
	void folderList();
	void homefolder();
}
 