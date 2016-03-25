package com.webhard.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.webhard.client.GUI.MainPage;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.UserDto;
public class MainServiceClientImpl implements MainServiceClientInt{
	
	private MainServiceAsync mainAsync;
	private MainPage main;
	
	public MainServiceClientImpl(String url) {
		
		this.mainAsync = GWT.create(MainService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget)this.mainAsync;
		endPoint.setServiceEntryPoint(url);
		
		this.main = new MainPage(this);
	}
	
	@Override
	public void compList() {
		
		this.mainAsync.compList(new AsyncCallback<List<CompanyDto>>() {
			
			@Override
			public void onSuccess(List<CompanyDto> result) {
				main.setCompList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
	}
	@Override
	public void folderList() {
		this.mainAsync.folderList(new AsyncCallback<List<FolderDto>>() {
			@Override
			public void onSuccess(List<FolderDto> result) {
				main.setFolderList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void homeFolder() {
		this.mainAsync.homeFolder(new AsyncCallback<FolderDto>() {
			@Override
			public void onSuccess(FolderDto result) {
				main.setHomeFolder(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void entryCompany(String name, String addr, String phone) {
		this.mainAsync.entryCompany(name, addr, phone, new AsyncCallback<List<CompanyDto>>() {
			
			@Override
			public void onSuccess(List<CompanyDto> result) {
				Window.alert("회사가 추가되었습니다.");
				
				RootPanel.get().clear();
				
				MainServiceClientImpl main = new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main");
				
				RootPanel.get().add(main.getMainPage());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
		
	}
	public void UserList() {
		
			this.mainAsync.UserList(new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				main.selectUser(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});	
	}
	
	public void AccessList() {

		this.mainAsync.AccessList(new AsyncCallback<List<UserDto>>() {
			
			@Override
			public void onSuccess(List<UserDto> result) {
				// TODO Auto-generated method stub
				main.selectAccess(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});		
	}
/*
 	public TreeGrid test(){
		Tree grid1Tree = new Tree();  
        grid1Tree.setModelType(TreeModelType.CHILDREN);  
        grid1Tree.setNameProperty("Name");  
        grid1Tree.setRoot(
        		new PartsTreeNode("Root",new PartsTreeNode("Bin 1",new PartsTreeNode("Blue Cube", "folder1.png"),  
                new PartsTreeNode("Yellow Cube", "folder1.png"),  
                new PartsTreeNode("Green Cube", "folder1.png")  
                ),  
                new PartsTreeNode("Bin 2",  
                new PartsTreeNode("Blue Piece", "folder1.png"),  
                new PartsTreeNode("Green Piece", "folder1.png"),  
                new PartsTreeNode("Yellow Piece", "folder1.png")  
                )  
        ));  
  
        final PartsTreeGrid grid1 = new PartsTreeGrid();  
        grid1.setDragDataAction(DragDataAction.MOVE);  
        grid1.setData(grid1Tree);  
        grid1.getData().openAll();  
        return grid1;
	}
	 public static class PartsTreeGrid extends TreeGrid {  
	        public PartsTreeGrid() {  
	            setWidth(200);  
	            setHeight(200);  
	            setShowEdges(true);  
	            setBorder("0px");  
	            setBodyStyleName("normal");  
	            setShowHeader(false);  
	            setLeaveScrollbarGap(false);  
	            setEmptyMessage("<br>Drag & drop parts here");  
	            setManyItemsImage("folder1.png");  	        
	            setCanReorderRecords(true);  
	            setCanAcceptDroppedRecords(true);  
	            setCanDragRecordsOut(true);  
	        }  
	    }  
	public static class PartsTreeNode extends TreeNode {  
        public PartsTreeNode(String name, String icon) {  
            this(name, icon, new PartsTreeNode[]{});  
        }  
  
        public PartsTreeNode(String name, PartsTreeNode... children) {  
            this(name, null, children);  
        }  
  
        public PartsTreeNode(String name, String icon, PartsTreeNode... children) {  
            setAttribute("Name", name);  
            setAttribute("children", children);  
            if (icon != null)  
                setAttribute("icon", icon);  
        }  
    } 
     */
	
	public DialogBox fileUpload(){
final DialogBox FileDialog = new DialogBox();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		FileDialog.setWidget(absolutePanel);
		absolutePanel.setSize("371px", "287px");
		
		HTML html = new HTML("FileUpload");
		absolutePanel.add(html, 10, 10);
		html.setSize("300px", "20px");
		
		final FileUpload fileUpload = new FileUpload();
		fileUpload.ensureDebugId("cwFileUpload");
		absolutePanel.add(fileUpload, 10, 107);
		fileUpload.setSize("349px", "25px");
		
		HTML html_1 = new HTML("<br>");
		absolutePanel.add(html_1, 10, 155);
		html_1.setSize("300px", "20px");
		
		Button button = new Button("FileUpload");
		button.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        String filename = fileUpload.getFilename();
		        
		        if (filename.length() == 0) {
		          Window.alert("Error");
		        } else {
		          Window.alert("Success");
		        }
		      }
		    });
		absolutePanel.add(button, 10, 249);
		button.setSize("85px", "25px");
		
		Button button_1 = new Button("Cancel");
		button_1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				FileDialog.hide();
			}
		});
		absolutePanel.add(button_1, 121, 249);
		button_1.setSize("64px", "25px");
	    
	    return FileDialog;
	}
	public MainPage getMainPage(){

		return this.main;
	}
	
}
