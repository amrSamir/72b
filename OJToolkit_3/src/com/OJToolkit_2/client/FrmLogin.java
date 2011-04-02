package com.OJToolkit_2.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;

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
