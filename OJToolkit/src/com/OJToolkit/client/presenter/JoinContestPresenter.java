package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.ContestServicesAsync;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.view.JoinContestView;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class JoinContestPresenter implements Presenter {

	public interface Display{
		HasClickHandlers getSubmitButton();
		String getContestName() ;
		HasValue<String> getContestAccessCode() ;
		void setContests(ArrayList<ContestData> contests);
		void setContestSize(int size);
		Widget asWidget();
	}
	
	private final int contestSize ;
	private  ArrayList<ContestData> contests ;
	private final Display display ;
	private final ContestServicesAsync contestServises ;
	private final HandlerManager eventBus ;
	
	public JoinContestPresenter(ContestServicesAsync contestServices,HandlerManager eventBus,Display display) {
		
		this.contestServises = contestServices ;
		this.eventBus = eventBus ;
		this.contests = new ArrayList<ContestData>();
		getContests();
		System.out.println("Magdi-presnter-join- Number of contests = "+ contests.size());
		contestSize = contests.size();
		this.display = display ;
		this.display.setContests(contests);
		this.display.setContestSize(contestSize);
		bind();
	}
	
	private void getContests() {
		contestServises.getContests(new AsyncCallback<ArrayList<ContestData>>() {
			
			@Override
			public void onSuccess(ArrayList<ContestData> result) {
				contests.addAll(result);
				display.setContests(contests);
			}
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Magdi-join contest - canot load contest");
			}
		});
	}

	private void bind() {
		display.getSubmitButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				contestServises.addUserToContest(display.getContestName(),display.getContestAccessCode().getValue(),new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						
						System.out.println("Magdi-join contest-user not added");
					}

					@Override
					public void onSuccess(Boolean result) {
						if(result == true)
							System.out.println("Magdi-join contest-user added ");
						else 
							Window.alert("Wrong Contest Code or u r already rejesterd") ;
					}
				});
				
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear() ;
		container.add(display.asWidget()) ;
	}
	
}
