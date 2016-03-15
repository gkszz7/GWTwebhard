package com.webhard.client.GUI;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webhard.client.service.LoginSerivceClientImpl;

public class LoginUser extends Composite{
	
	private VerticalPanel dialogBox = new VerticalPanel();
	private final LoginSerivceClientImpl serviceImpl;
	private TextBox textBox;
	
	public LoginUser(final LoginSerivceClientImpl loginSerivceClientImpl) {
		dialogBox.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		initWidget(this.dialogBox);
		this.serviceImpl = loginSerivceClientImpl;
		dialogBox.setSize("470px", "403px");
		
		LayoutPanel layoutPanel = new LayoutPanel();
		this.dialogBox.add(layoutPanel);
		layoutPanel.setSize("493px", "407px");
		
		Label lblNewLabel = new Label("ID");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblNewLabel);
		layoutPanel.setWidgetLeftWidth(lblNewLabel, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblNewLabel, 104.0, Unit.PX, 34.0, Unit.PX);
		
		textBox = new TextBox();
		layoutPanel.add(textBox);
		layoutPanel.setWidgetLeftWidth(textBox, 125.0, Unit.PX, 232.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBox, 94.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblPw = new Label("PW");
		lblPw.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblPw);
		layoutPanel.setWidgetLeftWidth(lblPw, 27.0, Unit.PX, 80.0, Unit.PX);
		layoutPanel.setWidgetBottomHeight(lblPw, 225.0, Unit.PX, 24.0, Unit.PX);
		
		TextBox textBox_1 = new TextBox();
		layoutPanel.add(textBox_1);
		layoutPanel.setWidgetLeftWidth(textBox_1, 125.0, Unit.PX, 232.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBox_1, 148.0, Unit.PX, 34.0, Unit.PX);
		
		Label lblLogIn = new Label("LOG IN");
		lblLogIn.setStyleName("gwt-Title");
		lblLogIn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblLogIn);
		layoutPanel.setWidgetLeftWidth(lblLogIn, 135.0, Unit.PX, 166.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblLogIn, 30.0, Unit.PX, 40.0, Unit.PX);
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("\uD655 \uC778");
		layoutPanel.add(btnNewButton);
		btnNewButton.setSize("93", "52");
		layoutPanel.setWidgetLeftWidth(btnNewButton, 268.0, Unit.PX, 89.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnNewButton, 213.0, Unit.PX, 52.0, Unit.PX);
		
		Button button = new Button("New button");
		button.setText("\uD68C\uC6D0 \uAC00\uC785");
		layoutPanel.add(button);
		layoutPanel.setWidgetLeftWidth(button, 169.0, Unit.PX, 93.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(button, 213.0, Unit.PX, 52.0, Unit.PX);
		
	}
}
