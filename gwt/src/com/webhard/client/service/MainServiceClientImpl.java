package com.webhard.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webhard.client.GUI.MainPage;
import com.webhard.client.model.CompanyDto;
import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;

public class MainServiceClientImpl implements MainServiceClientInt {

	private MainServiceAsync mainAsync;
	private MainPage main;
	private Tree tree;
	private String companyName;
	private int homeFolNum;
	private UserDto userDto;
	private DialogBox folderBox, FileDialog;
	private CellTable<ItemDto> cellTable;
	private List<ItemDto> itemList;
	private List<FolderDto> folderList;
	private List<FileDto> fileList;
	private ItemDto selected;
	Images images = GWT.create(Images.class);
	private List<FileDto> files;
	
	public MainServiceClientImpl() {
		
	}
	
	public MainServiceClientImpl(String url, Tree getTree, String compName, int homeNum , UserDto userDto) {
		
		
		this.mainAsync = GWT.create(MainService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget) this.mainAsync;
		endPoint.setServiceEntryPoint(url);
		this.companyName = compName;
		this.homeFolNum = homeNum;
		this.tree = getTree;
		this.userDto = userDto;
		this.main = new MainPage(this, getTree, compName, homeNum, userDto);
		
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
	public void entryCompany(String name, String addr, String phone) {
		this.mainAsync.entryCompany(name, addr, phone,
				new AsyncCallback<ItemDto>() {

					@Override
					public void onSuccess(ItemDto result) {
						Window.alert("회사가 추가되었습니다.");
						
						tree = new Tree();
						TreeItem homeItem = new TreeItem();
						homeItem.setText(result.getName());
						homeItem.setUserObject(result);
						getTree(homeItem);
						homeItem.setHTML(imageItemHTML(images.treeOpen(), homeItem.getText()));
						
						RootPanel.get().clear();

						MainServiceClientImpl main = 
								new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree, companyName, homeFolNum, userDto);

						RootPanel.get().add(main.getMainPage());
					}

					@Override
					public void onFailure(Throwable caught) {

					}
				});

	}
	@Override
	public void UserList() {

		this.mainAsync.UserList(new AsyncCallback<List<UserDto>>() {

			@Override
			public void onSuccess(List<UserDto> result) {
				main.selectUser(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void AccessList() {

		this.mainAsync.AccessList(new AsyncCallback<List<UserDto>>() {

			@Override
			public void onSuccess(List<UserDto> result) {
				// TODO Auto-generated method stub
				main.selectAccess(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void createFolder(String name, int parentNum, int companyNum) {
		this.mainAsync.createFolder(name, parentNum, companyNum, new AsyncCallback<ItemDto>() {
			@Override
			public void onSuccess(ItemDto result) {
				Window.alert("폴더가 추가되었습니다.");
				
				tree = new Tree();
				TreeItem homeItem = new TreeItem();
				homeItem.setText(result.getName());
				homeItem.setUserObject(result);
				getTree(homeItem);
				homeItem.setHTML(imageItemHTML(images.treeOpen(), homeItem.getText()));
				
				RootPanel.get().clear();

				MainServiceClientImpl main = 
						new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree, companyName, homeFolNum, userDto );

				RootPanel.get().add(main.getMainPage());
			}
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	@Override
	public void updateFolder(String name, int itemNum) {
		this.mainAsync.updateFolder(name, itemNum, new AsyncCallback<ItemDto>() {
			@Override
			public void onSuccess(ItemDto result) {
				Window.alert("폴더가 변경되었습니다.");
				
				tree = new Tree();
				TreeItem homeItem = new TreeItem();
				homeItem.setText(result.getName());
				homeItem.setUserObject(result);
				getTree(homeItem);
				homeItem.setHTML(imageItemHTML(images.treeOpen(), homeItem.getText()));
				
				RootPanel.get().clear();

				MainServiceClientImpl main = 
						new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree, companyName, homeFolNum, userDto );

				RootPanel.get().add(main.getMainPage());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	@Override
	public void deleteFolder(int itemNum) {
		this.mainAsync.deleteFolder(itemNum, new AsyncCallback<ItemDto>() {
			@Override
			public void onSuccess(ItemDto result) {
				Window.alert("폴더가 삭제되었습니다.");
				
				tree = new Tree(){
					@Override
					public void onBrowserEvent(Event event) {
						if(DOM.eventGetType(event) == event.ONCONTEXTMENU){
							
						}
						super.onBrowserEvent(event);
					}
				};
				TreeItem homeItem = new TreeItem();
				homeItem.setText(result.getName());
				homeItem.setUserObject(result);
				homeItem.setState(true);
				getTree(homeItem);
				homeItem.setHTML(imageItemHTML(images.treeOpen(), homeItem.getText()));
				
				RootPanel.get().clear();

				MainServiceClientImpl main = 
						new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree, companyName, homeFolNum, userDto );

				RootPanel.get().add(main.getMainPage());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	@Override
	public void allFiles() {
		this.mainAsync.allFiles(new AsyncCallback<List<FileDto>>() {
			
			@Override
			public void onSuccess(List<FileDto> result) {
				files = result;
				main.setAllFiles(files);
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	@Override
	public void ItemInTable(int itemNum) {
		this.mainAsync.ItemInTable(itemNum, new AsyncCallback<HashMap<String,Object>>() {
			@Override
			public void onSuccess(HashMap<String, Object> result) {
				List<ItemDto> item = (List<ItemDto>)result.get("itemList");
				List<FolderDto> folder = (List<FolderDto>)result.get("folderList");
				List<FileDto> file = (List<FileDto>)result.get("fileList");
				itemList = item;
				folderList = folder;
				fileList = file;
				companyTable(itemList);
				main.setTable(cellTable);
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	/*************************트리 생성***********************/
	public void getTree(TreeItem result){
		
		TreeItem item = result;
		List<ItemDto> childNodes = new ArrayList<ItemDto>();
		List<ItemDto> grandChildNodes = new ArrayList<ItemDto>();
		ItemDto itemDto = (ItemDto)result.getUserObject();
		childNodes = itemDto.getChild();
		
		for(int i=0;i<childNodes.size();i++){
			ItemDto childNode = childNodes.get(i);
			
			TreeItem childItem = new TreeItem();
			childItem.setText(childNode.getName());
			childItem.setUserObject(childNode);
			
			grandChildNodes = childNode.getChild();
			if(grandChildNodes.size() != 0){
				for(int j=0; j<grandChildNodes.size(); j++){
					
					getTree(childItem);
					if(childNode.getType() == 0){
						childItem.setHTML(imageItemHTML(images.treeLeaf(), childItem.getText()));
					}else{
						for(int a=0;a<files.size();a++){
							if(childNode.getItemNum() == files.get(a).getItemNum()){
								if(files.get(a).getFileType().equals("pptx")){
									childItem.setHTML(imageItemHTML(images.ppt(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("txt")){
									childItem.setHTML(imageItemHTML(images.text(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("xlsx")){
									childItem.setHTML(imageItemHTML(images.excel(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("zip")){
									childItem.setHTML(imageItemHTML(images.zip(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("mp3")){
									childItem.setHTML(imageItemHTML(images.mp3(), childItem.getText()));
								}else{
									childItem.setHTML(imageItemHTML(images.files(), childItem.getText()));
								}
							}
						}
					}
					item.addItem(childItem);
					break;
				}
			}else{
				if(itemDto.getItemNum() == homeFolNum || childItem.getChildCount() == 0){
					if(childNode.getType() == 0){
						childItem.setHTML(imageItemHTML(images.treeLeaf(), childItem.getText()));
					}else{
						for(int a=0;a<files.size();a++){
							if(childNode.getItemNum() == files.get(a).getItemNum()){
								if(files.get(a).getFileType().equals("pptx")){
									childItem.setHTML(imageItemHTML(images.ppt(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("txt")){
									childItem.setHTML(imageItemHTML(images.text(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("xlsx")){
									childItem.setHTML(imageItemHTML(images.excel(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("zip")){
									childItem.setHTML(imageItemHTML(images.zip(), childItem.getText()));
								}else if(files.get(a).getFileType().equals("mp3")){
									childItem.setHTML(imageItemHTML(images.mp3(), childItem.getText()));
								}else{
									childItem.setHTML(imageItemHTML(images.files(), childItem.getText()));
								}
							}
						}
					}
					item.addItem(childItem);
				}
			}
			
		}
		tree.addItem(item);
		
		if(((ItemDto)item.getUserObject()).getItemNum() == homeFolNum){
			item.setState(true);
		}
	}
	/**********************************************************/
	/************************파일 업로드 다이얼로그*******************/
	
	public DialogBox fileUpload(int itemnum, int comnum){
		
		FileDialog = new DialogBox();
		
		final FormPanel form = new FormPanel();
	    form.setAction(GWT.getModuleBaseURL()+"fileupload");

	    // Because we're going to add a FileUpload widget, we'll need to set the
	    // form to use the POST method, and multipart MIME encoding.
	    form.setEncoding(FormPanel.ENCODING_MULTIPART);
	    form.setMethod(FormPanel.METHOD_POST);

	    // Create a panel to hold all of the form widgets.
	    VerticalPanel panel = new VerticalPanel();
	    form.setWidget(panel);

	    TextBox inum = new TextBox();
	    String Item = Integer.toString(itemnum);
	    inum.setName("title");
	    inum.setText(Item);
	    inum.setVisible(false);
	    panel.add(inum);
	   
	    TextBox cnum = new TextBox();
	    String compnum = Integer.toString(comnum);
	    cnum.setName("title1");
	    cnum.setText(compnum);
	    cnum.setVisible(false);
	    panel.add(cnum);
	    
	    FileDialog.setWidget(form); 
	    // Create a FileUpload widget.
	    final FileUpload upload = new FileUpload();
	    upload.setName("uploadFormElement");
	    panel.add(upload);

	    // Add a 'submit' button.
	    panel.add(new Button("Submit", new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        form.submit();
	      }
	    }));

	    // Add an event handler to the form.
	    form.addSubmitHandler(new FormPanel.SubmitHandler() {	     
		@Override
		public void onSubmit(SubmitEvent event) {
			 // This event is fired just before the form is submitted. We can take
	        // this opportunity to perform validation.
	        if (upload.getFilename().length() == 0) {
	          Window.alert("파일을 선택해주기바랍니다.");
	          event.cancel();
	          
	        }
		}
	    });
	    form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {	    
		@Override
		public void onSubmitComplete(SubmitCompleteEvent event) {
			 // When the form submission is successfully completed, this event is
	        // fired. Assuming the service returned a response of type text/html,
	        // we can get the result text here (see the FormPanel documentation for
	        // further explanation).
	        Window.alert("등록 완료");
	        FileDialog.hide();
	       /* tree = new Tree();
			TreeItem homeItem = new TreeItem();
			homeItem.setText(result.getName());
			homeItem.setUserObject(result);
			getTree(homeItem);
			
			RootPanel.get().clear();

			MainServiceClientImpl main = 
					new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree, companyName, homeFolNum, userDto);

			RootPanel.get().add(main.getMainPage());*/
	        
		}
	    });

	   return FileDialog;
	  }
	
	public DialogBox filedownload(int itemnum, String comnum){
		
		final DialogBox downloadDialog = new DialogBox();
		
		final FormPanel form = new FormPanel();
	    form.setAction(GWT.getModuleBaseURL()+"filedownload");

	    // Because we're going to add a FileUpload widget, we'll need to set the
	    // form to use the POST method, and multipart MIME encoding.
	    form.setEncoding(FormPanel.ENCODING_MULTIPART);
	    form.setMethod(FormPanel.METHOD_POST);

	    // Create a panel to hold all of the form widgets.
	    VerticalPanel panel = new VerticalPanel();
	    form.setWidget(panel);
		   
	    TextBox cnum = new TextBox();
	    cnum.setName("title1");
	    String Item = Integer.toString(itemnum);
	    cnum.setText(Item);
	    cnum.setVisible(false);
	    panel.add(cnum);
	    
	    downloadDialog.setWidget(form); 
	    // Create a FileUpload widget.
	  
	    
	    final TextBox inum = new TextBox();
	    inum.setEnabled(false);
	    inum.setName("title");
	    inum.setText(comnum);
	    panel.add(inum);


	    // Add a 'submit' button.
	    panel.add(new Button("다운로드", new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        form.submit();
	      }
	    }));

	    // Add an event handler to the form.
	    form.addSubmitHandler(new FormPanel.SubmitHandler() {	     
		@Override
		public void onSubmit(SubmitEvent event) {
			 // This event is fired just before the form is submitted. We can take
	        // this opportunity to perform validation.
	        if (inum.getText().length() == 0) {
	          Window.alert("파일을 선택해주기바랍니다.");
	          event.cancel();
	          
	        }
		}
	    });
	    form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {	    
		@Override
		public void onSubmitComplete(SubmitCompleteEvent event) {
			 // When the form submission is successfully completed, this event is
	        // fired. Assuming the service returned a response of type text/html,
	        // we can get the result text here (see the FormPanel documentation for
	        // further explanation).
	        Window.alert("등록 완료");
	        downloadDialog.hide();
	       /* tree = new Tree();
			TreeItem homeItem = new TreeItem();
			homeItem.setText(result.getName());
			homeItem.setUserObject(result);
			getTree(homeItem);
			
			RootPanel.get().clear();

			MainServiceClientImpl main = 
					new MainServiceClientImpl(GWT.getModuleBaseURL()+"Main", tree, companyName, homeFolNum, userDto);

			RootPanel.get().add(main.getMainPage());*/
	        
		}
	    });

	   return downloadDialog;
	}
	/*************************폴더 관련 다이얼로그 ****************/
	public DialogBox createFolderBox(final int parentNum, final int companyNum){
		folderBox = new DialogBox();
		folderBox.setAnimationEnabled(true);
		AbsolutePanel aPanel = new AbsolutePanel();
		aPanel.setStyleName("gwt-absolutePanel");
		folderBox.setWidget(aPanel);
		aPanel.setSize("397px", "325px");
		
		Label label = new Label("폴더 이름");
		aPanel.add(label, 39, 100);
		label.setSize("92px", "32px");
		
		final TextBox textBox = new TextBox();
		aPanel.add(textBox, 39, 138);
		textBox.setSize("316px", "28px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("확 인");
		aPanel.add(btnNewButton, 73, 231);
		btnNewButton.setSize("92px", "41px");
		btnNewButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(textBox.getText() != null){
					createFolder(textBox.getText(), parentNum, companyNum);
				}else{
					Window.alert("이름을 입력해주세요.");
				}
			}
		});		
		Button button = new Button("New button");
		button.setText("취 소");
		aPanel.add(button, 242, 231);
		button.setSize("92px", "41px");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				folderBox.hide();
			}
		});
		
		return folderBox;
	}
	
	public DialogBox updateFolderBox(final int itemNum){
		folderBox = new DialogBox();
		folderBox.setAnimationEnabled(true);
		AbsolutePanel aPanel = new AbsolutePanel();
		aPanel.setStyleName("gwt-absolutePanel");
		folderBox.setWidget(aPanel);
		aPanel.setSize("397px", "325px");
		
		Label label = new Label("폴더 이름");
		aPanel.add(label, 39, 100);
		label.setSize("92px", "32px");
		
		final TextBox textBox = new TextBox();
		aPanel.add(textBox, 39, 138);
		textBox.setSize("316px", "28px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("확 인");
		aPanel.add(btnNewButton, 73, 231);
		btnNewButton.setSize("92px", "41px");
		btnNewButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(textBox.getText() != null){
					updateFolder(textBox.getText(), itemNum);
				}else{
					Window.alert("이름을 입력해주세요.");
				}
			}
		});		
		Button button = new Button("New button");
		button.setText("취 소");
		aPanel.add(button, 242, 231);
		button.setSize("92px", "41px");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				folderBox.hide();
			}
		});
		
		return folderBox;
	}
	
	/**********************************************************/
	
	/********************테이블 생성 코드 ***********************/
	public void companyTable(List<ItemDto> items) {
		
		cellTable = new CellTable<ItemDto>();
		cellTable.setSkipRowHoverCheck(true);
		cellTable.setSize("661px", "221px");
		 if(items != null){
		companylistByCom(items);
		 }
	}

	public void companylistByCom(final List<ItemDto> items) {
		cellTable.removeFromParent();
		if (items != null) {
			TextColumn<ItemDto> nameColumn = new TextColumn<ItemDto>() {
				@Override
				public String getValue(ItemDto object) {
					return object.getName();
				}
			};
			TextColumn<ItemDto> dateColumn = new TextColumn<ItemDto>() {
				@Override
				public String getValue(ItemDto object) {
					return object.getDate();
				}
			};
			TextColumn<ItemDto> userColumn = new TextColumn<ItemDto>() {
				@Override
				public String getValue(ItemDto object) {
					return object.getUserId();
				}
			};
			TextColumn<ItemDto> typeColumn = new TextColumn<ItemDto>() {
				@Override
				public String getValue(ItemDto object) {
					if(object.getType()==0){
						return "폴더";
					}else{
						return "파일";
					}
					
				}
			};
			cellTable.addColumn(nameColumn, "이름");
			cellTable.addColumn(dateColumn, "등록일");
			cellTable.addColumn(userColumn, "만든 이");
			cellTable.addColumn(typeColumn, "유형");
			cellTable.setRowCount(items.size(),true);
			cellTable.setRowData(0,items);
		}

	}
	/***********************************************************/
	@Override
	public void logout() {
		this.mainAsync.logout(new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				Window.alert("로그아웃 하셨습니다.");
				
				RootPanel.get().clear();
				
				LoginServiceClientImpl login = new LoginServiceClientImpl(GWT.getModuleBaseURL()+"login");
				
				RootPanel.get().add(login.getLoginUser());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error");
			}
		});
	}
	
	/*********************트리 아이콘 생성 ************************/
	
	public SafeHtml imageItemHTML(ImageResource imageProto, String title) {
	    SafeHtmlBuilder builder = new SafeHtmlBuilder();
	    builder.append(AbstractImagePrototype.create(imageProto).getSafeHtml());
	    builder.appendHtmlConstant(" ");
	    builder.appendEscaped(title);
	    return builder.toSafeHtml();
	}
	public interface Images extends Tree.Resources {
		@Source("Folder-48.png")
	    ImageResource ppt();
		@Source("Folder-48.png")
	    ImageResource excel();
		@Source("Folder-48.png")
	    ImageResource text();
		@Source("Folder-48.png")
	    ImageResource mp3();
		@Source("Folder-48.png")
	    ImageResource zip();
		@Source("Folder-48.png")
	    ImageResource files();
	    @Override
	    @Source("Folder-48.png")
	    ImageResource treeLeaf();
	    @Override
	    @Source("Open Folder-48.png")
	    ImageResource treeOpen();
	  }
	public MainPage getMainPage() {

		return this.main;
	}
}
