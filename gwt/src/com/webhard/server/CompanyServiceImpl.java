package com.webhard.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.CompanyService;
import com.webhard.server.dao.CompanyDao;
import com.webhard.server.dao.FolderDao;
import com.webhard.server.dao.UserDao;


/**
 * The server-side implementation of the RPC service.
 */

public class CompanyServiceImpl extends RemoteServiceServlet implements CompanyService {
	
	private int selectNum;
	
	@Override
	public boolean compNameCheck(String name) {
		boolean checkId = false;
		boolean check = false;
		CompanyDao cDao = new CompanyDao();
		
		checkId = cDao.checkCompanyName(name);
		if(checkId == true){
			check = true;
		}else{
			check = false;
		}
		return check;
	}
	
	@Override
	public List<CompanyDto> updateCompany(String basicName, String name, String phone, String addr) {
		List<CompanyDto> companys = new ArrayList<CompanyDto>();
		CompanyDao cDao = new CompanyDao();
		FolderDao fDao = new FolderDao();
		CompanyDto cDto = cDao.getCompanyDtoByName(basicName);
		cDao.updateCompany(cDto.getCompanyNum(), name, addr, phone);
		ItemDto item = fDao.printCompFolderbyCompanyNum(cDto.getCompanyNum());
		fDao.updateFolder(name, item.getItemNum());
		companys = cDao.selectCompany();
		return companys;
	}
	@Override
	public List<CompanyDto> deleteCompany(String name) {
		List<CompanyDto> companys = new ArrayList<CompanyDto>();
		CompanyDao cDao = new CompanyDao();
		FolderDao fDao = new FolderDao();
		ItemDto fDto = new ItemDto();
		
		int compNum = cDao.selectCompanyNum(name);
		fDto = fDao.printCompFolderbyCompanyNum(compNum);
		
		selectNum = fDto.getItemNum();
		deleteFolder(selectNum);
		cDao.deleteCompany(name);
		
		companys = cDao.selectCompany();
		
		return companys;
	}
	@Override
	public List<CompanyDto> searchCompByName(String name) {
		List<CompanyDto> companys = new ArrayList<CompanyDto>();
		CompanyDao cDao = new CompanyDao();
		companys = cDao.searchCompanybyName(name);
		return companys;
	}
	@Override
	public List<CompanyDto> searchCompByAddr(String addr) {
		List<CompanyDto> companys = new ArrayList<CompanyDto>();
		CompanyDao cDao = new CompanyDao();
		companys = cDao.searchCompanybyAddr(addr);
		return companys;
	}
	@Override
	public List<CompanyDto> searchCompByPhone(String phone) {
		List<CompanyDto> companys = new ArrayList<CompanyDto>();
		CompanyDao cDao = new CompanyDao();
		companys = cDao.searchCompanybyPhone(phone);
		return companys;
	}
	public void deleteFolder(int itemNum) {

		ArrayList<Integer> childs = new ArrayList<Integer>();
		FolderDao dao = new FolderDao();
		childs = (ArrayList<Integer>) dao.itemNumByParentNum(itemNum);

		for (int i = 0; i < childs.size(); i++) {
			int child = childs.get(i);
			ArrayList<Integer> grandChilds = (ArrayList<Integer>) dao.itemNumByParentNum(child);
			if (grandChilds.size() != 0) {
				for (int j = 0; j < grandChilds.size(); j++) {
					deleteFolder(child);
					if (((ArrayList<Integer>) dao.itemNumByParentNum(child)).size() == 0) {
						dao.deleteFolder(child);
					}
				}
			} else {
				dao.deleteFolder(child);
			}
		}
		if (((ArrayList<Integer>) dao.itemNumByParentNum(selectNum)).size() == 0) {
			dao.deleteFolder(selectNum);

		}
	}
	@Override
	public HashMap<String, Object> goMain() {
		UserDao userDao = new UserDao();
		FolderDao folDao = new FolderDao();
		UserDto userDto = new UserDto();
		
		int homeNum = folDao.selectHomeFolder().getItemNum();
		String companyName = null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
	    
		int compNum = userDao.selectcompany(userDto.getUserId());
		companyName = userDao.selectcompanyname(compNum);
		String homeFolderNum = Integer.toString(homeNum);
		
		map.put("companyName", companyName);
	    map.put("homeFolderNum", homeFolderNum);
		return map;
	}
	@Override
	public UserDto getUser() {
		UserDto userDto = new UserDto();
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    
	    userDto = (UserDto)session.getAttribute("user");
		return userDto;
	}
}
