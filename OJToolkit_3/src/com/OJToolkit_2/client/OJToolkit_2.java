package com.OJToolkit_2.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("unused")
public class OJToolkit_2 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final coderServiceAsync coderService = GWT
			.create(coderService.class);

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	private LoginInfo loginInfo;
	private AbsolutePanel core;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		dockLayoutPanel.setSize("100%", "100%");
		rootPanel.add(dockLayoutPanel, 0, 0);

		TestNorth tn = new TestNorth();
		dockLayoutPanel.addNorth(tn, 10);
		tn.setSize("100%", "100%");

		TestWestUi tw = new TestWestUi();
		dockLayoutPanel.addWest(tw, 15);
		tw.setSize("100%", "100%");

		AbsolutePanel core = new AbsolutePanel();
		dockLayoutPanel.add(core);
		CoreContainer.initialize(core);

		LoginHelper lh = new LoginHelper();
		//CoreContainer.getInstance().setContent(new LoginHelper());

//		CoreContainer.getInstance().setContent(new FrmRegistration());
	}

	public void checkRegistered() {
		coderService.checkRegistered(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				// Window.alert("Registered " + result );
				if (result == true) {

					viewCoders();

				} else {
					// core.clear();
					core.add(new FrmRegistration());

				}

			}

			@Override
			public void onFailure(Throwable caught) {
				// core.clear();
				core.add(new FrmLogin(loginInfo.getLoginUrl()));
			}
		});
	}

	private void viewCoders() {
		coderService.viewCoders(new AsyncCallback<ArrayList<CoderData>>() {

			@Override
			public void onSuccess(ArrayList<CoderData> result) {
				// Window.alert("Success_CoderData");
				// core.clear();
				core.add(new FrmViewUsers(result));
				// signOutLink.setHref(loginInfo.getLogoutUrl());
				// rootPanel.add(signOutLink);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failure_CoderData");
				// TODO Auto-generated method stub

			}
		});
	}

}
