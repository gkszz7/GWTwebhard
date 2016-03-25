package com.webhard.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.MainService;
import com.webhard.server.dao.CompanyDao;
import com.webhard.server.dao.FolderDao;
import com.webhard.server.dao.UserDao;

public class MainServiceImpl extends RemoteServiceServlet implements MainService{
	private ItemDto homeFolder;

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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ItemDto updateFolder(String name, int itemNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ItemDto deleteFolder(int itemNum) {
		// TODO Auto-generated method stub
		return null;
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
					System.out.println(folDao.itemNumByParentNum(
							childNode.getItemNum()).size());
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
	public void test(String filename){
		
		File file = new File(filename);
		file.getName();
		
	}
	public class Files extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			super.doGet(req, resp);
		}
	}
}
