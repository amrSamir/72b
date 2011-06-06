/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.presenter.RegistrationPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B Apr 26, 2011
 */
public class RegistrationView extends Composite implements
		RegistrationPresenter.Display {

	TextBox txtUsername;
	Button btnRegister;

	public RegistrationView() {
		
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("center");
		initWidget(verticalPanel);
		
		Label msg = new Label("You should register before using the site");
		verticalPanel.add(msg);

		Label lblRegistraionForm = new Label("Registration Form");
		verticalPanel.add(lblRegistraionForm);
		verticalPanel.setCellHorizontalAlignment(lblRegistraionForm, HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel userNamePabel = new HorizontalPanel() ;
		Label lblUsername = new Label("Username");
		userNamePabel.add(lblUsername);
		txtUsername = new TextBox();
		userNamePabel.add(txtUsername);
		txtUsername.setSize("141px", "16px");

		verticalPanel.add(userNamePabel);
		verticalPanel.setCellHorizontalAlignment(userNamePabel, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		/*
		 * Label lblSpojUsername = new Label("SPOJ Username");
		 * absolutePanel.add(lblSpojUsername); txtSPOJUsername = new TextBox();
		 * absolutePanel.add(txtSPOJUsername); txtSPOJUsername.setSize("141px",
		 * "16px"); Label lblSpojPassword = new Label("SPOJ Password");
		 * absolutePanel.add(lblSpojPassword); pwdSPOJ = new PasswordTextBox();
		 * absolutePanel.add(pwdSPOJ); pwdSPOJ.setSize("141px", "16px");
		 */

		btnRegister = new Button("Register");
		verticalPanel.add(btnRegister);
		btnRegister.setSize("100px", "28px");
		verticalPanel.setCellHorizontalAlignment(btnRegister, HasHorizontalAlignment.ALIGN_CENTER);

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
	 * com.OJToolkit.client.presenter.RegistrationPresenter.Display#getSubmitButton
	 * ()
	 */
	@Override
	public HasClickHandlers getSubmitButton() {
		return btnRegister;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.RegistrationPresenter.Display#getUsername
	 * ()
	 */
	@Override
	public HasValue<String> getUsername() {
		return txtUsername;
	}

}
