package com.webhard.client.GUI;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webhard.client.service.MainSerivceClientImpl;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel.AbstractSelectionModel;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;
import com.google.gwt.view.client.TreeViewModel.DefaultNodeInfo;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Command;

public class MainPage extends Composite{
	
	private final MainSerivceClientImpl serviceImpl;
	AbsolutePanel absolutePanel;
	public MainPage(MainSerivceClientImpl mainServiceClientImpl) {
		
		this.serviceImpl = mainServiceClientImpl;
		
		absolutePanel = new AbsolutePanel();
		initWidget(this.absolutePanel);
		
		HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
		absolutePanel.add(horizontalSplitPanel, 0, 46);
		horizontalSplitPanel.setSize("669px", "364px");
		
		CellTree cellTree = new CellTree(
			new TreeViewModel() {
				final AbstractDataProvider<String> dataProvider = new ListDataProvider<String>();
				final AbstractSelectionModel<String> selectionModel = new NoSelectionModel<String>();
				@Override
				public <T> NodeInfo<?> getNodeInfo(T value) {
					return new DefaultNodeInfo<String>(dataProvider, new TextCell(), selectionModel, null);
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
		cellTable.setSize("100%", "100%");
		
		MenuBar menuBar = new MenuBar(false);
		absolutePanel.add(menuBar, 0, 0);
		menuBar.setSize("669px", "40px");
		MenuBar menuBar_1 = new MenuBar(true);
		
		MenuItem mntmNewMenu = new MenuItem("New menu", false, menuBar_1);
		menuBar.addItem(mntmNewMenu);
		MenuBar menuBar_3 = new MenuBar(true);
		
		MenuItem mntmNewMenu_2 = new MenuItem("New menu", false, menuBar_3);
		menuBar.addItem(mntmNewMenu_2);
		MenuBar menuBar_4 = new MenuBar(true);
		
		MenuItem menuItem = new MenuItem("New menu", false, menuBar_4);
		menuBar.addItem(menuItem);
		MenuBar menuBar_5 = new MenuBar(true);
		
		MenuItem mntmNewMenu_3 = new MenuItem("New menu", false, menuBar_5);
		menuBar.addItem(mntmNewMenu_3);
		MenuBar menuBar_6 = new MenuBar(true);
		
		MenuItem mntmNewMenu_4 = new MenuItem("New menu", false, menuBar_6);
		menuBar.addItem(mntmNewMenu_4);
		
		Button btnNewButton = new Button("New button");
		absolutePanel.add(btnNewButton, 552, 416);
		btnNewButton.setSize("85px", "25px");
		
		Label lblNewLabel = new Label("회사 명 : 제네지");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel, 414, 423);
		lblNewLabel.setSize("111px", "18px");
		
		Label lblNewLabel_1 = new Label("아이디 : test1");
		lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel_1, 287, 423);
		lblNewLabel_1.setSize("116px", "18px");
		
		MenuBar menuBar_2 = new MenuBar(true);
		
		MenuItem mntmNewMenu_1 = new MenuItem("파일", false, menuBar_2);

	}
}
