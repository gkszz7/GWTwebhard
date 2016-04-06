package com.webhard.client.service;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("company")
public interface CompanyService extends RemoteService {
	boolean compNameCheck(String name);
	List<CompanyDto> updateCompany(String basicName, String name, String phone, String addr);
	List<CompanyDto> deleteCompany(String name);
	List<CompanyDto> searchCompByName(String name);
	List<CompanyDto> searchCompByAddr(String addr);
	List<CompanyDto> searchCompByPhone(String phone);
	HashMap<String, Object> goMain() throws IllegalArgumentException;
	UserDto getUser();
	ItemDto getHomeFolder();
}
