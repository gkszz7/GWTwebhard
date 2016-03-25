package com.webhard.client.GUI;

import java.io.File;

import javax.swing.JFileChooser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

public class FileUpload{
	
	private DialogBox FileDialog;
	private AbsolutePanel absolutePanel;
	private Label lblNewLabel_1;
	public DialogBox FileDialog(){
		
		FileDialog = new DialogBox();	
		
		absolutePanel = new AbsolutePanel();
		FileDialog.setWidget(absolutePanel);
		absolutePanel.setSize("624px", "302px");
		
		Label lblNewLabel = new Label("파일 경로");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		absolutePanel.add(lblNewLabel, 31, 64);
		lblNewLabel.setSize("115px", "28px");
		
		lblNewLabel_1 = new Label("\"\"\"\"");
		lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		absolutePanel.add(lblNewLabel_1, 31, 111);
		lblNewLabel_1.setSize("431px", "28px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("파일선택");
		btnNewButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		absolutePanel.add(btnNewButton, 490, 111);
		btnNewButton.setSize("103px", "28px");
		
		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("파일저장");
		absolutePanel.add(btnNewButton_1, 353, 260);
		btnNewButton_1.setSize("105px", "28px");
		
		Button button = new Button("New button");
		button.setText("취소");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FileDialog.hide();
			}
		});
		absolutePanel.add(button, 488, 260);
		button.setSize("105px", "28px");
		
		FileDialog.setAnimationEnabled(true);
			
		return FileDialog;
	}
}
