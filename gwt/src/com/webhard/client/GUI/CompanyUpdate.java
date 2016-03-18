package com.webhard.client.GUI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class CompanyUpdate extends Composite{
	
	
	public CompanyUpdate(){
		
		
		/** 수정 다이얼로그**/
		
		final DialogBox editDialog = new DialogBox();
		
		AbsolutePanel aPanel = new AbsolutePanel();
		aPanel.setStyleName("editFolder");
		editDialog.setWidget(aPanel);
		aPanel.setSize("429px", "430px");
		
		Label nameLabel = new Label("이름");
		nameLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		nameLabel.setDirectionEstimator(true);
		nameLabel.setStyleName("gwt-label");
		aPanel.add(nameLabel, 58, 86);
		nameLabel.setSize("65px", "28px");
		
		TextBox nameText = new TextBox();
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
		
		TextBox phoneText = new TextBox();
		aPanel.add(phoneText, 57, 208);
		phoneText.setSize("284px", "27px");
		
		TextBox addrText = new TextBox();
		aPanel.add(addrText, 57, 292);
		addrText.setSize("284px", "27px");
		
		Label label = new Label("(-는 제외)");
		aPanel.add(label, 351, 212);
		/************************************************/
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("login");
		absolutePanel.setSize("661px", "505px");
		
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
		absolutePanel.add(cellTable, 0, 159);
		cellTable.setSize("661px", "221px");
		
		final Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("수정");
		absolutePanel.add(btnNewButton_1, 403, 391);
		btnNewButton_1.setSize("85px", "29px");
		btnNewButton_1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				editDialog.center();
				btnNewButton_1.setEnabled(false);
			}
		});
		
		final Button btnNewButton_2 = new Button("New button");
		btnNewButton_2.setText("삭제");
		absolutePanel.add(btnNewButton_2, 513, 391);
		btnNewButton_2.setSize("85px", "29px");
		
		
		
	}
}
