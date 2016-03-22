package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.AccessListServiceClientImpl;


public class AccessList extends Composite{
	
	private final AccessListServiceClientImpl serviceImpl;
	private AbsolutePanel absolutePanel;
	private TextBox textBox;
	private ListBox comboBox;
	public AccessList(AccessListServiceClientImpl AccessListserviceImpl,final List<UserDto> AccessList){
		
		this.serviceImpl = AccessListserviceImpl;
		TableList table = new TableList();
		absolutePanel = new AbsolutePanel();
		initWidget(this.absolutePanel);
		
		absolutePanel.setStyleName("login");
		
		absolutePanel.setSize("860px", "539px");
		
		Label lblNewLabel = new Label("사용자 검색");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel, 351, 10);
		lblNewLabel.setSize("173px", "20px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("검색");
		absolutePanel.add(btnNewButton, 587, 96);
		btnNewButton.setSize("85px", "31px");
		
		textBox = new TextBox();
		absolutePanel.add(textBox, 411, 62);
		textBox.setSize("257px", "14px");
		
		comboBox = new ListBox();
		comboBox.addItem("아이디");
		comboBox.addItem("이름");
		comboBox.addItem("회사명");
		comboBox.addItem("전화번호");
		comboBox.setDirectionEstimator(true);
		absolutePanel.add(comboBox, 307, 62);
		comboBox.setSize("78px", "25px");
		
		Label lblNewLabel_1 = new Label("사용자 검색 :");
		lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel_1, 182, 62);
		lblNewLabel_1.setSize("107px", "25px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_1, 0, 143);
		absolutePanel_1.setSize("860px", "396px");
		
		CellTable<Object> cellTable_1 = table.AccessListTable(AccessList);
		absolutePanel_1.add(cellTable_1, 0, 0);
		cellTable_1.setSize("860px", "338px");
		
		Button btnNewButton_3 = new Button("New button");
		btnNewButton_3.setText("인증");
		absolutePanel_1.add(btnNewButton_3, 626, 355);
		btnNewButton_3.setSize("85px", "27px");
		
		Button btnNewButton_4 = new Button("New button");
		btnNewButton_4.setText("취소");
		absolutePanel_1.add(btnNewButton_4, 729, 355);
		btnNewButton_4.setSize("85px", "27px");
		
		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("수정");
		btnNewButton_1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		btnNewButton_1.setSize("85px", "29px");
		
		Button btnNewButton_2 = new Button("New button");
		btnNewButton_2.setText("삭제");
		
		btnNewButton_2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnNewButton_2.setSize("85px", "29px");
		
	}

}
