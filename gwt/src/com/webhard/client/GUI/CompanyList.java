package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.cellview.client.CellTable;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.service.CompanyServiceClientImpl;

public class CompanyList extends Composite{
	private CompanyServiceClientImpl serviceImpl;
	private DialogBox editDialog;
	
	public CompanyList(final CompanyServiceClientImpl compImpl, final List<CompanyDto> companys){
		
		this.serviceImpl = compImpl;
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setStyleName("login");
		
		absolutePanel.setSize("667px", "503px");
		
		Label label = new Label("회사 목록");
		label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(label, 243, 10);
		label.setSize("173px", "20px");
		
		Button button = new Button("New button");
		button.setText("검색");
		absolutePanel.add(button, 511, 99);
		button.setSize("85px", "31px");
		
		TextBox textBox = new TextBox();
		absolutePanel.add(textBox, 335, 65);
		textBox.setSize("257px", "14px");
		
		ListBox listBox = new ListBox();
		listBox.setDirectionEstimator(true);
		absolutePanel.add(listBox, 251, 65);
		listBox.setSize("78px", "45px");
		
		CellTable<CompanyDto> cellTable = new CellTable<CompanyDto>();
		cellTable.setSkipRowHoverCheck(true);
		absolutePanel.add(cellTable, 0, 159);
		cellTable.setSize("661px", "221px");
		
		final Button button_1 = new Button("New button");
		button_1.setText("수정");
		absolutePanel.add(button_1, 403, 391);
		button_1.setSize("85px", "29px");
		button_1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				editDialog.center();				
			}
		});
		
		Button button_2 = new Button("New button");
		button_2.setText("삭제");
		absolutePanel.add(button_2, 513, 391);
		button_2.setSize("85px", "29px");

		/** 수정 다이얼로그**/
		
//		editDialog = new DialogBox();
//		editDialog.setAnimationEnabled(true);
//		AbsolutePanel aPanel = new AbsolutePanel();
//		aPanel.setStyleName("gwt-absolutePanel");
//		editDialog.setWidget(aPanel);
//		aPanel.setSize("429px", "430px");
//		
//		Label nameLabel = new Label("이름");
//		nameLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		nameLabel.setDirectionEstimator(true);
//		nameLabel.setStyleName("gwt-label");
//		aPanel.add(nameLabel, 58, 86);
//		nameLabel.setSize("65px", "28px");
//		
//		TextBox nameText = new TextBox();
//		aPanel.add(nameText, 57, 120);
//		nameText.setSize("284px", "27px");
//		
//		Button createBtn = new Button("New button");
//		createBtn.setText("수정");
//		aPanel.add(createBtn, 159, 370);
//		createBtn.setSize("85px", "29px");
//		
//		Button cancelBtn = new Button("New button");
//		cancelBtn.setText("취소");
//		aPanel.add(cancelBtn, 260, 370);
//		cancelBtn.setSize("85px", "29px");
//		
//		Label phoneLb = new Label("전화번호");
//		phoneLb.setStyleName("gwt-label");
//		phoneLb.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		phoneLb.setDirectionEstimator(true);
//		aPanel.add(phoneLb, 58, 174);
//		phoneLb.setSize("65px", "28px");
//		
//		Label adLabel = new Label("주소");
//		adLabel.setStyleName("gwt-label");
//		adLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		adLabel.setDirectionEstimator(true);
//		aPanel.add(adLabel, 58, 258);
//		adLabel.setSize("65px", "28px");
//		
//		TextBox phoneText = new TextBox();
//		aPanel.add(phoneText, 57, 208);
//		phoneText.setSize("284px", "27px");
//		
//		TextBox addrText = new TextBox();
//		aPanel.add(addrText, 57, 292);
//		addrText.setSize("284px", "27px");
//		
//		Label label1 = new Label("(-는 제외)");
//		aPanel.add(label1, 351, 212);
//		
//		cancelBtn.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				editDialog.hide();
//				button_1.setEnabled(true);
//				button_1.setFocus(true);
//			}
//		});
		/************************************************/
		
	}
}
