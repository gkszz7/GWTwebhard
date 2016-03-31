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
import com.webhard.client.service.AccessListServiceClientImpl;


public class AccessList extends Composite{
	
	private final AccessListServiceClientImpl serviceImpl;
	private AbsolutePanel absolutePanel;
	private TextBox textBox;
	private ListBox comboBox;
	private CellTable<UserDto> cellTable_1;
	private UserDto selected;
	public AccessList(AccessListServiceClientImpl AccessListserviceImpl,final List<UserDto> AccessList){
		
		this.serviceImpl = AccessListserviceImpl;
		AccessListTable(AccessList);
		absolutePanel = new AbsolutePanel();
		initWidget(this.absolutePanel);
		
		absolutePanel.setStyleName("gwt-absolutePanel-new");
		
		absolutePanel.setSize("860px", "539px");
		
		Label lblNewLabel = new Label("사용자 검색");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel.add(lblNewLabel, 351, 10);
		lblNewLabel.setSize("173px", "20px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBox.getText() != null){
					if(comboBox.getSelectedIndex() == 1){
						serviceImpl.searchUserById(textBox.getText());
					}else if(comboBox.getSelectedIndex() == 2){
						serviceImpl.searchUserByName(textBox.getText());
					}else if(comboBox.getSelectedIndex() == 3){
						serviceImpl.searchUserBycompany(textBox.getText());
					}else if(comboBox.getSelectedIndex() == 4){
						serviceImpl.searchUserByPhone(textBox.getText());
					}
					else{
						Window.alert("검색 조건을 선택해 주세요.");
					}
				}
			}
		});
		btnNewButton.setText("검색");
		absolutePanel.add(btnNewButton, 587, 96);
		btnNewButton.setSize("85px", "31px");
		
		textBox = new TextBox();
		absolutePanel.add(textBox, 411, 62);
		textBox.setSize("257px", "14px");
		
		comboBox = new ListBox();
		comboBox.addItem("선택해주세요");
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
		absolutePanel_1.setStyleName("gwt-absolutePanel-new");
		absolutePanel.add(absolutePanel_1, 0, 143);
		absolutePanel_1.setSize("860px", "396px");
		
		
		absolutePanel_1.add(cellTable_1, 0, 0);
		cellTable_1.setSize("860px", "338px");
		
		Button btnNewButton_3 = new Button("New button");
		btnNewButton_3.setText("인증");
		btnNewButton_3.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(selected == null){
					Window.alert("사용자를 선택해 주세요.");
				}else{
					if(Window.confirm("인증 하시겠습니까?")){
						serviceImpl.accessUser(selected.getUserId());
					}					
				}
			}
		});
	
		
		absolutePanel_1.add(btnNewButton_3, 626, 355);
		btnNewButton_3.setSize("85px", "27px");
		
		Button btnNewButton_4 = new Button("New button");
		btnNewButton_4.setText("취소");
		btnNewButton_4.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			}
		});		
		absolutePanel_1.add(btnNewButton_4, 729, 355);
		btnNewButton_4.setSize("85px", "27px");
		
		setStyleName("gwt-absolutePanel-new");
	}
	//인증대기자목록
		public void AccessListTable(List<UserDto> AccessList) {

			cellTable_1 = new CellTable<UserDto>();
			cellTable_1.setSkipRowHoverCheck(true);
			cellTable_1.setSize("661px", "221px");

			if (AccessList != null) {

				AccessListByCom(AccessList);
			}
			
		}
		public void AccessListByCom(final List<UserDto> AccessList) {
			cellTable_1.removeFromParent();
			if (AccessList != null) {

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
				TextColumn<UserDto> UserAccessColumn = new TextColumn<UserDto>() {
					@Override
					public String getValue(UserDto object) {
						if(object.getAccess() == 0){
							return "인증대기";
						}else{
							return "인증완료";
						}									
					}
				};
				// UserTable.addColumn(select);
				cellTable_1.addColumn(UserIdcolumn, "유저ID");
				cellTable_1.addColumn(UserNameColumn, "유저이름");
				cellTable_1.addColumn(CompNameColumn, "회사명");
				cellTable_1.addColumn(UserPhoneColumn, "핸드폰");
				cellTable_1.addColumn(UserAddrColumn, "주소");
				cellTable_1.addColumn(UserAccessColumn,"인증상태");

				final SingleSelectionModel<UserDto> selectionModel = new SingleSelectionModel<UserDto>();
				cellTable_1.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {

						selected = selectionModel.getSelectedObject();

					}
				});
				cellTable_1.setRowCount(AccessList.size(), true);
				cellTable_1.setRowData(0, AccessList);
			}
		}
}
