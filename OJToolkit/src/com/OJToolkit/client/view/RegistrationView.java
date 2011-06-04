/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.presenter.RegistrationPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class RegistrationView extends Composite implements
        RegistrationPresenter.Display {

	TextBox txtUsername;
	Button btnRegister;

	public RegistrationView() {
		VerticalPanel absolutePanel = new VerticalPanel();
		initWidget(absolutePanel);
		Label msg = new Label("You should register before using the site");
		absolutePanel.add(msg);

		Label lblUsername = new Label("Registration Form");
		absolutePanel.add(lblUsername);

		Label lblUsername_1 = new Label("Username");
		absolutePanel.add(lblUsername_1);

		txtUsername = new TextBox();
		absolutePanel.add(txtUsername);
		txtUsername.setSize("141px", "16px");

		/*
		 * Label lblSpojUsername = new Label("SPOJ Username");
		 * absolutePanel.add(lblSpojUsername);
		 * txtSPOJUsername = new TextBox();
		 * absolutePanel.add(txtSPOJUsername);
		 * txtSPOJUsername.setSize("141px", "16px");
		 * Label lblSpojPassword = new Label("SPOJ Password");
		 * absolutePanel.add(lblSpojPassword);
		 * pwdSPOJ = new PasswordTextBox();
		 * absolutePanel.add(pwdSPOJ);
		 * pwdSPOJ.setSize("141px", "16px");
		 */

		btnRegister = new Button("Register");
		absolutePanel.add(btnRegister);
		btnRegister.setSize("100px", "28px");

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
	 * com.OJToolkit.client.presenter.RegistrationPresenter.Display#getSubmitButton
	 * ()
	 */
	@Override
	public HasClickHandlers getSubmitButton() {
		// TODO Auto-generated method stub
		return btnRegister;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.RegistrationPresenter.Display#getUsername
	 * ()
	 */
	@Override
	public HasValue<String> getUsername() {
		// TODO Auto-generated method stub
		return txtUsername;
	}

}
