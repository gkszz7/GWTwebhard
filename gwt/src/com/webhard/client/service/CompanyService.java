package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webhard.client.model.CompanyDto;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("company")
public interface CompanyService extends RemoteService {
	boolean compNameCheck(String name);
	void updateCompany(String basicName, String name, String phone, String addr);
}
