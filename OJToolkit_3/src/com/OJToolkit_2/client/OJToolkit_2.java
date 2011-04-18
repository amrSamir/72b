package com.OJToolkit_2.client;

import java.util.ArrayList;

import com.OJToolkit_2.client.Contents.TestNorth;
import com.OJToolkit_2.client.Contents.TestWestUi;
import com.OJToolkit_2.client.Services.ProblemService;
import com.OJToolkit_2.client.Services.ProblemServiceAsync;
import com.OJToolkit_2.client.ValueObjects.ProblemData;
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
		//CoreContainer.getInstance().setContent(new ContentProblemPage("problem id gdeda"));

		//addSomeProblems();
		
		LoginHelper lh = new LoginHelper();
	
	}
	/*private final ProblemServiceAsync probServAsync = GWT.create(ProblemService.class);
	public void addSomeProblems() {
		ProblemData pd;
		pd = new ProblemData("TEST", " Prime Generator", "http://www.spoj.pl/problems/PRIME1/", "Spoj");
		probServAsync.addProblem(pd, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				Window.alert("Added");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Not Added");
			}
		});
	}
*/
}
