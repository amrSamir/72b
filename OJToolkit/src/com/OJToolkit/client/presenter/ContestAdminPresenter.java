package com.OJToolkit.client.presenter;

import java.util.ArrayList;
import java.util.Date;

import com.OJToolkit.client.Services.ContestServicesAsync;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ContestAdminPresenter implements Presenter{

	public interface Display{
		HasClickHandlers getSubmitButton();
		HasValue<String> getContestName() ;
		HasValue<String> getContestAccessCode() ;
		String getContestNameDropList() ;
		Date getStartDate() ;
		Date getEndDate() ;
		Widget asWidget() ;
		void setContests(ArrayList<ContestData> contests);
	}
	
	private final Display display ;
	private final ContestServicesAsync contestServises ;
	private final HandlerManager eventBus ;
	private  ArrayList<ContestData> contests ;
	public ContestAdminPresenter(ContestServicesAsync contestServices,HandlerManager eventBus,final Display display) {
		this.display = display ;
		this.contestServises = contestServices ;
		this.eventBus = eventBus ;
		this.contests = new ArrayList<ContestData>();
		getContests();
		bind();
	}
	
	
	private void getContests() {
		contestServises.getContestForAdmin(new AsyncCallback<ArrayList<ContestData>>() {
			
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

	
	private void bind(){
		display.getSubmitButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(display.getContestNameDropList().equals("New Contest"))
					contestServises.addContest(display.getContestName().getValue(), display.getContestAccessCode().getValue(), display.getStartDate(),display.getEndDate() ,new AsyncCallback<Boolean>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Sorry , Can't save contest, try again later.") ;
						}
						@Override
						public void onSuccess(Boolean result) {
							if(result){
								Window.alert("Contest Created Succsefully.") ;
							}else{
								Window.alert("this Name Is Already Exsist!") ;
							}
						}
					});
				else{
					if(display.getContestName().getValue().equals("")){
						contestServises.changeAccessCode(display.getContestNameDropList(), display.getContestAccessCode().getValue() , new AsyncCallback<Boolean>() {
							@Override
							public void onSuccess(Boolean result) {
//								System.out.println("Magdi-contestAdmin-contest edited scussuly ");
								Window.alert("Contest Edited Succsefully.") ;
							}
							
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Sorry , Can't Edit contest, try again later.") ;
							}
						});
						
					}else{
						contestServises.changeContestName(display.getContestNameDropList(), display.getContestName().getValue(), new AsyncCallback<Boolean>() {
							@Override
							public void onSuccess(Boolean result) {
//								System.out.println("Magdi-contestAdmin-contest edited scussuly ");
								Window.alert("Contest Edited Succsefully.") ;
							}
							@Override
							public void onFailure(Throwable caught) {
//								Window.alert("Magdi-contestAdmin-sorryCant't change the contest data ! :(") ;
								Window.alert("Sorry , Can't Edit contest, try again later.") ;
							}
						});
					}
				}
					
					
			}
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear() ;
		container.add(display.asWidget()) ;
	}

	
}
