package com.OJToolkit.client.presenter;

import java.util.Collection;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		void setLogoutURL(String logoutURL);
		HasClickHandlers getLogoutButton();
	}
	
	private final Display display;
	
	public TopPanelPresenter(String logoutURL, final Display display) {
		this.display = display;
		bind();
		String isLoggedInCookie = Cookies.getCookie("isLoggedInCookie");
		if(isLoggedInCookie!=null){
			display.setLogoutURL(logoutURL);
		}
		
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
