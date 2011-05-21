/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.presenter.LoginPresenter;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class LoginView extends Composite implements LoginPresenter.Display {

	Anchor signInLink;

	public LoginView() {
		AbsolutePanel absolutePanel = new AbsolutePanel();

		initWidget(absolutePanel);
		Label loginLabel = new Label(
		        "Please sign in to your Google Account to access the OJToolkit application.");
		signInLink = new Anchor("Sign In");

		absolutePanel.add(loginLabel);
		absolutePanel.add(signInLink);

	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#asWidget()
	 */
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.LoginPresenter.Display#setLoginURL(java
	 * .lang.String)
	 */
	@Override
	public void setLoginURL(String loginURL) {
		signInLink.setHref(loginURL);
		// TODO Auto-generated method stub

	}

}
