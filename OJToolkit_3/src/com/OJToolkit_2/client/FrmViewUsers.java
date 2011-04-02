package com.OJToolkit_2.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class FrmViewUsers extends Content{
	AbsolutePanel absolutePanel;
	public FrmViewUsers(ArrayList<CoderData> result) {
		 absolutePanel = new AbsolutePanel();

		initWidget(absolutePanel);
		for (CoderData coder : result) {
			viewCoder(coder);
		}
		
		// TODO Auto-generated constructor stub
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
