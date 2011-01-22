package com.gp.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Submission implements EntryPoint {
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 10, 10);
		verticalPanel.setSize("476px", "566px");
		
		Label lblProblemName = new Label("Problem Name");
		verticalPanel.add(lblProblemName);
		
		StackPanel stackPanel = new StackPanel();
		verticalPanel.add(stackPanel);
		stackPanel.setSize("465px", "509px");
		
		
		Frame frame = new Frame("http://www.google.com");
		stackPanel.add(frame, "Problem Description", false);
		frame.setSize("100%", "100%");
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		stackPanel.add(verticalPanel_1, "Submit", false);
		verticalPanel_1.setSize("100%", "100%");
		
		TextArea textArea = new TextArea();
		verticalPanel_1.add(textArea);
		textArea.setSize("434px", "318px");
		
		FileUpload fileUpload = new FileUpload();
		verticalPanel_1.add(fileUpload);
		
		Button button = new Button("New button");
		button.setText("Submit");
		verticalPanel_1.add(button);
		
		FlexTable flexTable = new FlexTable();
		stackPanel.add(flexTable, "Status", false);
		flexTable.setSize("100%", "100%");
		
		stackPanel.showStack(2);
	}
}
