package com.OJToolkit.client.view;

import com.OJToolkit.client.Contents.MyResource;
import com.OJToolkit.client.event.AddAccountEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.OJToolkit.client.event.ViewSubmissionsEvent;
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
	
	enum Labels {ViewCoders,AddAccounts,ViewProblems, Status};
	Label lblViewCoders;
	Label lblAddAccount;
	Label lblViewProblem;
	Label lblStatus;

	
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
		

		 lblViewCoders = addLeftPanel_lable("View Coders", eventBus,
				Labels.ViewCoders);
		absolutePanel.add(lblViewCoders, 0, 140);

		lblViewProblem = addLeftPanel_lable("View Problems", eventBus,
				Labels.ViewProblems);
		absolutePanel.add(lblViewProblem, 0, 180);
		
		 lblAddAccount = addLeftPanel_lable("Add Accounts", eventBus,
				Labels.AddAccounts);
		absolutePanel.add(lblAddAccount, 0, 220);
		
		lblStatus = addLeftPanel_lable("Status", eventBus,
				Labels.Status);
		absolutePanel.add(lblStatus, 0, 260);

		 
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
				else if (LinkType.equals(Labels.Status))
					eventBus.fireEvent(new ViewSubmissionsEvent());
			}
		});
		return lblLabel;

	}

	@Override
	public Widget asWidget() {
		return this;
	}

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.LeftPanelPresenter.Display#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean isEnabled) {
    	 lblViewCoders.setVisible(isEnabled);
    	 lblAddAccount.setVisible(isEnabled);
    	 lblViewProblem.setVisible(isEnabled);
    	 lblStatus.setVisible(isEnabled);
    }

}
