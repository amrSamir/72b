package com.OJToolkit.client.presenter;

import com.OJToolkit.client.Contents.MyResource;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AboutPresenter implements Presenter {
	interface Binder extends UiBinder<Widget, AboutPresenter> {
	}

	

	/**
	 * The event bus to notify event changes
	 */
	private final HandlerManager eventBus;

	@UiField(provided = true)
	Image imgFCI = new Image(MyResource.INSTANCE.imgFCILogo());
	
	
	@UiField(provided = true)
	Image imgCU = new Image(MyResource.INSTANCE.imgCULogo());
	
	public AboutPresenter(HandlerManager eventBus) {
		super();
		this.eventBus = eventBus;
			
		
	}

	@Override
	public void go(HasWidgets container) {		

		// Create the UiBinder.
		Binder uiBinder = GWT.create(Binder.class);
		Widget widget = uiBinder.createAndBindUi(this);
		
		// Clear and draw on container
		container.clear();
		container.add(widget);

		imgFCI.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						Window.open("http://www.fci-cu.edu.eg/", "_blank", "");
						
					}
				});
				
				imgCU.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						Window.open("http://193.227.13.20/index.php", "_blank", "");
						
					}
				});
			

	}
}
