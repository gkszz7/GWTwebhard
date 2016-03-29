package com.webhard.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.Tree;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService, IsSerializable{

	HashMap<String, Object> login(String id,String pwd) throws IllegalArgumentException;
	List<CompanyDto> comboList();
	List<UserDto> userList();
	ItemDto itemTree();
}
