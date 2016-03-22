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
import com.webhard.client.service.UserListServiceClientImpl;

public class UserList extends Composite{
	
	private final UserListServiceClientImpl userListServiceClientImpl;
	private TextBox textBox;
	private ListBox comboBox;
	private AbsolutePanel absolutePanel;
	private UserDto selectuser;
	
	public UserList(final UserListServiceClientImpl userListServiceClientImpl,final List<UserDto> userList){
		
		this.userListServiceClientImpl = userListServiceClientImpl;
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
		comboBox.addItem("선택");
		comboBox.addItem("아이디");
		comboBox.addItem("이름");
		comboBox.addItem("회사명");
		comboBox.addItem("전화번호");
		comboBox.setDirectionEstimator(true);
		absolutePanel.add(comboBox, 307, 62);
		comboBox.setSize("78px", "25px");
		btnNewButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(textBox.getText() != null){
					if(comboBox.getSelectedIndex() == 1){
						System.out.println(comboBox.getSelectedIndex());
						System.out.println(textBox.getText());
						userListServiceClientImpl.searchUserById(textBox.getText());
					}else if(comboBox.getSelectedIndex() == 2){
						userListServiceClientImpl.searchUserByName(textBox.getText());
					}else if(comboBox.getSelectedIndex() == 3){
						userListServiceClientImpl.searchUserBycompany(textBox.getText());
					}else if(comboBox.getSelectedIndex() == 4){
						userListServiceClientImpl.searchUserByPhone(textBox.getText());
					}
					else{
						Window.alert("검색 조건을 선택해 주세요.");
					}
				}
				
			}
		});
		Label lblNewLabel_1 = new Label("사용자 검색 :");
		lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel_1, 182, 62);
		lblNewLabel_1.setSize("107px", "25px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_1, 0, 143);
		absolutePanel_1.setSize("860px", "396px");
		
		CellTable<Object> cellTable_1 = table.UserListTable(userList);
		absolutePanel_1.add(cellTable_1, 0, 0);
		cellTable_1.setSize("860px", "338px");
		
		Button btnNewButton_3 = new Button("New button");
		btnNewButton_3.setText("수정");
		absolutePanel_1.add(btnNewButton_3, 626, 355);
		btnNewButton_3.setSize("85px", "27px");
		
		Button btnNewButton_4 = new Button("New button");
		btnNewButton_4.setText("삭제");
		absolutePanel_1.add(btnNewButton_4, 729, 355);
		btnNewButton_4.setSize("85px", "27px");
		
		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("수정");
		btnNewButton_1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		btnNewButton_1.setSize("85px", "29px");
		
		Button btnNewButton_2 = new Button("New button");
		btnNewButton_2.setText("삭제");
		
		btnNewButton_2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				if(selectuser == null){
					Window.alert("회사를 선택해 주세요.");
				}else{
					if(Window.confirm("삭제 하시겠습니까?")){
						userListServiceClientImpl.deleteUser(selectuser.getUserId());
					}					
				}
			}
		});
		
		btnNewButton_2.setSize("85px", "29px");
		
	}

}
