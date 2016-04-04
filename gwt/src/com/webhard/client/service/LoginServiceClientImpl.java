package com.webhard.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.webhard.client.GUI.LoginUser;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;

public class LoginServiceClientImpl implements LoginServiceClientInt{
	
	private LoginServiceAsync loginAsync;
	private LoginUser loginuser;
	private int check = 0;
	private List<CompanyDto> compList;
	private List<UserDto> userList;
	private Tree tree;
	private int homeFolderNum = HomeNum.homeNum;
	private List<FileDto> files;
	Images images = GWT.create(Images.class);
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
				check = Integer.parseInt((String)result.get("check"));
				if(Integer.parseInt((String)result.get("check")) == 1){
					String compName = (String)result.get("companyName");
					int homeFolderNum = Integer.parseInt((String)result.get("homeFolderNum"));
					UserDto userDto = (UserDto)result.get("userDto");
					Window.alert("로그인 성공");
							
					if(userDto.getAccess() == 0){
						Window.alert("인증대기자 입니다.");
					}
					RootPanel.get().clear();
					
					MainServiceClientImpl main = 
							new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree,compName,homeFolderNum, userDto);
					
					RootPanel.get().add(main.getMainPage());
					
				}else if(Integer.parseInt((String)result.get("check")) == 0){
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
				compList=result;
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
				userList=result;
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
		this.loginAsync.allFiles(new AsyncCallback<List<FileDto>>() {
			@Override
			public void onSuccess(List<FileDto> result) {
				files = result;
				loginAsync.itemTree(new AsyncCallback<FolderDto>() {
					@Override
					public void onSuccess(FolderDto result) {				
						FolderDto homeFolder = result;
						tree = new Tree();
						TreeItem homeItem = new TreeItem();
						homeItem.setText(homeFolder.getName());
						homeItem.setUserObject(homeFolder);
						getTree(homeItem);
						homeItem.setHTML(imageItemHTML(images.treeOpen(), homeItem.getText()));
						loginuser.setTree(tree);
					}
					@Override
					public void onFailure(Throwable caught) {
					}
				});
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
					if(childNode.getType() == 0){
						childItem.setHTML(imageItemHTML(images.treeLeaf(), childItem.getText()));
					}else{
						for(int a=0;a<files.size();a++){
							if(childNode.getItemNum() == files.get(a).getItemNum()){
								if(files.get(a).getFileType().equals("pptx")){
									childItem.setHTML(imageItemHTML(images.ppt(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("txt")){
									childItem.setHTML(imageItemHTML(images.text(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("xlsx")){
									childItem.setHTML(imageItemHTML(images.excel(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("zip")){
									childItem.setHTML(imageItemHTML(images.zip(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("mp3")){
									childItem.setHTML(imageItemHTML(images.mp3(), childItem.getText()));
								}else{
									childItem.setHTML(imageItemHTML(images.files(), childItem.getText()));
								}
							}
						}
					}
					item.addItem(childItem);
					break;
				}
			}else{
				if(itemDto.getItemNum() == homeFolderNum || childItem.getChildCount() == 0){
					if(childNode.getType() == 0){
						childItem.setHTML(imageItemHTML(images.treeLeaf(), childItem.getText()));
					}else{
						for(int a=0;a<files.size();a++){
							if(childNode.getItemNum() == files.get(a).getItemNum()){
								if(files.get(a).getFileType().equals("pptx")){
									childItem.setHTML(imageItemHTML(images.ppt(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("txt")){
									childItem.setHTML(imageItemHTML(images.text(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("xlsx")){
									childItem.setHTML(imageItemHTML(images.excel(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("zip")){
									childItem.setHTML(imageItemHTML(images.zip(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("mp3")){
									childItem.setHTML(imageItemHTML(images.mp3(), childItem.getText()));
								}else{
									childItem.setHTML(imageItemHTML(images.files(), childItem.getText()));
								}
							}
						}
					}
					item.addItem(childItem);
				}
			}
			
		}
		tree.addItem(item);
		
		if(((ItemDto)item.getUserObject()).getItemNum() == homeFolderNum){
			item.setState(true);
		}
	}
	
	public SafeHtml imageItemHTML(ImageResource imageProto, String title) {
		    SafeHtmlBuilder builder = new SafeHtmlBuilder();
		    builder.append(AbstractImagePrototype.create(imageProto).getSafeHtml());
		    builder.appendHtmlConstant(" ");
		    builder.appendEscaped(title);
		    return builder.toSafeHtml();
	}
	public interface Images extends Tree.Resources {
		@Source("MS PowerPoint.png")
	    ImageResource ppt();
		@Source("MS Excel.png")
	    ImageResource excel();
		@Source("Text Box.png")
	    ImageResource text();
		@Source("MP3.png")
	    ImageResource mp3();
		@Source("ZIP.png")
	    ImageResource zip();
		@Source("files.png")
	    ImageResource files();
	    @Override
	    @Source("Folder-48.png")
	    ImageResource treeLeaf();
	    @Override
	    @Source("Open Folder-48.png")
	    ImageResource treeOpen();
	}

	public LoginUser getLoginUser(){
		return this.loginuser;
	}
}
