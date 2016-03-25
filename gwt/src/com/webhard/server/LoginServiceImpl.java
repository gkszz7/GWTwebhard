package com.webhard.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.LoginService;
import com.webhard.server.dao.CompanyDao;
import com.webhard.server.dao.FolderDao;
import com.webhard.server.dao.UserDao;

/**
 * The server-side implementation of the RPC service.
 */


public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	
	private ItemDto homeFolder;
	
	@Override
	public int login(String id,String pwd) {
	
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		int chack = userDao.loginUser(id, pwd);
		
		if(chack == 1){
			if(id.equals("admin")){
				userDto = userDao.getOneUserData(id);
			}else{
				userDto = userDao.getUserData(id);
			}
			HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		    HttpSession session = httpServletRequest.getSession(true);
		    session.setAttribute("user", userDto);
		    
			return chack;
			
		}else if(chack == 0){
			return chack;
		}else{
			return chack;
		}
	}
	@Override
	public List<CompanyDto> comboList() {
		List<CompanyDto> comList = new ArrayList<CompanyDto>();
		CompanyDao dao = new CompanyDao();
		comList = dao.selectCompany();
		
		return comList;
	}
	
	@Override
	public List<UserDto> userList() {
		List<UserDto> userList = new ArrayList<UserDto>();
		UserDao dao = new UserDao();
		userList = dao.selectAllUser();
		return userList;
	}
	
	@Override
	public ItemDto itemTree() {
		FolderDao folDao = new FolderDao();
		homeFolder = folDao.selectHomeFolder();
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

}
