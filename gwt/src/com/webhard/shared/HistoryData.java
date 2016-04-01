package com.webhard.shared;

import java.util.List;

import com.google.gwt.user.client.ui.Tree;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;

public class HistoryData {
	private List<CompanyDto> companyList;
	private List<UserDto> userListByEntry;
	private Tree tree;
	private String companyNameByMain;
	private int homeNumByMain;
	private UserDto user;
	public HistoryData() {
		
	}
	public HistoryData(List<CompanyDto> companys, List<UserDto> users, Tree tree1, String compName){
		this.companyList = companys;
		this.userListByEntry = users;
		this.tree = tree1;
		this.companyNameByMain = compName;
	}
	public List<CompanyDto> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<CompanyDto> companyList) {
		this.companyList = companyList;
	}
	public List<UserDto> getUserListByEntry() {
		return userListByEntry;
	}
	public void setUserListByEntry(List<UserDto> userListByEntry) {
		this.userListByEntry = userListByEntry;
	}
	public Tree getTree() {
		return tree;
	}
	public void setTree(Tree tree) {
		this.tree = tree;
	}
	public String getCompanyNameByMain() {
		return companyNameByMain;
	}
	public void setCompanyNameByMain(String companyNameByMain) {
		this.companyNameByMain = companyNameByMain;
	}
	public int getHomeNumByMain() {
		return homeNumByMain;
	}
	public void setHomeNumByMain(int homeNumByMain) {
		this.homeNumByMain = homeNumByMain;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
}
