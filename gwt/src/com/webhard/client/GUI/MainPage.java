package com.webhard.client.GUI;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPage extends Composite{

	
	public MainPage(){
		
		HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
		initWidget(horizontalSplitPanel);
		horizontalSplitPanel.setSize("687px", "455px");
		
		Grid grid = new Grid();
		grid.setBorderWidth(1);
		grid.setText(0, 1, "안녕");
		grid.setText(0, 2, "하이");
		grid.setText(0, 3, "방가");
		grid.setText(0, 4, "즐");
		horizontalSplitPanel.setRightWidget(grid);
		
		grid.setSize("100%", "100%");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("login");
		horizontalSplitPanel.setLeftWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");
		VerticalPanel vPanel = new VerticalPanel();
		
		

	}
}
