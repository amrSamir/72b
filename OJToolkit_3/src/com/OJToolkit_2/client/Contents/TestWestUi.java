package com.OJToolkit_2.client.Contents;

import com.OJToolkit_2.client.CoreContainer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

public class TestWestUi extends Content {

	public TestWestUi() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("LeftPanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("197px", "279px");

		String sessionID = Cookies.getCookie("reg");

		if (sessionID != null) {
			Label lblLabel = new Label("View Coders");
			lblLabel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					CoreContainer.getInstance().setContent(
							new ContentCoderList());
				}
			});
			absolutePanel.add(lblLabel, 39, 57);

			Label lblLabel_1 = new Label("View Problems");
			lblLabel_1.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					CoreContainer.getInstance().setContent(
							new ContentProblemList());
				}
			});
			absolutePanel.add(lblLabel_1, 39, 136);
		} else {
			Label lblLabel_2 = new Label("Registration");
			lblLabel_2.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					CoreContainer.getInstance().setContent(
							new ContentRegistration());
				}
			});
			absolutePanel.add(lblLabel_2, 39, 222);
			// TODO Auto-generated constructor stub

		}
	}
}
