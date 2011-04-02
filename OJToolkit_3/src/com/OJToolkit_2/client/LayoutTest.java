package com.OJToolkit_2.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Label;

public class LayoutTest extends Composite {

	public LayoutTest() {
		
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		initWidget(dockLayoutPanel);
		
		Label lblLabelNorth = new Label("Label North");
		dockLayoutPanel.addNorth(lblLabelNorth, 7.5);
		
		Label lblLab = new Label("West");
		dockLayoutPanel.addWest(lblLab, 7.5);
		
		Label lblCenter = new Label("Center");
		dockLayoutPanel.add(lblCenter);
		
		Label lblEast = new Label("East");
		dockLayoutPanel.addEast(lblEast, -14.2);
	}
}
