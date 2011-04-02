package com.OJToolkit_2.client;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

public class TestWestUi extends Content{

	public TestWestUi() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("197px", "279px");
		
		Label lblLabel = new Label("Label 1");
		absolutePanel.add(lblLabel, 39, 57);
		
		Label lblLabel_1 = new Label("Label 2");
		absolutePanel.add(lblLabel_1, 39, 136);
		
		Label lblLabel_2 = new Label("Label 3");
		absolutePanel.add(lblLabel_2, 39, 222);
		// TODO Auto-generated constructor stub
		
	}
}
