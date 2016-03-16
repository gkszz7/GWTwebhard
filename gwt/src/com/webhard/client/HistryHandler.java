package com.webhard.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TabPanel;

public class HistryHandler implements ValueChangeHandler<String> {

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		// TODO Auto-generated method stub

		String historyToken = event.getValue();
		
		System.out.println(historyToken);
		
		if(historyToken.startsWith("login")){
			
		}else{
			
		}
	}

}
