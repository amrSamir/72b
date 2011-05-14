/**
 * 
 */
package com.OJToolkit.client.view;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.presenter.CodersPresenter;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class CodersView extends Composite implements CodersPresenter.Display {

	AbsolutePanel absolutePanel;

	public CodersView() {
		absolutePanel = new AbsolutePanel();

		initWidget(absolutePanel);
	

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


	

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.CodersPresenter.Display#setCodersList(java.util.ArrayList)
     */
    @Override
    public void setCodersList(ArrayList<CoderData> coders) {
    	for (CoderData coder : coders) {
			viewCoder(coder);
		}
	    // TODO Auto-generated method stub
	    
    }
    
	public void viewCoder(CoderData coder) {

		Label lblUserData = new Label("User Data");
		absolutePanel.add(lblUserData);

		Label lblNewLabel = new Label("UserID");
		absolutePanel.add(lblNewLabel);

		TextBox txtUserID = new TextBox();
		txtUserID.setText(coder.getUserID().toString());
		absolutePanel.add(txtUserID);

		Label lblUsername = new Label("Username");
		absolutePanel.add(lblUsername);

		TextBox txtUserName = new TextBox();
		txtUserName.setText(coder.getUsername());
		absolutePanel.add(txtUserName);

		Label lblEmail = new Label("Email");
		absolutePanel.add(lblEmail);

		TextBox txtEmail = new TextBox();
		txtEmail.setText(coder.getEmail());
		absolutePanel.add(txtEmail);

		Label lblSpojusername = new Label("SPOJUsername");
		absolutePanel.add(lblSpojusername);

		TextBox txtSPOJUsername = new TextBox();
		txtSPOJUsername.setText(coder.getSPOJUsername());
		absolutePanel.add(txtSPOJUsername);

		Label lblSpojpassword = new Label("SPOJPassword");
		absolutePanel.add(lblSpojpassword);

		TextBox txtSpojPassword = new TextBox();
		txtSpojPassword.setText(coder.getSPOJPassword());
		absolutePanel.add(txtSpojPassword);
	}


}
