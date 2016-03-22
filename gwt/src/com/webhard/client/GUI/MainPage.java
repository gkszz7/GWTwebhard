package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
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
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionModel.AbstractSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.AccessListServiceClientImpl;
import com.webhard.client.service.CompanyServiceClientImpl;
import com.webhard.client.service.MainServiceClientImpl;
import com.webhard.client.service.UserListServiceClientImpl;

public class MainPage extends Composite {

	private final MainServiceClientImpl serviceImpl;
	private AbsolutePanel absolutePanel;
	private CellTable<FolderDto> cellTable;
	private List<UserDto> userList;
	private List<UserDto> accessList;
	private TableList table = new TableList();
	private List<CompanyDto> companys;
	private DialogBox entryCompany;
	private TextBox nameText;
	private TextBox phoneText;
	private TextBox addrText;

	//파일 리스트. 폴더 리스트
	public MainPage(final MainServiceClientImpl mainServiceClientImpl,List<FolderDto> folderList,FolderDto homefolder,final List<CompanyDto> compList) {

		absolutePanel = new AbsolutePanel();
		initWidget(this.absolutePanel);
		absolutePanel.setSize("1121px", "760px");
		HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
		this.absolutePanel.add(horizontalSplitPanel, 10, 51);
		horizontalSplitPanel.setSize("1121px", "631px");
		this.serviceImpl = mainServiceClientImpl;
		this.serviceImpl.UserList();
		this.serviceImpl.compList();
		this.serviceImpl.AccessList();
		
		CellTree cellTree = new CellTree(new TreeViewModel() {
			final AbstractDataProvider<String> dataProvider = new ListDataProvider<String>();
			final AbstractSelectionModel<String> selectionModel = new NoSelectionModel<String>();

			@Override
			public <T> NodeInfo<?> getNodeInfo(T value) {
				return new DefaultNodeInfo<String>(dataProvider,
						new TextCell(), selectionModel, null);
			}

			@Override
			public boolean isLeaf(Object value) {
				return true;
			}
		}, null);

		horizontalSplitPanel.setLeftWidget(cellTree);
		cellTree.setSize("100%", "100%");

		CellTable<Object> cellTable = new CellTable<Object>();
		horizontalSplitPanel.setRightWidget(cellTable);
		cellTable.setSize("545px", "100%");

		MenuBar menuBar = new MenuBar(false);
		menuBar.setStyleName("gwt-MenuBar");
		absolutePanel.add(menuBar, 0, 0);
		menuBar.setSize("1119px", "40px");
		MenuBar menuBar_1 = new MenuBar(true);

		MenuItem folderMenu = new MenuItem("폴더", false, menuBar_1);
		menuBar.addItem(folderMenu);
		MenuBar menuBar_3 = new MenuBar(true);

		MenuItem fileMenu = new MenuItem("파일", false, menuBar_3);
		menuBar.addItem(fileMenu);
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

		Button btnNewButton = new Button("New button");
		btnNewButton.setText("로그아웃");
		absolutePanel.add(btnNewButton, 1009, 706);
		btnNewButton.setSize("85px", "25px");

		Label lblNewLabel = new Label("회사 명 : 제네지");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel, 838, 713);
		lblNewLabel.setSize("129px", "18px");

		Label lblNewLabel_1 = new Label("아이디 : test1");
		lblNewLabel_1
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel_1, 686, 713);
		lblNewLabel_1.setSize("129px", "18px");

		MenuBar menuBar_2 = new MenuBar(true);

		MenuItem mntmNewMenu_1 = new MenuItem("파일", false, menuBar_2);
		
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
		createBtn.setText("수정");
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
				for(CompanyDto company : compList){
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
				for(CompanyDto company : compList){
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
					//serviceImpl.updateCompany(selected.getCompanyName(), nameText.getText(), phoneText.getText(), addrText.getText());
				}
			}
		});
		
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				entryCompany.hide();

			}
		});
		
		/*********************************************************/
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
	public void AccessList(){
		RootPanel.get().clear();
		
		AccessListServiceClientImpl AccessListImpl = new AccessListServiceClientImpl(GWT.getModuleBaseURL()+"AccessList");
		AccessList Accesslist = new AccessList(AccessListImpl, accessList);
		RootPanel.get().add(Accesslist);
	}
	
	public void enterUserList(){
		RootPanel.get().clear();
		
		UserListServiceClientImpl userImpl = new UserListServiceClientImpl(GWT.getModuleBaseURL()+"UserList");
		UserList userlist = new UserList(userImpl, userList);
		RootPanel.get().add(userlist);

	}
	
	public void enterCompList(){
		
		RootPanel.get().clear();
		
		CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company",companys);
		CompanyList companyList = new CompanyList(compImpl, companys);
		RootPanel.get().add(companyList);
	}
}