package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.AccessListServiceClientImpl;
import com.webhard.client.service.CompanyServiceClientImpl;
import com.webhard.client.service.MainServiceClientImpl;
import com.webhard.client.service.UserListServiceClientImpl;
import com.webhard.client.service.LoginServiceClientImpl.Images;

public class MainPage extends Composite {
	
	private HorizontalSplitPanel horizontalSplitPanel;
	private final MainServiceClientImpl serviceImpl;
	private AbsolutePanel absolutePanel;
	private CellTable<ItemDto> cellTable = new CellTable<ItemDto>();
	private List<UserDto> userList;
	private List<UserDto> accessList;
	private List<CompanyDto> companys;
	private DialogBox entryCompany,FileDialog, createFolder, editFolder;
	private TextBox nameText;
	private TextBox phoneText;
	private TextBox addrText;
	private String fileName;
	private long filesize;
	private Boolean check;
	private Tree tree;
	
	private TreeItem selectItem;
	private ItemDto selectItemData;
	private ItemDto selected;
	
	//파일 리스트. 폴더 리스트
	
	public MainPage(final MainServiceClientImpl mainServiceClientImpl, Tree getTree, String compName, final int homeNum, final UserDto userDto) {

		absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("gwt-absolutePanel-new");
		initWidget(this.absolutePanel);
		absolutePanel.setSize("1121px", "760px");
		horizontalSplitPanel = new HorizontalSplitPanel();
		horizontalSplitPanel.setStyleName("gwt-Label-new");
		horizontalSplitPanel.setSplitPosition("30%");
		this.absolutePanel.add(horizontalSplitPanel, 0, 51);
		horizontalSplitPanel.setSize("1131px", "631px");
		this.serviceImpl = mainServiceClientImpl;
		this.serviceImpl.UserList();
		this.serviceImpl.compList();
		this.serviceImpl.AccessList();
		
		this.tree = getTree;
		
		CellTable<Object> cellTable = new CellTable<Object>();	
		cellTable.setStyleName("sendButton-new");
		horizontalSplitPanel.setRightWidget(cellTable);
		cellTable.setSize("767px", "100%");
		horizontalSplitPanel.setLeftWidget(tree);
		tree.setSize("313px", "628px");
		
//		if(userDto.getAccess() == 0){
//			for(int i=0;i<tree.getItem(0).getChildCount();i++){
//				TreeItem item = tree.getItem(0).getChild(i);
//				item.removeItems();
//			}			
//		}else{
//			for(int i=0;i<tree.getItem(0).getChildCount();i++){
//				TreeItem item = tree.getItem(0).getChild(i);
//				ItemDto itemDto = (ItemDto)item.getUserObject();
//				if((userDto.getCompanyNum() != itemDto.getCompanyNum()) && userDto.getAdmin() == 0){
//					item.removeItems();
//				}
//			}	
//		}
		MenuBar menuBar = new MenuBar(false);
		menuBar.setStyleName("gwt-MenuBar");
		absolutePanel.add(menuBar, 0, 0);
		menuBar.setSize("1119px", "40px");
		
		MenuBar menuBar_1 = new MenuBar(true);
		final MenuItem folderMenu = new MenuItem("폴더", false, menuBar_1);
		if(userDto.getAccess() == 0){
			folderMenu.setScheduledCommand(new ScheduledCommand() {
	            @Override
	            public void execute() {
	               if(userDto.getAccess() == 0){
	                  folderMenu.setEnabled(false);
	                  Window.alert("인증 후 이용 가능 합니다.");
	               }else{
	            	   
	               }
	            }
	         });
		}
		
		menuBar.addItem(folderMenu);
		menuBar_1.addItem("폴더 생성", new ScheduledCommand() {
			@Override
			public void execute() {
				if(selectItemData != null){
					if(selectItemData.getType() == 0){
						if(selectItemData.getItemNum() != homeNum){
							createFolder = serviceImpl.createFolderBox(getSelectNode().getItemNum(), getSelectNode().getCompanyNum());
							createFolder.center();
						}else{
							Window.alert("HOME폴더는 사용할수없습니다.");
						}
					}else{
						Window.alert("폴더를 선택해주세요.");
					}
				}else{
					Window.alert("폴더를 선택해주세요.");
				}
			}
		});
		menuBar_1.addItem("폴더 수정", new ScheduledCommand() {
			
			@Override
			public void execute() {
				if(selectItemData != null){
					if(selectItemData.getType() == 0){
						if(selectItemData.getItemNum() != homeNum){
						editFolder = serviceImpl.updateFolderBox(getSelectNode().getItemNum());
						editFolder.center();
						}else{
							Window.alert("HOME폴더는 사용할수없습니다.");
						}
					}else{
						Window.alert("폴더를 선택해주세요.");
					}
				}else{
					Window.alert("폴더를 선택해주세요.");
				}
			}
		});
		menuBar_1.addItem("폴더 삭제", new ScheduledCommand() {
			
			@Override
			public void execute() {
				if(selectItemData != null){
					if(selectItemData.getType() == 0){
						if(selectItemData.getItemNum() != homeNum){
							if(Window.confirm("삭제 하시겠습니까?")){
								serviceImpl.deleteFolder(selectItemData.getItemNum());
							}
						}else{
							Window.alert("HOME폴더는 사용할수없습니다.");
						}
						}else{
							Window.alert("폴더를 선택해주세요.");
						}	
					}else{
						Window.alert("폴더를 선택해주세요.");
					}
				}
		});
		
		MenuBar menuBar_3 = new MenuBar(true);				
		final MenuItem fileMenu = new MenuItem("파일", false, menuBar_3);
		if(userDto.getAccess() == 0){
			fileMenu.setScheduledCommand(new ScheduledCommand() {
	            @Override
	            public void execute() {
	               if(userDto.getAccess() == 0){
	            	   fileMenu.setEnabled(false);
	                  Window.alert("인증 후 이용 가능 합니다.");
	               }
	            }
	         });
		}
		
		menuBar_3.addItem("파일 등록",new ScheduledCommand() {
			@Override
			public void execute() {
				int check = selectItemData.getType();
				if(check == 0){
					Window.alert("폴더입니다.");
				}else{
					Window.alert("파일입니다.");
				}
			}
		});
		menuBar.addItem(fileMenu);
		
		if(userDto.getUserId().equals("admin")){
			
			MenuBar menuBar_4 = new MenuBar(true);
			MenuItem userMenu = new MenuItem("사용자", false, menuBar_4);
			menuBar_4.addItem("사용자목록",new ScheduledCommand() {
				
				@Override
				public void execute() {
					
					enterUserList();
				}
			});
			menuBar.addItem(userMenu);
			MenuBar menuBar_5 = new MenuBar(true);
			MenuItem AccessMenu = new MenuItem("인증", false, menuBar_5);
			
			menuBar_5.addItem("인증대기 목록",new ScheduledCommand() {
				
				@Override
				public void execute() {
					
					AccessList();
					
				}
			});
			menuBar.addItem(AccessMenu);
			
			MenuBar menuBar_6 = new MenuBar(true);
			MenuItem compMenu = new MenuItem("회사", false, menuBar_6);
			menuBar.addItem(compMenu);
			menuBar_6.addItem("회사 가입", new ScheduledCommand() {

				@Override
				public void execute() {
					
					entryCompany.center();
					
				}
			});
			
			menuBar_6.addItem("회사 목록", new ScheduledCommand() {

				@Override
				public void execute() {
					
					enterCompList();
					
				}
			});

		}
				
		Button btnNewButton = new Button("New button");
		btnNewButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm("로그아웃 하시겠습니까?")){
					mainServiceClientImpl.logout();
				}
			}
		});
		
		btnNewButton.setText("로그아웃");
		absolutePanel.add(btnNewButton, 1009, 706);
		btnNewButton.setSize("85px", "25px");
		
		Label lblNewLabel = new Label("회사 명 : "+compName);
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel, 838, 713);
		lblNewLabel.setSize("129px", "18px");

		Label lblNewLabel_1 = new Label("아이디 : "+userDto.getUserId());
		lblNewLabel_1
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel_1, 686, 713);
		lblNewLabel_1.setSize("129px", "18px");

		MenuBar menuBar_2 = new MenuBar(true);

		
		/********** 회사 가입 다이얼로그 **************/

		entryCompany = new DialogBox();
		entryCompany.setAnimationEnabled(true);
		AbsolutePanel aPanel = new AbsolutePanel();
		aPanel.setStyleName("gwt-absolutePanel");
		entryCompany.setWidget(aPanel);
		aPanel.setSize("429px", "430px");

		Label nameLabel = new Label("이름");
		nameLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		nameLabel.setDirectionEstimator(true);
		nameLabel.setStyleName("gwt-label");
		aPanel.add(nameLabel, 58, 86);
		nameLabel.setSize("65px", "28px");

		nameText = new TextBox();
		aPanel.add(nameText, 57, 120);
		nameText.setSize("284px", "27px");

		Button createBtn = new Button("New button");
		createBtn.setText("확인");
		aPanel.add(createBtn, 159, 370);
		createBtn.setSize("85px", "29px");
		
		Button cancelBtn = new Button("New button");
		cancelBtn.setText("취소");
		aPanel.add(cancelBtn, 260, 370);
		cancelBtn.setSize("85px", "29px");

		Label phoneLb = new Label("전화번호");
		phoneLb.setStyleName("gwt-label");
		phoneLb.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		phoneLb.setDirectionEstimator(true);
		aPanel.add(phoneLb, 58, 174);
		phoneLb.setSize("65px", "28px");

		Label adLabel = new Label("주소");
		adLabel.setStyleName("gwt-label");
		adLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		adLabel.setDirectionEstimator(true);
		aPanel.add(adLabel, 58, 258);
		adLabel.setSize("65px", "28px");

		phoneText = new TextBox();
		aPanel.add(phoneText, 57, 208);
		phoneText.setSize("284px", "27px");

		addrText = new TextBox();
		aPanel.add(addrText, 57, 292);
		addrText.setSize("284px", "27px");

		Label label1 = new Label("(-는 제외)");
		aPanel.add(label1, 351, 212);

		Button checkBtn = new Button("New button");
		checkBtn.setText("중복");
		aPanel.add(checkBtn, 356, 120);
		checkBtn.setSize("63px", "31px");
		checkBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				boolean check = false;
				for(CompanyDto company : companys){
					if(nameText.getText().equals(company.getCompanyName())){
						check = true;
					}
				}
				
				if (nameText.getText().length() > 0) {
					if(check){
						Window.alert("이미 가입 된 회사입니다.");
					}else{
						Window.alert("가입 가능 한 회사입니다.");
					}
					//serviceImpl.compNameCheck(nameText.getText());
				} else {
					Window.alert("이름를 입력해 주세요.");
				}
			}
		});
		
		createBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean check = false;
				for(CompanyDto company : companys){
					if(nameText.getText().equals(company.getCompanyName())){
						check = true;
					}
				}
				
				if (nameText.getText().length() == 0) {
					Window.alert("회사명을 입력해 주세요.");
				} else if (check) {
					Window.alert("이미 등록 된 회사 입니다.");
				} else if (phoneText.getText().length() == 0) {
					Window.alert("전화번호를 입력해 주세요.");
				} else if (addrText.getText().length() == 0) {
					Window.alert("주소를 입력해 주세요.");
				} else {
					serviceImpl.entryCompany(nameText.getText(), addrText.getText(), phoneText.getText());
				}
			}
		});
		
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				entryCompany.hide();

			}
		});
		
		/*********************************************************/
		
		
		/*************트리 선택 핸들러********************************/
		tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				if(userDto.getAccess()==0){
					Window.alert("인증 후 이용 가능 합니다.");
				}else{
					selectItem = event.getSelectedItem();
					selectItemData = (ItemDto)selectItem.getUserObject();
					
					if(selectItem != null){
						if(selectItemData.getCompanyNum() != 0 || selectItemData.getItemNum() == homeNum){
		                     if(selectItemData.getCompanyNum() != userDto.getCompanyNum() && !userDto.getUserId().equals("admin")){
		                        Window.alert("타 회사는 열람 할 수 없습니다.");
		                     }else{
		                        serviceImpl.ItemInTable(selectItemData.getItemNum());
		                     }
		                }
					}
				}
				
				
			}
		});
		tree.addOpenHandler(new OpenHandler<TreeItem>() {
			@Override
			public void onOpen(OpenEvent<TreeItem> event) {
				if(event.getTarget().getState()){
					Images images = GWT.create(Images.class);
					event.getTarget().setHTML(imageItemHTML(images.treeOpen(), event.getTarget().getText()));
				}
			}
		});
		tree.addCloseHandler(new CloseHandler<TreeItem>() {
			@Override
			public void onClose(CloseEvent<TreeItem> event) {
				if(event.getTarget().getState()){
					Images images = GWT.create(Images.class);
					event.getTarget().setHTML(imageItemHTML(images.treeLeaf(), event.getTarget().getText()));
				}
			}
		});
		/************************************************************/
			
	}
	public void selectUser(List<UserDto> UserList){
		this.userList = UserList;
	}
	
	public void selectAccess(List<UserDto> AccessList){
		this.accessList = AccessList;
	}
	
	public void setCompList(List<CompanyDto> list){
		companys = list;

	}
	public ItemDto getSelectNode(){
		selectItem = tree.getSelectedItem();
		if(selectItem != null)
		selectItemData = (ItemDto)selectItem.getUserObject();
		return selectItemData;
	}
	public void setTable(CellTable<ItemDto> table){
		cellTable = new CellTable<ItemDto>();
		this.cellTable = table;
		horizontalSplitPanel.setRightWidget(cellTable);
		cellTable.setSize("767px", "100%");
		final SingleSelectionModel<ItemDto> selectionModel = new SingleSelectionModel<ItemDto>();
		cellTable.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				selected = selectionModel.getSelectedObject();
				
			}
		});
	}
	
	public void file(){
		/*
		FileUploader file = new FileUploader();
		file.center();
		*/
	}
	public void checkfolder(Boolean check){
		this.check = check;
	}
	public void AccessList(){
		RootPanel.get().clear();
		
		AccessListServiceClientImpl AccessListImpl = new AccessListServiceClientImpl(GWT.getModuleBaseURL()+"AccessList");
		AccessList Accesslist = new AccessList(AccessListImpl, accessList);
		RootPanel.get().add(Accesslist);
	}
	
	public void enterUserList(){
		RootPanel.get().clear();
		
		UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
		UserList userlist = new UserList(userImpl, userList,companys);
		RootPanel.get().add(userlist);

	}
	 
	public void enterCompList(){
		
		RootPanel.get().clear();
		
		CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company",companys);
		CompanyList companyList = new CompanyList(compImpl, companys);
		RootPanel.get().add(companyList);
	}
	public SafeHtml imageItemHTML(ImageResource imageProto, String title) {
	    SafeHtmlBuilder builder = new SafeHtmlBuilder();
	    builder.append(AbstractImagePrototype.create(imageProto).getSafeHtml());
	    builder.appendHtmlConstant(" ");
	    builder.appendEscaped(title);
	    return builder.toSafeHtml();
}
}