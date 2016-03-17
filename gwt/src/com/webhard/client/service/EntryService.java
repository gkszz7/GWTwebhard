package com.webhard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.ListBox;
import com.webhard.client.model.CompanyDto;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("entry")
public interface EntryService extends RemoteService {
	boolean IdCheck(String id);
	void entryUser(String id, String pw, String name, String phone, String addr, String company);
}
