package com.OJToolkit.client.Contents;

import java.util.ArrayList;

import com.OJToolkit.client.Services.CoderService;
import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ContentCoderList extends Content {
	AbsolutePanel absolutePanel;
	private final CoderServiceAsync coderService = GWT
			.create(CoderService.class);

	public ContentCoderList() {
		absolutePanel = new AbsolutePanel();

		initWidget(absolutePanel);
		viewCoders();

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

	private void viewCoders() {
		coderService.viewCoders(new AsyncCallback<ArrayList<CoderData>>() {

			@Override
			public void onSuccess(ArrayList<CoderData> result) {
				// Window.alert("Success_CoderData");

				for (CoderData coder : result) {
					viewCoder(coder);
				}

				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failure_CoderData");
				// TODO Auto-generated method stub

			}
		});
	}
}
