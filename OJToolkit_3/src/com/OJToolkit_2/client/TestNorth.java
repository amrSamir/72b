package com.OJToolkit_2.client;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

public class TestNorth extends Content{

	public TestNorth() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("TitlePanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("387px", "83px");
		
		Label lblWelcomeToOjtoolkit = new Label("Welcome To OJToolKit");
		absolutePanel.add(lblWelcomeToOjtoolkit, 99, 30);
		lblWelcomeToOjtoolkit.setSize("252px", "43px");
		// TODO Auto-generated constructor stub
		
	}
}
