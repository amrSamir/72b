package com.OJToolkit.client.Contents;

import com.OJToolkit.client.event.AddAccountEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class LeftPanel extends Content {
	enum Labels {ViewCoders,AddAccounts,ViewProblems};
	
	public LeftPanel(final HandlerManager eventBus) {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("LeftPanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");

		ImageResource img = MyResource.INSTANCE.imgVertical();
		Image widget = new Image(img);

		absolutePanel.add(widget);
		
		Label lableViewCoders = addLeftPanel_lable("View Coders",eventBus,Labels.ViewCoders);
		absolutePanel.add(lableViewCoders, 39, 100);

		Label lableAddAccount = addLeftPanel_lable("Add Accounts",eventBus,Labels.AddAccounts);		
		absolutePanel.add(lableAddAccount, 39, 140);
		
		Label lableViewProblem = addLeftPanel_lable("View Problems",eventBus,Labels.ViewProblems);		
		absolutePanel.add(lableViewProblem, 39, 120);
		
	}
	Label addLeftPanel_lable(String LableName, final HandlerManager eventBus,final Labels L){
		
		Label lblLabel = new Label(LableName);
		lblLabel.setStyleName("LeftPanel-Label");
		lblLabel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(L.equals(Labels.ViewCoders))
					eventBus.fireEvent(new ViewCodersEvent());
				else if(L.equals(Labels.AddAccounts))
					eventBus.fireEvent(new AddAccountEvent());
				else if(L.equals(Labels.ViewProblems))
					eventBus.fireEvent(new AlreadyRegisteredEvent());
			}
		});
		return lblLabel ;
		
	}
}
