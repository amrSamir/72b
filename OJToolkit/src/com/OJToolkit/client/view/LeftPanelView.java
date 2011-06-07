package com.OJToolkit.client.view;

import com.OJToolkit.client.Contents.MyResource;
import com.OJToolkit.client.event.AddAccountEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.OJToolkit.client.presenter.LeftPanelPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LeftPanelView extends Composite implements
		LeftPanelPresenter.Display {
	
	enum Labels {ViewCoders,AddAccounts,ViewProblems};

	
	// Hack to be fixed
	public LeftPanelView(final HandlerManager eventBus) {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("LeftPanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");

		ImageResource img = MyResource.INSTANCE.imgLogo();
		Image widget = new Image(img.getURL());
		widget.setSize("", "120px");
		absolutePanel.add(widget, 30, 10);
		

		Label lableViewCoders = addLeftPanel_lable("View Coders", eventBus,
				Labels.ViewCoders);
		absolutePanel.add(lableViewCoders, 0, 140);

		Label lableAddAccount = addLeftPanel_lable("Add Accounts", eventBus,
				Labels.AddAccounts);
		absolutePanel.add(lableAddAccount, 0, 220);

		Label lableViewProblem = addLeftPanel_lable("View Problems", eventBus,
				Labels.ViewProblems);
		absolutePanel.add(lableViewProblem, 0, 180);
	}

	private Label addLeftPanel_lable(String LableName,
			final HandlerManager eventBus, final Labels LinkType) {

		Label lblLabel = new Label(LableName);
		lblLabel.setStylePrimaryName("LeftPanel-Label");
		lblLabel.setSize("100%", "");
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

	@Override
	public Widget asWidget() {
		return this;
	}

}
