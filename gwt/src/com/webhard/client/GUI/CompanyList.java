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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.service.CompanyServiceClientImpl;

public class CompanyList extends Composite {
	private final CompanyServiceClientImpl serviceImpl;
	private boolean nameCheck;
	private TextBox nameText;
	private TextBox phoneText;
	private TextBox addrText;
	private DialogBox editDialog;
	private AbsolutePanel absolutePanel;
	private CellTable<CompanyDto> cellTable;
	private CompanyDto selected;

	public CompanyList(final CompanyServiceClientImpl compImpl,
			final List<CompanyDto> companys) {

		absolutePanel = new AbsolutePanel();
		initWidget(this.absolutePanel);
		absolutePanel.setStyleName("login");
		this.serviceImpl = compImpl;
		
		createTable(companys);
		absolutePanel.add(cellTable, 0, 159);
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

		final Button button_1 = new Button("New button");
		button_1.setText("수정");
		absolutePanel.add(button_1, 403, 391);
		button_1.setSize("85px", "29px");
		button_1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(selected == null){
					Window.alert("회사를 선택해 주세요.");
				}else{
					editDialog.center();
				}
			}
		});

		Button button_2 = new Button("New button");
		button_2.setText("삭제");
		absolutePanel.add(button_2, 513, 391);
		button_2.setSize("85px", "29px");
		button_2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(selected == null){
					Window.alert("회사를 선택해 주세요.");
				}else{
					
				}
			}
		});

		/** 수정 다이얼로그 **/

		editDialog = new DialogBox();
		editDialog.setAnimationEnabled(true);
		AbsolutePanel aPanel = new AbsolutePanel();
		aPanel.setStyleName("gwt-absolutePanel");
		editDialog.setWidget(aPanel);
		aPanel.setSize("429px", "430px");

		Label nameLabel = new Label("이름");
		nameLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		nameLabel.setDirectionEstimator(true);
		nameLabel.setStyleName("gwt-label");
		aPanel.add(nameLabel, 58, 86);
		nameLabel.setSize("65px", "28px");

		nameText = new TextBox();
		aPanel.add(nameText, 57, 120);
		nameText.setSize("284px", "27px");

		Button createBtn = new Button("New button");
		createBtn.setText("수정");
		aPanel.add(createBtn, 159, 370);
		createBtn.setSize("85px", "29px");
		createBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("2."+getNameCheck());
				if (nameText.getText().length() == 0) {
					Window.alert("회사명을 입력해 주세요.");
				} else if (getNameCheck() == true) {
					Window.alert("이미 등록 된 회사 입니다.");
				} else if (phoneText.getText().length() == 0) {
					Window.alert("전화번호를 입력해 주세요.");
				} else if (addrText.getText().length() == 0) {
					Window.alert("주소를 입력해 주세요.");
				} else {
					serviceImpl.updateCompany(selected.getCompanyName(), nameText.getText(), phoneText.getText(), addrText.getText());
				}
			}
		});
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

		phoneText = new TextBox();
		aPanel.add(phoneText, 57, 208);
		phoneText.setSize("284px", "27px");

		addrText = new TextBox();
		aPanel.add(addrText, 57, 292);
		addrText.setSize("284px", "27px");

		Label label1 = new Label("(-는 제외)");
		aPanel.add(label1, 351, 212);

		Button checkBtn = new Button("New button");
		checkBtn.setText("중복");
		aPanel.add(checkBtn, 349, 120);
		checkBtn.setSize("63px", "31px");
		checkBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nameText.getText().length() > 0) {
					serviceImpl.compNameCheck(nameText.getText());
				} else {
					Window.alert("아이디를 입력해 주세요.");
				}
			}
		});
		
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editDialog.hide();
				button_1.setEnabled(true);
				button_1.setFocus(true);
			}
		});
		/************************************************/
	}

	private void createTable(List<CompanyDto> companys) {
		cellTable = new CellTable<CompanyDto>();
		cellTable.setSkipRowHoverCheck(true);
		cellTable.setSize("661px", "221px");
		absolutePanel.add(cellTable, 0, 159);
		// if(companys != null){
		listByCom(companys);
		// }
	}

	public void listByCom(final List<CompanyDto> companys) {
		cellTable.removeFromParent();
		if (companys != null) {
			TextColumn<CompanyDto> namecolumn = new TextColumn<CompanyDto>() {
				@Override
				public String getValue(CompanyDto object) {
					return object.getCompanyName();
				}
			};
			TextColumn<CompanyDto> addrColumn = new TextColumn<CompanyDto>() {
				@Override
				public String getValue(CompanyDto object) {
					return object.getCompanyAddr();
				}
			};
			TextColumn<CompanyDto> phoneColumn = new TextColumn<CompanyDto>() {
				@Override
				public String getValue(CompanyDto object) {
					return object.getCompanyPhone();
				}
			};
			TextColumn<CompanyDto> dateColumn = new TextColumn<CompanyDto>() {
				@Override
				public String getValue(CompanyDto object) {
					return object.getCompanyCreationDate();
				}
			};
			cellTable.addColumn(namecolumn, "이름");
			cellTable.addColumn(addrColumn, "주소");
			cellTable.addColumn(phoneColumn, "전화번호");
			cellTable.addColumn(dateColumn, "등록일");
			final SingleSelectionModel<CompanyDto> selectionModel = new SingleSelectionModel<CompanyDto>();
			cellTable.setSelectionModel(selectionModel);
			selectionModel.addSelectionChangeHandler(new Handler() {

				@Override
				public void onSelectionChange(SelectionChangeEvent event) {
					selected = selectionModel.getSelectedObject();
				}
			});
			cellTable.setRowCount(companys.size(),true);
            cellTable.setRowData(0,companys);
		}

	}

	public void setNameCheck(boolean result) {
		this.nameCheck = result;
		System.out.println(nameCheck);
	}

	public boolean getNameCheck() {
		
		return nameCheck;
	}
}
