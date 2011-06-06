package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class CodersPresenter implements Presenter {

	public interface Display {
		AbstractHasData<CoderData> getTable();
		void setCodersList(ArrayList<CoderData> coderlist);
		void setNumberOfCoders(int numOfCoders);
		void setPageStart(int pageStart);
		// void setCodersList(ArrayList<CoderData> coders);
		Widget asWidget();
	}

	private final Display display;
	private final CoderServiceAsync coderService;
	private final HandlerManager eventBus;
	private final AbstractHasData<CoderData> codersTable;
	private final ArrayList<CoderData> codersList;
	private final int numOfCoders = 1;
	private int pageStart = 0;

	public CodersPresenter(CoderServiceAsync coderService,
			HandlerManager eventBus, final Display display) {
		
		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;
		codersTable = this.display.getTable();
		this.display.setNumberOfCoders(numOfCoders);
		this.codersList = new ArrayList<CoderData>();
		for (int i = 0; i <= numOfCoders; i++) {
			codersList.add(null);
		}
		this.display.setCodersList(codersList);
		fitchCoders() ;
		this.display.setPageStart(pageStart);

	}

	public void fitchCoders() {
		coderService.viewCoders(new AsyncCallback<ArrayList<CoderData>>() {
			@Override
			public void onSuccess(ArrayList<CoderData> result) {
				display.setCodersList(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to load coder Data!\n" + "error : "
						+ caught.getMessage());
			}
		});

	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
