package com.webhard.client.GUI;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;

public class Test extends Composite{
	public Test() {
		DialogBox createFolderBox = new DialogBox();
		createFolderBox.setAnimationEnabled(true);
		AbsolutePanel aPanel = new AbsolutePanel();
		aPanel.setStyleName("gwt-absolutePanel");
		createFolderBox.setWidget(aPanel);
		aPanel.setSize("397px", "325px");
		
		Label label = new Label("폴더 이름");
		aPanel.add(label, 39, 100);
		label.setSize("92px", "32px");
		
		TextBox textBox = new TextBox();
		aPanel.add(textBox, 39, 138);
		textBox.setSize("316px", "28px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("확 인");
		aPanel.add(btnNewButton, 73, 231);
		btnNewButton.setSize("92px", "41px");
		
		Button button = new Button("New button");
		button.setText("취 소");
		aPanel.add(button, 242, 231);
		button.setSize("92px", "41px");

	}
}
