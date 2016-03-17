//package com.webhard.client.GUI;
//
//import com.google.gwt.user.client.ui.AbsolutePanel;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.Composite;
//import com.google.gwt.user.client.ui.HasHorizontalAlignment;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.MenuBar;
//import com.google.gwt.user.client.ui.MenuItem;
//import com.google.gwt.user.client.ui.SplitLayoutPanel;
//import com.webhard.client.service.MainSerivceClientImpl;
//
//public class MainPage extends Composite{
//   public MainPage(MainSerivceClientImpl mainServiceClientImpl) {
//      
//      SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
//      initWidget(splitLayoutPanel);
//      splitLayoutPanel.setSize("693px", "473px");
//      
//      MenuBar menuBar = new MenuBar(false);
//      
//      splitLayoutPanel.addNorth(menuBar, 34.0);
//      MenuBar menuBar_1 = new MenuBar(true);
//      
//      
//      MenuItem mntmNewMenu = new MenuItem("폴더", false, menuBar_1);
//      menuBar.addItem(mntmNewMenu);
//      
//      MenuBar menuBar_2 = new MenuBar(true);
//      
//      MenuItem mntmNewMenu_1 = new MenuItem("파일", false, menuBar_2);
//      MenuBar menuBar_3 = new MenuBar(true);
//      
//      MenuItem mntmNewMenu_2 = new MenuItem("인증 대기자 목록", false, menuBar_3);
//      menuBar.addItem(mntmNewMenu_2);
//      MenuBar menuBar_4 = new MenuBar(true);
//      
//      MenuItem mntmNewMenu_3 = new MenuItem("사용자 목록", false, menuBar_4);
//      menuBar.addItem(mntmNewMenu_3);
//      MenuBar menuBar_5 = new MenuBar(true);
//      
//      MenuItem mntmNewMenu_4 = new MenuItem("회사 목록", false, menuBar_5);
//      menuBar.addItem(mntmNewMenu_4);
//      
//      AbsolutePanel absolutePanel = new AbsolutePanel();
//      splitLayoutPanel.addSouth(absolutePanel, 50.0);
//      
//      Button btnNewButton = new Button("New button");
//      btnNewButton.setText("로그아웃");
//      absolutePanel.add(btnNewButton, 583, 10);
//      btnNewButton.setSize("89px", "30px");
//      
//      Label lblNewLabel = new Label("회사 명 : LG");
//      lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//      absolutePanel.add(lblNewLabel, 450, 22);
//      lblNewLabel.setSize("114px", "18px");
//      
//      Label lblNewLabel_1 = new Label("아이디 : test1");
//      lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//      absolutePanel.add(lblNewLabel_1, 331, 22);
//      lblNewLabel_1.setSize("113px", "18px");
//      
//      
//      AbsolutePanel absolutePanel_1 = new AbsolutePanel();
//      splitLayoutPanel.add(absolutePanel_1); 
//   }
//}