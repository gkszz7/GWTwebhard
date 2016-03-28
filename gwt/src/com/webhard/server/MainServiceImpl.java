package com.webhard.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.MainService;
import com.webhard.server.dao.CompanyDao;
import com.webhard.server.dao.UserDao;
import com.webhard.server.dao.FolderDao;

public class MainServiceImpl extends RemoteServiceServlet implements MainService{
	private ItemDto homeFolder;
	private int parentNum = 0;
	@Override
	public List<CompanyDto> compList() {
		CompanyDao compDao = new CompanyDao();
		List<CompanyDto> compList = new ArrayList<CompanyDto>();
		compList = compDao.selectCompany();
		return compList;
	}
	
	@Override
	public ItemDto entryCompany(String name, String addr, String phone) {
		CompanyDao dao = new CompanyDao();
		FolderDao fDao = new FolderDao();

		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    UserDto user = (UserDto)session.getAttribute("user");
		
		dao.entryNewCompany(name, addr, phone);
		int cNum=dao.selectCompanyNum(name);
		int homeNum = fDao.selectHomeFolder().getItemNum();
		fDao.addNewFolder(name, homeNum, user.getUserId(), cNum);

		homeFolder = fDao.selectHomeFolder();
		setTree(homeFolder);
		return homeFolder;
	}

	@Override
	public List<UserDto> UserList() {
		List<UserDto> UserList = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		UserList = Dao.selectAllUser();
		return UserList;
	}
	
	@Override
	public List<UserDto> AccessList() {
		List<UserDto> Accesslist = new ArrayList<UserDto>();
		UserDao Dao = new UserDao();
		Accesslist = Dao.selectAccessUser();
		return Accesslist;
	}
	@Override
	public ItemDto createFolder(String name, int parentNum, int companyNum) {
		FolderDao dao = new FolderDao();
		
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    UserDto user = (UserDto)session.getAttribute("user");
	    
		dao.addNewFolder(name, parentNum, user.getUserId(), companyNum);
		
		homeFolder = dao.selectHomeFolder();
		setTree(homeFolder);
		return homeFolder;
	}
	@Override
	public ItemDto updateFolder(String name, int itemNum) {
		FolderDao dao = new FolderDao();
		dao.updateFolder(name, itemNum);
		
		homeFolder = dao.selectHomeFolder();
		setTree(homeFolder);
		return homeFolder;
	}
	@Override
	public ItemDto deleteFolder(int itemNum) {
		FolderDao dao = new FolderDao();
		parentNum = itemNum;
		delete(itemNum);
		
		homeFolder = dao.selectHomeFolder();
		setTree(homeFolder);
		return homeFolder;
	}
	public void setTree(ItemDto cycle) {

		FolderDao folDao = new FolderDao();
		FolderDto fDto;
		FolderDto homeFolder = folDao.selectHomeFolder();
		ItemDto home = new ItemDto();

		List<ItemDto> childNodes = new ArrayList<ItemDto>();
		List<ItemDto> grandChildNodes = new ArrayList<ItemDto>();

		if (cycle != null) {
			home = cycle;
		}

		childNodes = folDao.selectChildByParentNum(home.getItemNum());

		for (int i = 0; i < childNodes.size(); i++) {
			ItemDto childNode = childNodes.get(i);
			fDto = folDao.printFolderbyNum(childNode.getItemNum());
			if (fDto.getFolderType() == 0) {
				grandChildNodes = folDao.selectChildByParentNum(childNode
						.getItemNum());

				if (grandChildNodes.size() != 0) {
					for (int j = 0; j < grandChildNodes.size(); j++) {
						ItemDto grandChildNode = grandChildNodes.get(j);
						home.setChild(childNode);
						setTree(childNode);
						break;
					}

				} else {
					if (home.getItemNum() == homeFolder.getItemNum() || folDao.itemNumByParentNum(childNode.getItemNum()).size() == 0) {
						home.setChild(childNode);
					}
				}
			}
		}

		for (int i = 0; i < childNodes.size(); i++) {
			ItemDto childNode = childNodes.get(i);
			fDto = folDao.printFolderbyNum(childNode.getItemNum());
			if (fDto.getFolderType() != 0) {
				if (home.getItemNum() == homeFolder.getItemNum()) {
					home.setChild(childNode);
					setTree(childNode);
				}
			}
		}
		// tree = new JTree(home);

	}
	public void delete(int itemNum) {

		ArrayList<Integer> childs = new ArrayList<Integer>();
		FolderDao dao = new FolderDao();
		childs = (ArrayList<Integer>) dao.itemNumByParentNum(itemNum);

		for (int i = 0; i < childs.size(); i++) {
			int child = childs.get(i);
			ArrayList<Integer> grandChilds = (ArrayList<Integer>) dao.itemNumByParentNum(child);
			if (grandChilds.size() != 0) {
				for (int j = 0; j < grandChilds.size(); j++) {
					delete(child);
					if (((ArrayList<Integer>) dao.itemNumByParentNum(child)).size() == 0) {
						dao.deleteFolder(child);
					}
				}
			} else {
				dao.deleteFolder(child);
			}
		}
		if (((ArrayList<Integer>) dao.itemNumByParentNum(parentNum)).size() == 0) {
			dao.deleteFolder(parentNum);

		}
	} 
}
