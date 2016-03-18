package com.webhard.client.GUI;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.cellview.client.CellTable;

public class UserList extends Composite{
	
	
	public UserList(){
		
		AbsolutePanel rootPanel = RootPanel.get();
		rootPanel.add(null);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("login");
		rootPanel.add(absolutePanel, 0, 0);
		absolutePanel.setSize("661px", "157px");
		
		Label lblNewLabel = new Label("사용자 검색");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel, 243, 10);
		lblNewLabel.setSize("173px", "20px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("검색");
		absolutePanel.add(btnNewButton, 511, 99);
		btnNewButton.setSize("85px", "31px");
		
		TextBox textBox = new TextBox();
		absolutePanel.add(textBox, 335, 65);
		textBox.setSize("257px", "14px");
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("아이디");
		comboBox.addItem("이름");
		comboBox.addItem("회사명");
		comboBox.addItem("전화번호");
		comboBox.setDirectionEstimator(true);
		absolutePanel.add(comboBox, 231, 65);
		comboBox.setSize("78px", "45px");
		
		Label lblNewLabel_1 = new Label("사용자 검색 :");
		lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel_1, 106, 65);
		lblNewLabel_1.setSize("107px", "25px");
		
		CellTable<Object> cellTable = new CellTable<Object>();
		cellTable.setSkipRowHoverCheck(true);
		rootPanel.add(cellTable, 0, 159);
		cellTable.setSize("661px", "221px");
		
		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("수정");
		rootPanel.add(btnNewButton_1, 403, 391);
		btnNewButton_1.setSize("85px", "29px");
		
		Button btnNewButton_2 = new Button("New button");
		btnNewButton_2.setText("삭제");
		rootPanel.add(btnNewButton_2, 513, 391);
		btnNewButton_2.setSize("85px", "29px");
		
	}
}
