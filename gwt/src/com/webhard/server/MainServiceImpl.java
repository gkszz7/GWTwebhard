package com.webhard.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.MainService;
import com.webhard.server.dao.CompanyDao;
import com.webhard.server.dao.FolderDao;

public class MainServiceImpl extends RemoteServiceServlet implements MainService{
	
	@Override
	public List<CompanyDto> compList() {
		CompanyDao compDao = new CompanyDao();
		List<CompanyDto> compList = new ArrayList<CompanyDto>();
		compList = compDao.selectCompany();
		return compList;
	}
	
	@Override
	public void entryCompany(String name, String addr, String phone) {
		CompanyDao dao = new CompanyDao();
		FolderDao fDao = new FolderDao();

		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    UserDto user = (UserDto)session.getAttribute("user");
		
		dao.entryNewCompany(name, addr, phone);
		int cNum=dao.selectCompanyNum(name);
		int homeNum = fDao.selectHomeFolder().getItemNum();
		fDao.addNewFolder(name, homeNum, user.getUserId(), cNum);
	}
	@Override
	public List<FolderDto> folderList() {
		List<FolderDto> folderList = new ArrayList<FolderDto>();
		FolderDao dao = new FolderDao();
		folderList = dao.selectFolderTree();
		return folderList;
	}
	@Override
	public FolderDto homeFolder() {
		FolderDto homeFolder = new FolderDto();
		FolderDao folderDao = new FolderDao();
		homeFolder = folderDao.selectHomeFolder();
		return homeFolder;
	}

}
