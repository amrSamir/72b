package com.OJToolkit_2.client.Contents;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class TestNorth extends Content{

	public TestNorth() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("TitlePanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("387px", "83px");
		
		ImageResource img =  MyResource.INSTANCE.imgBanner();
		Image widget = new Image(img);
		
		absolutePanel.add(widget);
		Label lblWelcomeToOjtoolkit = new Label("Welcome To OJToolKit");
		absolutePanel.add(lblWelcomeToOjtoolkit, 99, 30);
		lblWelcomeToOjtoolkit.setSize("252px", "43px");
		// TODO Auto-generated constructor stub
		
	}
}
