package com.webhard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.webhard.client.service.LoginServiceClientImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt implements EntryPoint{
//	private static final String LOGIN_TOKEN = "login";
//	private static final String WELCOME_TOKEN = "welcome";
//	Label nameError = new Label();
//	Label pwdError = new Label();
//	final TextBox nameTextbox = new TextBox();
//	final PasswordTextBox passwordTextbox = new PasswordTextBox();

	@Override
	public void onModuleLoad() {

		 LoginServiceClientImpl login = new LoginServiceClientImpl(GWT.getModuleBaseURL()+"login");
		
		 RootPanel.get().add(login.getLoginUser());
//		setupHistory();
	}
//
//	private void setupHistory() {
//		History.addValueChangeHandler(this);
//		History.newItem(LOGIN_TOKEN, true);
//	}
//
//	private void loadLoginView() {
//		final Label loginPrompt = new Label("Please Log in");
//		final Grid grid = new Grid(3, 3);
//		final Label namePrompt = new Label("Name");
//		final Label passwordPrompt = new Label("Password");
//		final Button button = new Button("Login");
//		button.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				if (validateFormInput())
//					History.newItem(WELCOME_TOKEN);
//			}
//		});
//		loginPrompt.addStyleName("loginPrompt");
//		nameTextbox.addStyleName("nameField");
//		passwordTextbox.addStyleName("passwordField");
//		grid.setWidget(0, 0, namePrompt);
//		grid.setWidget(0, 1, nameTextbox);
//		grid.setWidget(0, 2, nameError);
//		grid.setWidget(1, 0, passwordPrompt);
//		grid.setWidget(1, 1, passwordTextbox);
//		grid.setWidget(1, 2, pwdError);
//		grid.setWidget(2, 1, button);
//
//		RootPanel.get().clear();
//		RootPanel.get().add(loginPrompt);
//		RootPanel.get().add(grid);
//	}
//
//	private void loadWelcomeView() {
//		final Label welcomeMsg = new Label("Welcome to the GWT History Demo");
//
//		welcomeMsg.addStyleName("welcomeMsg");
//
//		RootPanel.get().clear();
//		RootPanel.get().add(welcomeMsg);
//		RootPanel.get().add(new Hyperlink("Logout", LOGIN_TOKEN));
//	}
//
//	protected boolean validateFormInput() {
//
//		// validating the password is not empty
//		String name = nameTextbox.getText();
//		boolean nameIsValid = name.length() > 0;
//		nameError.setText(nameIsValid ? "" : "Please enter the Name");
//
//		String pwd = passwordTextbox.getText();
//		boolean pwdIsValid = pwd.length() > 0;
//		pwdError.setText(pwdIsValid ? "" : "Please enter the Password");
//
//		return (nameIsValid && pwdIsValid);
//	}
//
//	@Override
//	public void onValueChange(ValueChangeEvent event) {
//		String historyToken = (String) event.getValue();
//		if (LOGIN_TOKEN.equals(historyToken)) {
//			loadLoginView();
//		} else if (WELCOME_TOKEN.equals(historyToken)) {
//			loadWelcomeView();
//		}
//
//	}

}
