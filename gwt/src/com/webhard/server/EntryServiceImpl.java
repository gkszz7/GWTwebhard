package com.webhard.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.service.EntryService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EntryServiceImpl extends RemoteServiceServlet implements EntryService {

	@Override
	public String getData(String name) {
		
		String name1 = "Name = "+name;
		
		return name1;
	}

}
