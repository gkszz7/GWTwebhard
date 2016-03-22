package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.UserListServiceClientImpl;

public class UserList extends Composite{
	
	private final UserListServiceClientImpl userListServiceClientImpl;
	private TextBox textBox;
	private ListBox comboBox;
	private AbsolutePanel absolutePanel;
	private UserDto selected;
	private CellTable<UserDto> cellTable_1;
	private Button btnNewButton_2;
	public UserList(final UserListServiceClientImpl userListServiceClientImpl,final List<UserDto> userList){
		
		this.userListServiceClientImpl = userListServiceClientImpl;
		absolutePanel = new AbsolutePanel();
		initWidget(this.absolutePanel);
		UserListTable(userList);
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
		
		
		absolutePanel_1.add(cellTable_1, 0, 0);
		cellTable_1.setSize("860px", "338px");
		
		Button updateBtn = new Button("New button");
		updateBtn.setText("수정");
		absolutePanel_1.add(updateBtn, 626, 355);
		updateBtn.setSize("85px", "27px");
		
		Button deleteBtn = new Button("New button");
		deleteBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(selected == null){
					Window.alert("회사를 선택해 주세요.");
				}else{
					if(Window.confirm("삭제 하시겠습니까?")){
						userListServiceClientImpl.deleteUser(selected.getUserId());
					}					
				}
			}
		});
		deleteBtn.setText("삭제");
		absolutePanel_1.add(deleteBtn, 729, 355);
		deleteBtn.setSize("85px", "27px");
		
		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("수정");
		btnNewButton_1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		btnNewButton_1.setSize("85px", "29px");
		
		
		
	}
	//사용자목록
		public void UserListTable(List<UserDto> UserList) {

			cellTable_1 = new CellTable<UserDto>();
			cellTable_1.setSkipRowHoverCheck(true);
			cellTable_1.setSize("661px", "221px");

			if (UserList != null) {

				UserlistByCom(UserList);
			}
		}

		public void UserlistByCom(final List<UserDto> UserList) {
			cellTable_1.removeFromParent();
			if (UserList != null) {

				/*
				 * 체크 박스 Column<UserDto, Boolean> select = new Column<UserDto,
				 * Boolean>(new CheckboxCell()) {
				 * 
				 * @Override public Boolean getValue(UserDto object) { // TODO
				 * Auto-generated method stub return false; } };
				 */

				TextColumn<UserDto> UserIdcolumn = new TextColumn<UserDto>() {
					@Override
					public String getValue(UserDto object) {

						return object.getUserId();
					}
				};
				TextColumn<UserDto> UserNameColumn = new TextColumn<UserDto>() {
					@Override
					public String getValue(UserDto object) {

						return object.getUserName();
					}
				};
				TextColumn<UserDto> CompNameColumn = new TextColumn<UserDto>() {
					@Override
					public String getValue(UserDto object) {

						return object.getCompanyName();
					}
				};

				TextColumn<UserDto> UserPhoneColumn = new TextColumn<UserDto>() {
					@Override
					public String getValue(UserDto object) {

						return object.getUserPhone();
					}
				};
				TextColumn<UserDto> UserAddrColumn = new TextColumn<UserDto>() {
					@Override
					public String getValue(UserDto object) {

						return object.getUserAddr();
					}
				};
				// UserTable.addColumn(select);
				cellTable_1.addColumn(UserIdcolumn, "유저ID");
				cellTable_1.addColumn(UserNameColumn, "유저이름");
				cellTable_1.addColumn(CompNameColumn, "회사명");
				cellTable_1.addColumn(UserPhoneColumn, "핸드폰");
				cellTable_1.addColumn(UserAddrColumn, "주소");

				final SingleSelectionModel<UserDto> selectionModel = new SingleSelectionModel<UserDto>();
				cellTable_1.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {

						selected = selectionModel.getSelectedObject();
						
					}
				});

				cellTable_1.setRowCount(UserList.size(), true);
				cellTable_1.setRowData(0, UserList);

			}
		}
}
