package com.OJToolkit.client.Contents;

import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.RegistrationEvent;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class TestWestUi extends Content {

	public TestWestUi(final HandlerManager eventBus) {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("LeftPanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");

		ImageResource img = MyResource.INSTANCE.imgVertical();
		Image widget = new Image(img);

		absolutePanel.add(widget);

		String sessionID = Cookies.getCookie("reg");

		if (sessionID != null) {
			Label lblLabel = new Label("View Coders");
			lblLabel.setStyleName("LeftPanel-Label");
			lblLabel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					eventBus.fireEvent(new ViewCodersEvent());
					// CoreContainer.getInstance().setContent(
					// new ContentCoderList());
				}
			});
			absolutePanel.add(lblLabel, 39, 57);

			Label lblLabel_1 = new Label("View Problems");

			lblLabel_1.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					/*
					 * CoreContainer.getInstance().setContent(
					 * new ContentProblemList());
					 */
					eventBus.fireEvent(new AlreadyRegisteredEvent());
				}
			});
			absolutePanel.add(lblLabel_1, 39, 136);
			lblLabel_1.setStyleName("LeftPanel-Label");
		} else {
			if (Cookies.getCookie("loggedIn") != null) {
				Label lblLabel_2 = new Label("Registration");
				lblLabel_2.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						/*
						 * CoreContainer.getInstance().setContent(
						 * new ContentRegistration());
						 */
						eventBus.fireEvent(new RegistrationEvent());
					}
				});
				absolutePanel.add(lblLabel_2, 39, 222);

				lblLabel_2.setStyleName("LeftPanel-Label");
			}
			// TODO Auto-generated constructor stub

		}
	}
}
