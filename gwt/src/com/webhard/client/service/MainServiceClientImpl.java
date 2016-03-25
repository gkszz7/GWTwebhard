package com.webhard.client.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.webhard.client.GUI.MainPage;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;

public class MainServiceClientImpl implements MainServiceClientInt {

	private MainServiceAsync mainAsync;
	private MainPage main;
	private  DialogBox FileDialog;
	private Tree tree;

	public MainServiceClientImpl(String url, Tree getTree) {

		this.mainAsync = GWT.create(MainService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget) this.mainAsync;
		endPoint.setServiceEntryPoint(url);
		this.tree = getTree;
		this.main = new MainPage(this, getTree);
	}

	@Override
	public void compList() {

		this.mainAsync.compList(new AsyncCallback<List<CompanyDto>>() {

			@Override
			public void onSuccess(List<CompanyDto> result) {
				main.setCompList(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});

	}
	@Override
	public void entryCompany(String name, String addr, String phone) {
		this.mainAsync.entryCompany(name, addr, phone,
				new AsyncCallback<ItemDto>() {

					@Override
					public void onSuccess(ItemDto result) {
						Window.alert("회사가 추가되었습니다.");
						
						tree = new Tree();
						TreeItem homeItem = new TreeItem();
						homeItem.setText(result.getName());
						homeItem.setUserObject(result);
						getTree(homeItem);
						
						RootPanel.get().clear();

						MainServiceClientImpl main = new MainServiceClientImpl(
								GWT.getModuleBaseURL() + "Main", tree);

						RootPanel.get().add(main.getMainPage());
					}

					@Override
					public void onFailure(Throwable caught) {

					}
				});

	}
	@Override
	public void UserList() {

		this.mainAsync.UserList(new AsyncCallback<List<UserDto>>() {

			@Override
			public void onSuccess(List<UserDto> result) {
				main.selectUser(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void AccessList() {

		this.mainAsync.AccessList(new AsyncCallback<List<UserDto>>() {

			@Override
			public void onSuccess(List<UserDto> result) {
				// TODO Auto-generated method stub
				main.selectAccess(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void createFolder(String name, int parentNum, int companyNum) {
		this.mainAsync.createFolder(name, parentNum, companyNum, new AsyncCallback<ItemDto>() {
			@Override
			public void onSuccess(ItemDto result) {
				
			}
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	@Override
	public void updateFolder(String name, int itemNum) {
		this.mainAsync.updateFolder(name, itemNum, new AsyncCallback<ItemDto>() {
			@Override
			public void onSuccess(ItemDto result) {
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	@Override
	public void deleteFolder(int itemNum) {
		this.mainAsync.deleteFolder(itemNum, new AsyncCallback<ItemDto>() {
			@Override
			public void onSuccess(ItemDto result) {
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}

	/*************************트리 생성***********************/
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
				}
			}else{
				if(itemDto.getItemNum() == 140 || childItem.getChildCount() == 0){
					item.addItem(childItem);
				}
			}
			
		}
		tree.addItem(item);
	}
	/**********************************************************/
	/************************파일 업로드 다이얼로그*******************/
	public DialogBox fileUpload(){
		FileDialog = new DialogBox();

		AbsolutePanel absolutePanel = new AbsolutePanel();
		FileDialog.setWidget(absolutePanel);
		absolutePanel.setSize("371px", "287px");

		HTML html = new HTML("FileUpload");
		absolutePanel.add(html, 10, 10);
		html.setSize("300px", "20px");

		final FileUpload fileUpload = new FileUpload();
		fileUpload.ensureDebugId("cwFileUpload");
		absolutePanel.add(fileUpload, 10, 107);
		fileUpload.setSize("349px", "25px");

		HTML html_1 = new HTML("<br>");
		absolutePanel.add(html_1, 10, 155);
		html_1.setSize("300px", "20px");
		
		Button button = new Button("FileUpload");
		button.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				String filename = fileUpload.getFilename();
				if (filename.length() == 0) {
					Window.alert("Error");
				} else {
					Window.alert("Success");
				}
			}
		});
		absolutePanel.add(button, 10, 249);
		button.setSize("85px", "25px");
		
		Button button_1 = new Button("Cancel");
		button_1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				FileDialog.hide();
			}
		});
		absolutePanel.add(button_1, 121, 249);
		button_1.setSize("64px", "25px");

		return FileDialog;
	}
	/**********************************************************/
	/*************************폴더 관련 다이얼로그 ****************/
	public DialogBox createFolderBox(){
		DialogBox createFolderBox = new DialogBox();
		createFolderBox.setAnimationEnabled(true);
		AbsolutePanel aPanel = new AbsolutePanel();
		aPanel.setStyleName("gwt-absolutePanel");
		createFolderBox.setWidget(aPanel);
		aPanel.setSize("397px", "325px");
		
		Label label = new Label("폴더 이름");
		aPanel.add(label, 39, 100);
		label.setSize("92px", "32px");
		
		TextBox textBox = new TextBox();
		aPanel.add(textBox, 39, 138);
		textBox.setSize("316px", "28px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("확 인");
		aPanel.add(btnNewButton, 73, 231);
		btnNewButton.setSize("92px", "41px");
		
		Button button = new Button("New button");
		button.setText("취 소");
		aPanel.add(button, 242, 231);
		button.setSize("92px", "41px");
		
		return createFolderBox;
	}
	
	/**********************************************************/
	public MainPage getMainPage() {

		return this.main;
	}

}
