package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.ui.Tree;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;


public interface LoginServiceClientInt {
	int login(String id, String pwd, Tree tree);
	void comboList();
	void userList();
	void itemTree();
}
 