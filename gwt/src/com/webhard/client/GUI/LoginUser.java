package com.webhard.client.GUI;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.EntryServiceClientImpl;
import com.webhard.client.service.LoginServiceClientImpl;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;

public class LoginUser extends Composite{
    
   private VerticalPanel dialogBox = new VerticalPanel();
   private final LoginServiceClientImpl serviceImpl;
   private TextBox textBox;
   private PasswordTextBox passwordTextBox;
   private Tree tree;
   private List<CompanyDto> list;
   private List<UserDto> userList;
   
   public LoginUser(final LoginServiceClientImpl loginSerivceClientImpl) {
	  History.newItem("login");
      dialogBox.setStyleName("sendButton-new");
          
      dialogBox.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
      
      initWidget(this.dialogBox);
      this.serviceImpl = loginSerivceClientImpl;
      this.serviceImpl.comboList();
      this.serviceImpl.userList();
      this.serviceImpl.itemTree();
      dialogBox.setSize("463px", "511px");
      setupHistory();
      LayoutPanel layoutPanel = new LayoutPanel();
      layoutPanel.setStyleName("sendButton-new");
      this.dialogBox.add(layoutPanel);
      layoutPanel.setSize("605px", "509px");
      
      Label lblNewLabel = new Label("ID");
      lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      layoutPanel.add(lblNewLabel);
      layoutPanel.setWidgetLeftWidth(lblNewLabel, 27.0, Unit.PX, 80.0, Unit.PX);
      layoutPanel.setWidgetTopHeight(lblNewLabel, 167.0, Unit.PX, 24.0, Unit.PX);
      
      textBox = new TextBox(); 
      layoutPanel.add(textBox);
      textBox.setSize("390px", "25px");
      layoutPanel.setWidgetLeftWidth(textBox, 125.0, Unit.PX, 397.0, Unit.PX);
      layoutPanel.setWidgetTopHeight(textBox, 162.0, Unit.PX, 34.0, Unit.PX);
      
      Label lblPw = new Label("PW");
      lblPw.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      layoutPanel.add(lblPw);
      layoutPanel.setWidgetLeftWidth(lblPw, 27.0, Unit.PX, 80.0, Unit.PX);
      layoutPanel.setWidgetBottomHeight(lblPw, 245.0, Unit.PX, 24.0, Unit.PX);
      
      Label lblLogIn = new Label("LOG IN");
      lblLogIn.setStyleName("gwt-Title");
      lblLogIn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      layoutPanel.add(lblLogIn);
      layoutPanel.setWidgetLeftWidth(lblLogIn, 224.0, Unit.PX, 166.0, Unit.PX);
      layoutPanel.setWidgetTopHeight(lblLogIn, 33.0, Unit.PX, 40.0, Unit.PX);
      
      Button loginBtn = new Button("loginBtn");
      loginBtn.addClickHandler(new ClickHandler() {
         
         @Override
         public void onClick(ClickEvent event) { 
            if(textBox.getText().length()>0 && passwordTextBox.getText().length()>0){
            String id = textBox.getText();
            String pwd = passwordTextBox.getText();
            serviceImpl.login(id, pwd, tree);
            
            }
            else{
               Window.alert("아이디 비밀번호를 입력해주세요");   
            }
         }
      });
      loginBtn.setText("\uD655 \uC778");
      layoutPanel.add(loginBtn);
      loginBtn.setSize("93", "52");
      layoutPanel.setWidgetLeftWidth(loginBtn, 331.0, Unit.PX, 89.0, Unit.PX);
      layoutPanel.setWidgetTopHeight(loginBtn, 312.0, Unit.PX, 52.0, Unit.PX);
      
      Button userInsertBtn = new Button("userInsertBtn");
      userInsertBtn.addClickHandler(new ClickHandler() {   
         @Override
         public void onClick(ClickEvent event) { 
            entry();
            
         }
      });
      userInsertBtn.setText("\uD68C\uC6D0 \uAC00\uC785");
      layoutPanel.add(userInsertBtn);
      layoutPanel.setWidgetLeftWidth(userInsertBtn, 215.0, Unit.PX, 93.0, Unit.PX);
      layoutPanel.setWidgetTopHeight(userInsertBtn, 312.0, Unit.PX, 52.0, Unit.PX);
      
      passwordTextBox = new PasswordTextBox();
      passwordTextBox.addKeyDownHandler(new KeyDownHandler() {
    	  
      	public void onKeyDown(KeyDownEvent event) {
      		 if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
      			if(textBox.getText().length()>0 && passwordTextBox.getText().length()>0){
                    String id = textBox.getText();
                    String pwd = passwordTextBox.getText();
                    serviceImpl.login(id, pwd, tree);
                    
                    }
                    else{
                       Window.alert("아이디 비밀번호를 입력해주세요");   
                    }         
               }
      	}
      });
      
      layoutPanel.add(passwordTextBox);
      passwordTextBox.setSize("390px", "25px");
      layoutPanel.setWidgetLeftWidth(passwordTextBox, 125.0, Unit.PX, 397.0, Unit.PX);
      layoutPanel.setWidgetTopHeight(passwordTextBox, 233.0, Unit.PX, 34.0, Unit.PX);
      
   }
   
   public void setComList(List<CompanyDto> list1){
	   list = list1;
   }
   public void setUserList(List<UserDto> list1){
	   userList = list1;
   }
   public void setTree(Tree tree1){
		tree = tree1;
	}
   
   public void entry(){
      RootPanel.get().clear();
      EntryServiceClientImpl entry = new EntryServiceClientImpl(GWT.getModuleBaseURL()+"entry",list, userList);
      //EntryUser user = new EntryUser(entry, list, userList);
      RootPanel.get().add(entry.getEntryUser());
   }
   private void setupHistory() {
       final String initToken = History.getToken();
       if (initToken.length() == 0) {
          History.newItem("login");      
       }
   }
}