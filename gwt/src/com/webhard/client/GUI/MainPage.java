package com.webhard.client.GUI;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.cell.client.TextCell;
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
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionModel.AbstractSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.webhard.client.model.FolderDto;
import com.webhard.client.service.MainSerivceClientImpl;

public class MainPage extends Composite{
	
	private final MainSerivceClientImpl serviceImpl;
	AbsolutePanel absolutePanel;
	
	//파일 리스트. 폴더 리스트
	public MainPage(MainSerivceClientImpl mainServiceClientImpl,List<FolderDto> folderList,FolderDto homefolder) {
		
		this.serviceImpl = mainServiceClientImpl;
		
		absolutePanel = new AbsolutePanel();
		initWidget(this.absolutePanel);
		absolutePanel.setSize("857px", "514px"); 

		HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
		this.absolutePanel.add(horizontalSplitPanel, 0, 46);
		horizontalSplitPanel.setSize("857px", "427px");
		
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
		cellTree.setSize("416px", "100%");
		
		CellTable<Object> cellTable = new CellTable<Object>();
		horizontalSplitPanel.setRightWidget(cellTable);
		cellTable.setSize("420px", "100%");
		
		MenuBar menuBar = new MenuBar(false);
		absolutePanel.add(menuBar, 0, 0);
		menuBar.setSize("857px", "40px");
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
		btnNewButton.setText("로그아웃");
		absolutePanel.add(btnNewButton, 762, 479);
		btnNewButton.setSize("85px", "25px");				
		
		Label lblNewLabel = new Label("회사 명 : 제네지");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel, 609, 486);
		lblNewLabel.setSize("111px", "18px");
		
		Label lblNewLabel_1 = new Label("아이디 : test1");
		lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel_1, 463, 486);
		lblNewLabel_1.setSize("116px", "18px");
		
		MenuBar menuBar_2 = new MenuBar(true);
		
		MenuItem mntmNewMenu_1 = new MenuItem("파일", false, menuBar_2);		
		Window.addWindowClosingHandler(new ClosingHandler() {
            @Override
             public void onWindowClosing(ClosingEvent event) {
            
             }
      });
      Window.addCloseHandler(new CloseHandler<Window>() {
		
		@Override
		public void onClose(CloseEvent<Window> event) {
			// TODO Auto-generated method stub
			
		}
	});		
	}
}
