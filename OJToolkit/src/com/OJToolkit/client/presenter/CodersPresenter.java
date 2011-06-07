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
	private ArrayList<CoderData> codersList;
	private int numOfCoders = 1;
	private int pageStart = 0;

	/**
	 * Generate coder list page
	 * @param coderService
	 * @param eventBus
	 * @param display
	 */
	public CodersPresenter(CoderServiceAsync coderService,
			HandlerManager eventBus, final Display display) {
		
		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;
		codersTable = this.display.getTable();
		
		
		codersList = new  ArrayList<CoderData>() ;
		for (int i = 0; i <= numOfCoders; i++) {
			codersList.add(null);
		}
		this.display.setNumberOfCoders(numOfCoders);
		this.display.setCodersList(codersList);
		this.display.setPageStart(pageStart);
		fitchCoders() ;
	}

	/**
	 * Fetch all coder data
	 */
	private void fitchCoders() {
		coderService.viewCoders(new AsyncCallback<ArrayList<CoderData>>() {
			@Override
			public void onSuccess(ArrayList<CoderData> result) {
				for(int i = 0 ; i < result.size() ; i++){
					codersList.set(pageStart + i, result.get(i)) ;
				}
				codersTable.setRowData(0,codersList);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to load coder Data!\n" + "Error : "
						+ caught.getMessage());
			}
		});

	}

	/* (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
