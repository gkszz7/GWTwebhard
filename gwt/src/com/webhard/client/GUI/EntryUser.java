package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.EntryServiceClientImpl;
import com.webhard.client.service.LoginServiceClientImpl;

public class EntryUser extends Composite{
	
	private VerticalPanel vPanel = new VerticalPanel();
	private final EntryServiceClientImpl serviceImpl;
	private TextBox textBoxId;
	private PasswordTextBox textBoxPw;
	private PasswordTextBox textBoxPwCheck;
	private TextBox textBoxName;
	private TextBox textBoxPhone;
	private TextBox textBoxAddr;
	private ListBox comboBox;
	private boolean idCheck;

	
	public EntryUser(final EntryServiceClientImpl serviceImp, List<CompanyDto> cDto, final List<UserDto> userList) {
		
			
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		initWidget(this.vPanel);
		this.serviceImpl = serviceImp;

		vPanel.setSize("474px", "615px");
		LayoutPanel layoutPanel = new LayoutPanel();

		this.vPanel.add(layoutPanel);
		layoutPanel.setSize("469px", "616px");
		

		Label lblNewLabel = new Label("ID");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblNewLabel);
		layoutPanel.setWidgetLeftWidth(lblNewLabel, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblNewLabel, 94.0, Unit.PX, 34.0, Unit.PX);
		
		textBoxId = new TextBox();
		layoutPanel.add(textBoxId);
		layoutPanel.setWidgetLeftWidth(textBoxId, 125.0, Unit.PX, 177.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxId, 94.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblPw = new Label("PW");
		lblPw.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblPw);
		layoutPanel.setWidgetLeftWidth(lblPw, 29.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblPw, 148.0, Unit.PX, 34.0, Unit.PX);
		
		textBoxPw = new PasswordTextBox();
		layoutPanel.add(textBoxPw);
		layoutPanel.setWidgetLeftWidth(textBoxPw, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxPw, 148.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblName = new Label("Name");
		lblName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblName);
		layoutPanel.setWidgetLeftWidth(lblName, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblName, 250.0, Unit.PX, 34.0, Unit.PX);
		
		textBoxName = new TextBox();
		layoutPanel.add(textBoxName);
		layoutPanel.setWidgetLeftWidth(textBoxName, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxName, 250.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblPhone = new Label("Phone");
		lblPhone.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblPhone);
		layoutPanel.setWidgetLeftWidth(lblPhone, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblPhone, 299.0, Unit.PX, 34.0, Unit.PX);
		
		textBoxPhone = new TextBox();
		layoutPanel.add(textBoxPhone);
		layoutPanel.setWidgetLeftWidth(textBoxPhone, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxPhone, 299.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblAddress = new Label("Address");
		lblAddress.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblAddress);
		layoutPanel.setWidgetLeftWidth(lblAddress, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblAddress, 350.0, Unit.PX, 34.0, Unit.PX);
		
		textBoxAddr = new TextBox();
		layoutPanel.add(textBoxAddr);
		layoutPanel.setWidgetLeftWidth(textBoxAddr, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxAddr, 350.0, Unit.PX, 34.0, Unit.PX);
		
		Label label = new Label("\uD68C\uC6D0 \uAC00\uC785");
		label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(label);
		layoutPanel.setWidgetLeftWidth(label, 135.0, Unit.PX, 133.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(label, 30.0, Unit.PX, 40.0, Unit.PX);
		
		Label lblCheck = new Label("Check");
		lblCheck.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblCheck);
		layoutPanel.setWidgetLeftWidth(lblCheck, 29.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCheck, 199.0, Unit.PX, 34.0, Unit.PX);
		
		textBoxPwCheck = new PasswordTextBox();
		layoutPanel.add(textBoxPwCheck);
		layoutPanel.setWidgetLeftWidth(textBoxPwCheck, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxPwCheck, 199.0, Unit.PX, 34.0, Unit.PX);
		
