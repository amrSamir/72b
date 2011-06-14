package com.OJToolkit.client.presenter;

import com.OJToolkit.client.Services.HintServiceAsync;
import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.presenter.InvitationPresenter.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class HintAddingPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSubmitButton();

		HasValue<String> getHintString();

		Widget asWidget();
	}

	

	private final Display display;
	private final HandlerManager eventBus;
	//private problem
	private HintServiceAsync hintService;
	private String problemID;
	
	
	public String getProblemID() {
		return problemID;
	}

	public void setProblemID(String problemID) {
		this.problemID = problemID;
	}

	public HintAddingPresenter(HintServiceAsync hintService,HandlerManager eventBus, final Display display) {
		this.hintService = hintService;
		this.eventBus = eventBus;
		this.display = display;

		bind();

	}
	
	public void bind()
	{
		display.getSubmitButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hintService.addHint(problemID, display.getHintString().getValue(), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						 Window.alert("AhmedNasser - HintAddingPresenter - Your Hint has been added, thanks!");
							
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						 Window.alert("AhmedNasser - HintAddingPresenter - Error adding your hint, sorry!");
							
						
					}
				});
				
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
	
	
}

	

 
