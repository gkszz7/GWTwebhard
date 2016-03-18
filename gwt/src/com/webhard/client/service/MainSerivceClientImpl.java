package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.webhard.client.GUI.MainPage;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;

public class MainSerivceClientImpl implements MainServiceClientInt{
	
	private MainServiceAsync mainAsync;
	private MainPage main;
	private List<FolderDto> folList;
	private FolderDto homefolder;
	
	public MainSerivceClientImpl(String url) {
		
		this.mainAsync = GWT.create(MainService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.mainAsync;
		endPoint.setServiceEntryPoint(url);

		this.main = new MainPage(this,folList,homefolder);
		
	}

	
	public MainPage getMainPage(List<FolderDto> folderList,FolderDto Homefolder){
		
		homefolder = Homefolder;
		folList = folderList;
		return this.main;
	}


	@Override
	public void MainPage() {
		// TODO Auto-generated method stub
		
	}
}
