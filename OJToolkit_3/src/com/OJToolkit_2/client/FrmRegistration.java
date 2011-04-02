package com.OJToolkit_2.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class FrmRegistration  extends Content{

	public FrmRegistration() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		
		Label lblUsername = new Label("FrmRegistration");
		absolutePanel.add(lblUsername);
		
		Label lblUsername_1 = new Label("Username");
		absolutePanel.add(lblUsername_1, 41, 51);
		
		 TextBox txtUsername = new TextBox();
		absolutePanel.add(txtUsername, 166, 51);
		txtUsername.setSize("141px", "16px");
			
		Label lblSpojUsername = new Label("SPOJ Username");
		absolutePanel.add(lblSpojUsername, 41, 94);
		
		TextBox txtSPOJUsername = new TextBox();
		absolutePanel.add(txtSPOJUsername, 166, 88);
		txtSPOJUsername.setSize("141px", "16px");
		
		Label lblSpojPassword = new Label("SPOJ Password");
		absolutePanel.add(lblSpojPassword, 41, 136);
		
		PasswordTextBox pwdSPOJ = new PasswordTextBox();
		absolutePanel.add(pwdSPOJ, 166, 130);
		pwdSPOJ.setSize("141px", "16px");
		
		Button btnRegister = new Button("Register");
		absolutePanel.add(btnRegister, 126, 173);
		btnRegister.setSize("100px", "28px");

		
		// TODO Auto-generated constructor stub
	}
	

}
