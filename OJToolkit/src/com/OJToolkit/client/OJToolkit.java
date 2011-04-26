package com.OJToolkit.client;

import java.util.ArrayList;

import com.OJToolkit.client.Contents.ContentLogin;
import com.OJToolkit.client.Contents.ContentProblemList;
import com.OJToolkit.client.Contents.TestNorth;
import com.OJToolkit.client.Contents.TestWestUi;
import com.OJToolkit.client.Services.SubmissionService;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
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
public class OJToolkit implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final SubmissionServiceAsync submissionService = GWT
	.create(SubmissionService.class);
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		dockLayoutPanel.setSize("100%", "100%");
		rootPanel.add(dockLayoutPanel, 0, 0);

      /**
       * North panel
       */
		TestNorth tn = new TestNorth();
		dockLayoutPanel.addNorth(tn, 10);
		tn.setSize("100%", "100%");

      /**
       * West panel
       */
		TestWestUi tw = new TestWestUi();
		dockLayoutPanel.addWest(tw, 15);
		tw.setSize("100%", "100%");

      /**
       * Center panel
       */
		AbsolutePanel core = new AbsolutePanel();
		dockLayoutPanel.add(core);
		CoreContainer.initialize(core);

		LoginHelper lh = new LoginHelper();

	}
}
