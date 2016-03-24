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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.UserDto;
import com.webhard.client.service.EntryServiceClientImpl;
import com.webhard.client.service.LoginServiceClientImpl;

public class LoginUser extends Composite{
    
   private VerticalPanel dialogBox = new VerticalPanel();
   private final LoginServiceClientImpl serviceImpl;
   private TextBox textBox;
   private PasswordTextBox passwordTextBox;
   
   private List<CompanyDto> list;
   private List<UserDto> userList;
   
   public LoginUser(final LoginServiceClientImpl loginSerivceClientImpl) {
            
      
      dialogBox.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
      
      initWidget(this.dialogBox);
      this.serviceImpl = loginSerivceClientImpl;
      this.serviceImpl.comboList();
      this.serviceImpl.userList();
      dialogBox.setSize("1330px", "1000px");
      
      LayoutPanel layoutPanel = new LayoutPanel();
      this.dialogBox.add(layoutPanel);
      layoutPanel.setSize("662px", "461px");
      
      Label lblNewLabel = new Label("ID");
      lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      layoutPanel.add(lblNewLabel);
      layoutPanel.setWidgetLeftWidth(lblNewLabel, 27.0, Unit.PX, 80.0, Unit.PX);
      layoutPanel.setWidgetTopHeight(lblNewLabel, 162.0, Unit.PX, 34.0, Unit.PX);
      
      textBox = new TextBox(); 
      layoutPanel.add(textBox);
      textBox.setSize("390px", "25px");
      layoutPanel.setWidgetLeftWidth(textBox, 125.0, Unit.PX, 397.0, Unit.PX);
      layoutPanel.setWidgetTopHeight(textBox, 162.0, Unit.PX, 34.0, Unit.PX);
      
      Label lblPw = new Label("PW");
      lblPw.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      layoutPanel.add(lblPw);
      layoutPanel.setWidgetLeftWidth(lblPw, 27.0, Unit.PX, 80.0, Unit.PX);
      layoutPanel.setWidgetBottomHeight(lblPw, 204.0, Unit.PX, 24.0, Unit.PX);
      
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
            serviceImpl.login(id, pwd);
            
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
   
   public void entry(){
      RootPanel.get().clear();
      System.out.println(list.get(0).getCompanyAddr());
      System.out.println(userList.get(0).getUserAddr());
      EntryServiceClientImpl entry = new EntryServiceClientImpl(GWT.getModuleBaseURL()+"entry",list, userList);
      //EntryUser user = new EntryUser(entry, list, userList);
      RootPanel.get().add(entry.getEntryUser());
   }
 
}