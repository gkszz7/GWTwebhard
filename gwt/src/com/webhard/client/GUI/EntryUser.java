package com.webhard.client.GUI;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webhard.client.service.EntryServiceClientImpl;

public class EntryUser extends Composite{
	
	private VerticalPanel dialogBox = new VerticalPanel();
	private final EntryServiceClientImpl serviceImpl;
	private TextBox textBox;
	
	public EntryUser(final EntryServiceClientImpl serviceImp) {
		
		dialogBox.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		initWidget(this.dialogBox);
		this.serviceImpl = serviceImp;
		
		dialogBox.setSize("474px", "615px");
		
		LayoutPanel layoutPanel = new LayoutPanel();
		
		this.dialogBox.add(layoutPanel);
		layoutPanel.setSize("572px", "616px");
		
		Label lblNewLabel = new Label("ID");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblNewLabel);
		layoutPanel.setWidgetLeftWidth(lblNewLabel, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblNewLabel, 94.0, Unit.PX, 34.0, Unit.PX);
		
		textBox = new TextBox();
		layoutPanel.add(textBox);
		layoutPanel.setWidgetLeftWidth(textBox, 125.0, Unit.PX, 177.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBox, 94.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblPw = new Label("PW");
		lblPw.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblPw);
		layoutPanel.setWidgetLeftWidth(lblPw, 29.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblPw, 148.0, Unit.PX, 34.0, Unit.PX);
		
		TextBox textBox_1 = new TextBox();
		layoutPanel.add(textBox_1);
		layoutPanel.setWidgetLeftWidth(textBox_1, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBox_1, 148.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblName = new Label("Name");
		lblName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblName);
		layoutPanel.setWidgetLeftWidth(lblName, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblName, 250.0, Unit.PX, 34.0, Unit.PX);
		
		TextBox textBox_2 = new TextBox();
		layoutPanel.add(textBox_2);
		layoutPanel.setWidgetLeftWidth(textBox_2, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBox_2, 250.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblPhone = new Label("Phone");
		lblPhone.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblPhone);
		layoutPanel.setWidgetLeftWidth(lblPhone, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblPhone, 299.0, Unit.PX, 34.0, Unit.PX);
		
		TextBox textBox_3 = new TextBox();
		layoutPanel.add(textBox_3);
		layoutPanel.setWidgetLeftWidth(textBox_3, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBox_3, 299.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblAddress = new Label("Address");
		lblAddress.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblAddress);
		layoutPanel.setWidgetLeftWidth(lblAddress, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblAddress, 350.0, Unit.PX, 34.0, Unit.PX);
		
		TextBox textBox_4 = new TextBox();
		layoutPanel.add(textBox_4);
		layoutPanel.setWidgetLeftWidth(textBox_4, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBox_4, 350.0, Unit.PX, 34.0, Unit.PX);
		
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
		
		TextBox textBox_5 = new TextBox();
		layoutPanel.add(textBox_5);
		layoutPanel.setWidgetLeftWidth(textBox_5, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBox_5, 199.0, Unit.PX, 34.0, Unit.PX);
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("\uD655 \uC778");
		layoutPanel.add(btnNewButton);
		layoutPanel.setWidgetLeftWidth(btnNewButton, 77.0, Unit.PX, 89.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnNewButton, 468.0, Unit.PX, 40.0, Unit.PX);
		
		btnNewButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				serviceImpl.getData(textBox.getText());			
			}
		});
		
		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("\uCDE8 \uC18C");
		layoutPanel.add(btnNewButton_1);
		layoutPanel.setWidgetLeftWidth(btnNewButton_1, 230.0, Unit.PX, 89.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnNewButton_1, 468.0, Unit.PX, 40.0, Unit.PX);
		
		Button btnNewButton_2 = new Button("New button");
		btnNewButton_2.setText("\uC911\uBCF5\uD655\uC778");
		layoutPanel.add(btnNewButton_2);
		layoutPanel.setWidgetLeftWidth(btnNewButton_2, 308.0, Unit.PX, 72.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnNewButton_2, 94.0, Unit.PX, 34.0, Unit.PX);
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("APPLE");
		comboBox.addItem("BANANA");
		comboBox.setMultipleSelect(true);
		comboBox.setDirectionEstimator(true);
		layoutPanel.add(comboBox);
		layoutPanel.setWidgetLeftWidth(comboBox, 125.0, Unit.PX, 215.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(comboBox, 400.0, Unit.PX, 40.0, Unit.PX);
		
		Label lblCompany = new Label("Company");
		lblCompany.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblCompany);
		layoutPanel.setWidgetLeftWidth(lblCompany, 18.0, Unit.PX, 89.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCompany, 406.0, Unit.PX, 34.0, Unit.PX);
		
	}
	public void editID(String edit){
		this.textBox.setText(edit);
	}
	
}
