package com.OJToolkit_2.client.Contents;

import java.util.ArrayList;

import com.OJToolkit_2.client.CoreContainer;
import com.OJToolkit_2.client.Services.SubmissionService;
import com.OJToolkit_2.client.Services.SubmissionServiceAsync;
import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Grid;

public class ContentProblemList extends Content {
	private final SubmissionServiceAsync submissionService = GWT
			.create(SubmissionService.class);

	// private final LanguageServiceAsync languageService =
	// GWT.create(LanguageService.class);

	public ContentProblemList() {
		super();

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);

		final Grid grid = new Grid(51, 3);
		grid.setBorderWidth(2);
		absolutePanel.add(grid, 0, 0);
		grid.setWidth("100%");
		grid.setTitle("Problem List");
		grid.setText(0, 0, "Problem ID");
		grid.setText(0, 1, "Problem Name");
		grid.setText(0, 2, "Source Judge");

		submissionService.getProblems(0,
				new AsyncCallback<ArrayList<ProblemData>>() {

					@Override
					public void onSuccess(ArrayList<ProblemData> result) {
						// final ArrayList<ProblemData> resultFinal = result;
						for (int i = 0; i < result.size(); i++) {
							final String problemCode = result.get(i)
									.getProblemCode();
							grid.setText(i + 1, 0, problemCode);
							Anchor problemName = new Anchor(result.get(i)
									.getProblemName());
							problemName.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									CoreContainer.getInstance().setContent(
											new ContentProblemPage(

											problemCode));
								}
							});
							grid.setWidget(i + 1, 1, problemName);

							grid.setText(i + 1, 2, "SPOJ");
						}
						// TODO Auto-generated method stub

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failure");
						// TODO Auto-generated method stub

					}
				});

	}
}
