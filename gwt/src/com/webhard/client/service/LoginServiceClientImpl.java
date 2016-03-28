package com.webhard.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.webhard.client.GUI.LoginUser;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;

public class LoginServiceClientImpl implements LoginServiceClientInt{
	
	private LoginServiceAsync loginAsync;
	private LoginUser loginuser;
	private int check = 0;
	private List<CompanyDto> list;
	private Tree tree;
	
	public LoginServiceClientImpl(String url) {
		
		
		
		this.loginAsync = GWT.create(LoginService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.loginAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.loginuser = new LoginUser(this);
	}

	@Override
	public int login(final String id, String pwd, final Tree tree) {
			
		this.loginAsync.login(id, pwd, new AsyncCallback<HashMap<String, Object>>() {	
			@Override
			public void onSuccess(HashMap<String, Object> result) {
				System.out.println(123);
				check = (Integer)result.get("check");
				System.out.println(check);
				if((Integer)result.get("check") == 1){
					String compName = (String)result.get("companyName");
					int homeFolderNum = (Integer)result.get("homeFolderNum");
					Window.alert("로그인 성공");
					
					RootPanel.get().clear();
					
					MainServiceClientImpl main = 
							new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree,compName,homeFolderNum, id);
					
					RootPanel.get().add(main.getMainPage());
					
				}else if((Integer)result.get("check") == 0){
					Window.alert("비밀번호 실패");
					
				}else{
					Window.alert("존재하지않은 아이디");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		return check;
				
	}
	@Override
	public void comboList() {
		this.loginAsync.comboList(new AsyncCallback<List<CompanyDto>>() {
			
			@Override
			public void onSuccess(List<CompanyDto> result) {
				loginuser.setComList(result);
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
	}

	@Override
	public void userList() {
		this.loginAsync.userList(new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				loginuser.setUserList(result);
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void itemTree() {
		this.loginAsync.itemTree(new AsyncCallback<ItemDto>() {
			@Override
			public void onSuccess(ItemDto result) {
				tree = new Tree();
				TreeItem homeItem = new TreeItem();
				homeItem.setText(result.getName());
				homeItem.setUserObject(result);
				getTree(homeItem);
				loginuser.setTree(tree);
			}
			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}
	public void getTree(TreeItem result){
		
		TreeItem item = result;
		List<ItemDto> childNodes = new ArrayList<ItemDto>();
		List<ItemDto> grandChildNodes = new ArrayList<ItemDto>();
		ItemDto itemDto = (ItemDto)result.getUserObject();
		childNodes = itemDto.getChild();
		
		for(int i=0;i<childNodes.size();i++){
			ItemDto childNode = childNodes.get(i);
			
			TreeItem childItem = new TreeItem();
			childItem.setText(childNode.getName());
			childItem.setUserObject(childNode);
			
			grandChildNodes = childNode.getChild();
			if(grandChildNodes.size() != 0){
				for(int j=0; j<grandChildNodes.size(); j++){
					
					getTree(childItem);
					item.addItem(childItem);
					break;
				}
			}else{
				if(itemDto.getItemNum() == 78 || childItem.getChildCount() == 0){
					item.addItem(childItem);
				}
			}
			
		}
		tree.addItem(item);
	}
	
	public LoginUser getLoginUser(){
		return this.loginuser;
	}
}
