package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;

public class TableList {

	CellTable<FolderDto> cellTable;
	CellTable<UserDto> UserTable;
	CellTable<UserDto> AccessTable;
	CellTable<CompanyDto> CompTable;
	
	//폴더 목록
	public CellTable createTable(List<FolderDto> folderList) {

		cellTable = new CellTable<FolderDto>();
		cellTable.setSkipRowHoverCheck(true);
		cellTable.setSize("661px", "221px");

		if (folderList != null) {

			listByCom(folderList);
		}
		return cellTable;
	}

	public void listByCom(final List<FolderDto> folderList) {
		cellTable.removeFromParent();
		if (folderList != null) {

			TextColumn<FolderDto> FolNamecolumn = new TextColumn<FolderDto>() {
				@Override
				public String getValue(FolderDto object) {

					return object.getName();
				}
			};
			TextColumn<FolderDto> valuesColumn = new TextColumn<FolderDto>() {
				@Override
				public String getValue(FolderDto object) {

					return object.getName();
				}
			};
			TextColumn<FolderDto> DateColumn = new TextColumn<FolderDto>() {
				@Override
				public String getValue(FolderDto object) {

					return object.getDate();
				}
			};
			TextColumn<FolderDto> UseridColumn = new TextColumn<FolderDto>() {
				@Override
				public String getValue(FolderDto object) {

					return object.getUserId();
				}
			};

			cellTable.addColumn(FolNamecolumn, "folderName");
			cellTable.addColumn(valuesColumn, "folder??");
			cellTable.addColumn(DateColumn, "folderDate");
			cellTable.addColumn(UseridColumn, "UserId");

			final SingleSelectionModel<FolderDto> selectionModel = new SingleSelectionModel<FolderDto>();
			cellTable.setSelectionModel(selectionModel);
			selectionModel.addSelectionChangeHandler(new Handler() {
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {

					FolderDto selected = selectionModel.getSelectedObject();

					if (selected != null) {
						Window.alert(selected.getName());
					}
				}
			});

			cellTable.setRowCount(folderList.size(), true);
			cellTable.setRowData(0, folderList);

		}
	}
	
	//인증대기자목록
	public CellTable AccessListTable(List<UserDto> AccessList) {

		AccessTable = new CellTable<UserDto>();
		AccessTable.setSkipRowHoverCheck(true);
		AccessTable.setSize("661px", "221px");

		if (AccessList != null) {

			AccessListByCom(AccessList);
		}
		return AccessTable;
	}
	public void AccessListByCom(final List<UserDto> AccessList) {
		AccessTable.removeFromParent();
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
			AccessTable.addColumn(UserIdcolumn, "유저ID");
			AccessTable.addColumn(UserNameColumn, "유저이름");
			AccessTable.addColumn(CompNameColumn, "회사명");
			AccessTable.addColumn(UserPhoneColumn, "핸드폰");
			AccessTable.addColumn(UserAddrColumn, "주소");
			AccessTable.addColumn(UserAccessColumn,"인증상태");

			final SingleSelectionModel<UserDto> selectionModel = new SingleSelectionModel<UserDto>();
			AccessTable.setSelectionModel(selectionModel);
			selectionModel.addSelectionChangeHandler(new Handler() {
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {

					UserDto selected = selectionModel.getSelectedObject();

					if (selected != null) {
						Window.alert(selected.getUserId());
					}
				}
			});

			AccessTable.setRowCount(AccessList.size(), true);
			AccessTable.setRowData(0, AccessList);

		}
	}

}
