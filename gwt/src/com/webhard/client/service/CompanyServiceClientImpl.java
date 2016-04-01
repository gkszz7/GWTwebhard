package com.webhard.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.webhard.client.GUI.CompanyList;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.MainServiceClientImpl.Images;


public class CompanyServiceClientImpl implements CompanyServiceClientInt{
	
	private CompanyServiceAsync companyAsync;
	private CompanyList company;
	private List<CompanyDto> companys;
	private List<FileDto> files;
	Images images = GWT.create(Images.class);
	private int homeFolderNum = HomeNum.homeNum;
	private Tree tree;
	
	public CompanyServiceClientImpl(String url, List<CompanyDto> comps, Tree tree, List<FileDto> files) {
		
		this.companyAsync = GWT.create(CompanyService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.companyAsync;
		endPoint.setServiceEntryPoint(url);
		this.tree = tree;
		this.files=files;
		this.company = new CompanyList(this, comps);
	}
	
	@Override
	public void compNameCheck(String name) {
		this.companyAsync.compNameCheck(name, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result == true){
					Window.alert("존재하는 회사명 입니다.");
				}else{
					Window.alert("수정 가능 한 회사명 입니다.");
				}
				company.setNameCheck(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void updateCompany(String basicName, String name, String phone,
			String addr) {
		this.companyAsync.updateCompany(basicName, name, phone, addr, new AsyncCallback<List<CompanyDto>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("company edit error");
			}
			@Override
			public void onSuccess(List<CompanyDto> result) {
				Window.alert("수정 되었습니다.");
				
				RootPanel.get().clear();
				
				CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company", result,tree,files);
				RootPanel.get().add(compImpl.getCompanyList());
			}
		});
	}
	@Override
	public void deleteCompany(String name) {
		this.companyAsync.deleteCompany(name, new AsyncCallback<List<CompanyDto>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("company delete error");
			}
			@Override
			public void onSuccess(List<CompanyDto> result) {
				Window.alert("삭제되었습니다.");
				
				RootPanel.get().clear();
				CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company", result,tree,files);
				RootPanel.get().add(compImpl.getCompanyList());
			}
		});
	}
	@Override
	public void searchCompByName(String name) {
		this.companyAsync.searchCompByName(name, new AsyncCallback<List<CompanyDto>>() {
			@Override
			public void onSuccess(List<CompanyDto> result) {
				RootPanel.get().clear();
				CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company", result,tree,files);
				RootPanel.get().add(compImpl.getCompanyList());
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error");
			}
		});
	}
	@Override
	public void searchCompByAddr(String addr) {
		this.companyAsync.searchCompByAddr(addr, new AsyncCallback<List<CompanyDto>>() {
			@Override
			public void onSuccess(List<CompanyDto> result) {
				RootPanel.get().clear();
				CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company", result,tree,files);
				RootPanel.get().add(compImpl.getCompanyList());
			}
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	@Override
	public void searchCompByPhone(String phone) {
		this.companyAsync.searchCompByPhone(phone, new AsyncCallback<List<CompanyDto>>() {
			@Override
			public void onSuccess(List<CompanyDto> result) {
				RootPanel.get().clear();
				CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company", result,tree,files);
				RootPanel.get().add(compImpl.getCompanyList());
			}
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	@Override
	public void goMain() {
		this.companyAsync.getUser(new AsyncCallback<UserDto>() {
			
			@Override
			public void onSuccess(UserDto result) {
				final UserDto user = result;
				companyAsync.goMain(new AsyncCallback<HashMap<String,Object>>() {
					
					@Override
					public void onSuccess(HashMap<String, Object> result) {
						String compName = (String)result.get("companyName");
						int homeFolderNum = Integer.parseInt((String)result.get("homeFolderNum"));
						UserDto userDto = (UserDto)result.get("userDto");
						
						RootPanel.get().clear();
						
						MainServiceClientImpl main = 
								new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree,compName,homeFolderNum, user);
						
						RootPanel.get().add(main.getMainPage());
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
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
		@Source("Folder-48.png")
	    ImageResource ppt();
		@Source("Folder-48.png")
	    ImageResource excel();
		@Source("Folder-48.png")
	    ImageResource text();
		@Source("Folder-48.png")
	    ImageResource mp3();
		@Source("Folder-48.png")
	    ImageResource zip();
		@Source("Folder-48.png")
	    ImageResource files();
	    @Override
	    @Source("Folder-48.png")
	    ImageResource treeLeaf();
	    @Override
	    @Source("Open Folder-48.png")
	    ImageResource treeOpen();
	}
	
	
	public CompanyList getCompanyList(){
		//companys = list;
		return this.company;
	}
}
