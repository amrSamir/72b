package com.OJToolkit.client.presenter;

import java.util.Collection;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		void setLogoutURL(String logoutURL, String username);
		HasClickHandlers getLogoutButton();
	}
	
	private final Display display;
	private final CoderServiceAsync coderService;
	
	public TopPanelPresenter(final String logoutURL,CoderServiceAsync coderService, final Display display) {
		this.display = display;
		this.coderService = coderService;
		bind();
		String isLoggedInCookie = Cookies.getCookie("isLoggedInCookie");
		if(isLoggedInCookie!=null){
			callGetUsername(logoutURL);
			//display.setLogoutURL(logoutURL, "ffsdfds");
			
		}
		
	}
	
	/**
     * 
     */
    private void callGetUsername(final String logoutURL) {
	   coderService.getUsername(new AsyncCallback<String>() {
		
		@Override
		public void onSuccess(String result) {
			display.setLogoutURL(logoutURL, result);
			
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}
	});
    }

	private void bind() {
		display.getLogoutButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				System.out.println("ae-toppanelpres-loggedout");
				Collection<String> cookies = Cookies.getCookieNames();
				for(String cookie : cookies){
					System.out.println("ae-toppanelpres-clearing cookie: " + cookie);
					Cookies.removeCookie(cookie);
				}
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
