package com.OJToolkit_2.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;

/*
 * Steps for Login:
 * 1 - Call LoginService
 * 	a - On success
 * 		i - is he logged in then check registration
 * 		ii - not logged in then login page
 * b - On failure - Alert
 * 
 * Steps for Check Registration:
 * 1 - Call CheckRegisteredService
 * 	a - On Success
 * 		i - Registered = true, proceed to next page
 * 		ii - Not Registered , proceed to registration form
 * 	b - On failure - Login Form
 */
public class FrmLogin extends Content {

	public FrmLogin(String loginURL) {
		AbsolutePanel absolutePanel = new AbsolutePanel();

		initWidget(absolutePanel);
		Label loginLabel = new Label(
				"Please sign in to your Google Account to access the OJToolkit application.");
		Anchor signInLink = new Anchor("Sign In");
		signInLink.setHref(loginURL);
		absolutePanel.add(loginLabel);
		absolutePanel.add(signInLink);
		
		
		
		

		// TODO Auto-generated constructor stub
	}

}