		Button okBtn = new Button("New button");
		okBtn.setText("\uD655 \uC778");
		layoutPanel.add(okBtn);
		layoutPanel.setWidgetLeftWidth(okBtn, 77.0, Unit.PX, 89.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(okBtn, 468.0, Unit.PX, 40.0, Unit.PX);
		
		Button cancelBtn = new Button("New button");
		cancelBtn.setText("\uCDE8 \uC18C");
		layoutPanel.add(cancelBtn);
		layoutPanel.setWidgetLeftWidth(cancelBtn, 230.0, Unit.PX, 89.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(cancelBtn, 468.0, Unit.PX, 40.0, Unit.PX);
		cancelBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				LoginServiceClientImpl login = new LoginServiceClientImpl(GWT.getModuleBaseURL()+"login");
				
				RootPanel.get().add(login.getLoginUser());
			}
		});
		
		Button checkBtn = new Button("New button");
		checkBtn.setText("\uC911\uBCF5\uD655\uC778");
		layoutPanel.add(checkBtn);
		layoutPanel.setWidgetLeftWidth(checkBtn, 308.0, Unit.PX, 72.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(checkBtn, 94.0, Unit.PX, 34.0, Unit.PX);
		
		checkBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(textBoxId.getText().length()>0){
					serviceImpl.IdCheck(textBoxId.getText());
				}else{
					Window.alert("아이디를 입력해 주세요.");
				}
			}
		});
		
		
		comboBox = new ListBox();
		comboBox.addItem("선택해주세요");
		if(cDto !=null){
			for(CompanyDto company : cDto){
				
				comboBox.addItem(company.getCompanyName());
			}
		}else{
			comboBox.addItem("등록 X");
		}
		comboBox.setDirectionEstimator(true);
		comboBox.setMultipleSelect(false);
		layoutPanel.add(comboBox);
		layoutPanel.setWidgetLeftWidth(comboBox, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(comboBox, 400.0, Unit.PX, 40.0, Unit.PX);
		
		Label lblCompany = new Label("Company");
		lblCompany.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblCompany);
		layoutPanel.setWidgetLeftWidth(lblCompany, 18.0, Unit.PX, 89.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCompany, 406.0, Unit.PX, 34.0, Unit.PX);
		
		okBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				boolean check = false;
				for(UserDto user : userList){
					if(textBoxId.getText().equals(user.getUserId())){
						check = true;
					}
				}
				
				if(textBoxId.getText().length() == 0){
					Window.alert("아이디를 입력해 주세요");
				}else if(textBoxId.getText().length() < 4){
					Window.alert("아이디는 4자리 이상으로 입력해 주세요");
				}else if(check){
					Window.alert("이미 사용 중인 아이디 입니다.");
				}else if(textBoxPw.getText().length() == 0 ){
					Window.alert("비밀번호를 입력해 주세요");
				}else if(textBoxPwCheck.getText().length() == 0){
					Window.alert("비밀번호를 확인해 주세요.");
				}else if(!textBoxPw.getText().equals(textBoxPwCheck.getText())){
					Window.alert("비밀번호가 일치하지 않습니다.");
				}else if(textBoxName.getText().length() == 0){
					Window.alert("이름을 입력해 주세요.");
				}else if(textBoxPhone.getText().length() == 0){
					Window.alert("전화번호를 입력해 주세요.");
				}else if(textBoxAddr.getText().length() == 0){
					Window.alert("주소를 입력해 주세요");
				}else if(comboBox.getSelectedIndex() == 0){
					Window.alert("회사를 선택해 주세요.");
				}else{
					serviceImpl.entryUser(textBoxId.getText(), textBoxPw.getText(), textBoxName.getText(), 
							textBoxPhone.getText(), textBoxAddr.getText(), comboBox.getValue(comboBox.getSelectedIndex()));				
				}
			}
		});
		
		
	}

	public void setIdCheck(boolean check){
		idCheck = check;
	}
//	public boolean getIdCheck(){
//		
//		return idCheck;
//	}
}
