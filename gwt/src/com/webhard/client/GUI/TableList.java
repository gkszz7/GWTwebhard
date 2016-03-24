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
}
