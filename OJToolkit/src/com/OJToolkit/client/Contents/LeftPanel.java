package com.OJToolkit.client.Contents;

import com.OJToolkit.client.event.AddAccountEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * @author 72B
 */
public class LeftPanel extends Content {

	enum Labels {
		ViewCoders, AddAccounts, ViewProblems
	};

	/**
	 * genrate the left panel
	 * 
	 * @param eventBus
	 */
	public LeftPanel(final HandlerManager eventBus) {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("LeftPanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");

		ImageResource img = MyResource.INSTANCE.imgVertical();
		Image widget = new Image(img);

		absolutePanel.add(widget);
		String isEnabledCookie = Cookies.getCookie("isEnabledCookie");
		if (isEnabledCookie != null) {
			Label lableViewCoders = addLeftPanel_lable("View Coders", eventBus,
			        Labels.ViewCoders);
			absolutePanel.add(lableViewCoders, 39, 100);

			Label lableAddAccount = addLeftPanel_lable("Add Accounts",
			        eventBus, Labels.AddAccounts);
			absolutePanel.add(lableAddAccount, 39, 140);

			Label lableViewProblem = addLeftPanel_lable("View Problems",
			        eventBus, Labels.ViewProblems);
			absolutePanel.add(lableViewProblem, 39, 120);

			HTML feedback = new HTML("<a href=\"http://goo.gl/ZsQxt\" target=\"_blank\">Fill the evaluation form</a>"); 
			HTML bug = new HTML("<a href=\"http://goo.gl/aV0kZ\" target=\"_blank\">Report a bug</a>"); 
			
		
			absolutePanel.add(feedback, 39, 60);
			absolutePanel.add(bug,  39, 80);
		}

	}

	private Label addLeftPanel_lable(String LableName,
	        final HandlerManager eventBus, final Labels LinkType) {

		Label lblLabel = new Label(LableName);
		lblLabel.setStyleName("LeftPanel-Label");
		lblLabel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (LinkType.equals(Labels.ViewCoders))
					eventBus.fireEvent(new ViewCodersEvent());
				else if (LinkType.equals(Labels.AddAccounts))
					eventBus.fireEvent(new AddAccountEvent());
				else if (LinkType.equals(Labels.ViewProblems))
					eventBus.fireEvent(new AlreadyRegisteredEvent());
			}
		});
		return lblLabel;

	}
}
