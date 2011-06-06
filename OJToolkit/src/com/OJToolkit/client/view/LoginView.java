/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.presenter.LoginPresenter;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B Apr 26, 2011
 */
public class LoginView extends Composite implements LoginPresenter.Display {

	Anchor signInLink;

	public LoginView() {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setSize("100%", "100%");
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("center");
		
		
		Label loginLabel = new Label("Please sign in to your Google Account to access the OJToolkit application.");
		verticalPanel.add(loginLabel) ;
		verticalPanel.setCellHorizontalAlignment(loginLabel, HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(absolutePanel);

		signInLink = new Anchor("Sign In");
		verticalPanel.setCellHorizontalAlignment(signInLink, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(signInLink);

		absolutePanel.add(verticalPanel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.LoginPresenter.Display#setLoginURL(java
	 * .lang.String)
	 */
	@Override
	public void setLoginURL(String loginURL) {
		signInLink.setHref(loginURL);
	}

}
