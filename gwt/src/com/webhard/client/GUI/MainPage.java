package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionModel.AbstractSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.service.CompanyServiceClientImpl;
import com.webhard.client.service.MainServiceClientImpl;

public class MainPage extends Composite {

	private final MainServiceClientImpl serviceImpl;
	private AbsolutePanel absolutePanel = new AbsolutePanel();
	private List<CompanyDto> companys;

	//파일 리스트. 폴더 리스트
	public MainPage(final MainServiceClientImpl mainServiceClientImpl,List<FolderDto> folderList,FolderDto homefolder) {

		initWidget(this.absolutePanel);
		absolutePanel.setSize("857px", "514px");
		HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
		this.absolutePanel.add(horizontalSplitPanel, 0, 46);
		horizontalSplitPanel.setSize("857px", "427px");
		this.serviceImpl = mainServiceClientImpl;
		this.serviceImpl.compList();

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
		cellTable.setSize("420px", "100%");

		MenuBar menuBar = new MenuBar(false);
		menuBar.setStyleName("gwt-MenuBar");
		absolutePanel.add(menuBar, 0, 0);
		menuBar.setSize("857px", "40px");
		MenuBar menuBar_1 = new MenuBar(true);

		MenuItem folderMenu = new MenuItem("폴더", false, menuBar_1);
		menuBar.addItem(folderMenu);
		MenuBar menuBar_3 = new MenuBar(true);

		MenuItem fileMenu = new MenuItem("파일", false, menuBar_3);
		menuBar.addItem(fileMenu);
		MenuBar menuBar_4 = new MenuBar(true);

		MenuItem userMenu = new MenuItem("사용자", false, menuBar_4);
		menuBar.addItem(userMenu);
		MenuBar menuBar_5 = new MenuBar(true);

		MenuItem AccessMenu = new MenuItem("인증", false, menuBar_5);
		menuBar.addItem(AccessMenu);
		MenuBar menuBar_6 = new MenuBar(true);

		MenuItem compMenu = new MenuItem("회사", false, menuBar_6);

		
		menuBar_6.addItem("회사 가입", new ScheduledCommand() {

			@Override
			public void execute() {
				
				Window.alert("asd");
			}
		});

		menuBar.addItem(compMenu);

		Button btnNewButton = new Button("New button");
		btnNewButton.setText("로그아웃");
		absolutePanel.add(btnNewButton, 762, 479);
		btnNewButton.setSize("85px", "25px");

		Label lblNewLabel = new Label("회사 명 : 제네지");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel, 609, 486);
		lblNewLabel.setSize("111px", "18px");

		Label lblNewLabel_1 = new Label("아이디 : test1");
		lblNewLabel_1
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel_1, 463, 486);
		lblNewLabel_1.setSize("116px", "18px");

		MenuBar menuBar_2 = new MenuBar(true);

		MenuItem mntmNewMenu_1 = new MenuItem("파일", false, menuBar_2);
		
		menuBar_6.addItem("회사 목록", new ScheduledCommand() {

			@Override
			public void execute() {
				
				enterCompList();
			}
		});

	}
	public void setCompList(List<CompanyDto> list){
		companys = list;

	}
	public void enterCompList(){
		
		RootPanel.get().clear();
		
		CompanyServiceClientImpl compImpl = new CompanyServiceClientImpl(GWT.getModuleBaseURL()+"company",companys);
		CompanyList companyList = new CompanyList(compImpl, companys);
		RootPanel.get().add(companyList);
	}
}