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
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
public class CodersListPresenter implements Presenter {

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
	private int pageStart = 0;

	/**
	 * Generate coder list page
	 * @param coderService
	 * @param eventBus
	 * @param display
	 */
	public CodersListPresenter(CoderServiceAsync coderService,
			HandlerManager eventBus, final Display display) {
		
		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;
		codersTable = this.display.getTable();
		codersList = new  ArrayList<CoderData>() ;
		
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
				codersList.addAll(result);
				display.setCodersList(codersList);
//				for(int i = 0 ; i < result.size() ; i++){
//					codersList.set(pageStart + i, result.get(i)) ;
//				}
//				codersTable.setRowData(0,codersList);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to load coder Data!\n" + "Error : "+ caught.getMessage());
			}
		});

	}

	private void onClickHandler(){
		final SingleSelectionModel mySelectionModel = new SingleSelectionModel<CoderData>();
		codersTable.setSelectionModel(mySelectionModel) ;
		mySelectionModel.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				CoderData coder = (CoderData) mySelectionModel.getSelectedObject();
				//fire event
				//eventBus.fireEvent(new ViewCoderEvent(coder));
			}
		});
	}
	private void bind() {
		onClickHandler();
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
