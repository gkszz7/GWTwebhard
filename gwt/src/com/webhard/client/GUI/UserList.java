package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.LoginServiceClientImpl;
import com.webhard.client.service.UserListServiceClientImpl;

public class UserList extends Composite{
	
	private final UserListServiceClientImpl userListServiceClientImpl;
	private TextBox textBox,nameBox,phoneBox,addrBox;
	private ListBox comboBox,compBox;
	private AbsolutePanel absolutePanel;
	private UserDto selected;
	private CellTable<UserDto> cellTable_1;
	private DialogBox editDialog;
	private Button updateBtn,btnNewButton1,button;
	
	public UserList(final UserListServiceClientImpl userListServiceClientImpl,final List<UserDto> userList,final List<CompanyDto> compList){
		
		this.userListServiceClientImpl = userListServiceClientImpl;
		absolutePanel = new AbsolutePanel();
		initWidget(this.absolutePanel);
		UserListTable(userList);
		absolutePanel.setStyleName("gwt-absolutePanel-new");
		
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
		Window.addWindowClosingHandler(new ClosingHandler() {
		     @Override
		      public void onWindowClosing(ClosingEvent event) {
		    	 
		    	
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
		
		updateBtn = new Button("New button");
		updateBtn.setText("수정");
		updateBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
					if(selected == null){
						Window.alert("회사를 선택해 주세요.");
					}else{
						editDialog.center();
					}
				}
		});
		

		/********** 회사 수정 다이얼로그 **************/

		editDialog = new DialogBox();
		editDialog.setAnimationEnabled(true);
		AbsolutePanel aPanel = new AbsolutePanel();
		aPanel.setStyleName("gwt-absolutePanel");
		editDialog.setWidget(aPanel);
		aPanel.setSize("500px", "600px");

		Label lblNewLabe2 = new Label("이름");
		lblNewLabe2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		aPanel.add(lblNewLabe2, 33, 56);
		lblNewLabe2.setSize("50px", "18px");
		
		nameBox = new TextBox();
		aPanel.add(nameBox, 33, 87);
		nameBox.setSize("368px", "32px");
		
		Label lblwnth = new Label("주소");
		lblwnth.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		aPanel.add(lblwnth, 33, 158);
		lblwnth.setSize("50px", "18px");
		
		addrBox = new TextBox();
		aPanel.add(addrBox, 33, 189);
		addrBox.setSize("368px", "32px");
		
		Label label_1 = new Label("전화번호");
		label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		aPanel.add(label_1, 33, 265);
		label_1.setSize("62px", "18px");
		
		phoneBox = new TextBox();
		aPanel.add(phoneBox, 33, 296);
		phoneBox.setSize("368px", "32px");
		
		Label label = new Label("회사 선택");
		label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		aPanel.add(label, 33, 364);
		label.setSize("75px", "18px");
		
		compBox = new ListBox();
		compBox.addItem("선택해주세요");
		if(compList !=null){
			for(CompanyDto company : compList){
				
				compBox.addItem(company.getCompanyName());
			}
		}else{
			compBox.addItem("등록 X");
		}
		compBox.setDirectionEstimator(true);
		compBox.setMultipleSelect(false);
		aPanel.add(compBox, 33, 405);
		compBox.setSize("95px", "29px");
		
		btnNewButton1 = new Button("New button");
		btnNewButton1.setText("수정");
		btnNewButton1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
							
				if (nameBox.getText().length() == 0) {
					Window.alert("이름을 입력해 주세요.");
				}  else if (phoneBox.getText().length() == 0) {
					Window.alert("전화번호를 입력해 주세요.");
				} else if (addrBox.getText().length() == 0) {
					Window.alert("주소를 입력해 주세요.");
				}else if(compBox.getItemCount() == 0){
					Window.alert("회사를 선택해주세요.");
				}
				else {
					userListServiceClientImpl.updateUser(selected.getUserId(), nameBox.getText(), phoneBox.getText(), addrBox.getText(), compBox.getValue(compBox.getSelectedIndex()));
				}
			}
		});
		aPanel.add(btnNewButton1, 117, 486);
		btnNewButton1.setSize("85px", "32px");
		
		button = new Button("New button");
		button.setText("취소");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editDialog.hide();
				updateBtn.setEnabled(true);
				updateBtn.setFocus(true);
			}
		});
		aPanel.add(button, 230, 486);
		button.setSize("85px", "32px");	
			
		/************************************************/
		
		absolutePanel_1.add(updateBtn, 626, 355);
		updateBtn.setSize("85px", "27px");
		
		Button deleteBtn = new Button("New button");
		deleteBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(selected == null){
					Window.alert("사용자를 선택해 주세요.");
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
