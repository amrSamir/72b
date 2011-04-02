package com.OJToolkit_2.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Grid;

public class ContentProblemList extends Content {

	public ContentProblemList() {
		super();

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);

		Grid grid = new Grid(5, 3);
		grid.setBorderWidth(2);
		absolutePanel.add(grid, 0, 0);
		grid.setSize("100%", "100%");
		grid.setTitle("Problem List");
		grid.setText(0, 0, "Problem ID");
		grid.setText(0, 1, "Problem Name");
		grid.setText(0, 2, "Source Judge");

		grid.setText(1, 0, "TEST");
		Anchor problemName = new Anchor("Life, the Universe, and Everything");
		problemName.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				CoreContainer.getInstance().setContent(new FrmRegistration());
			}
		});
		grid.setWidget(1, 1, problemName);

		grid.setText(1, 2, "SPOJ");
	}
}
