package com.OJToolkit_2.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.OJToolkit_2.client.Contents.Content;
import com.OJToolkit_2.client.Services.SubmissionService;
import com.OJToolkit_2.client.Services.SubmissionServiceAsync;
import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.OJToolkit_2.server.SubmissionServiceImpl;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

public class SpojProblemsToDBAdder extends Content {

	private final SubmissionServiceAsync submissionService = GWT
			.create(SubmissionService.class);
	int failures = 0;
	int success = 0;

	public SpojProblemsToDBAdder() {

		ScrollPanel scrollPanel = new ScrollPanel();
		initWidget(scrollPanel);

		TextArea textArea = new TextArea();
		scrollPanel.setWidget(textArea);
		textArea.setSize("100%", "100%");
		String problems = MyResource.INSTANCE.defaultText().getText();
		String[] linesArr = problems.split("\n");
		// Window.alert(linesArr[0]);
		ProblemData problemSpoj = new ProblemData();
		String[] splitted;
		String txt = "";
		/*
		 * submissionService.saveSpojProblemtoDB(problemSpoj, new
		 * AsyncCallback<Void>() {
		 * 
		 * @Override public void onSuccess(Void result) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void onFailure(Throwable caught) { failures =
		 * failures + 1; // TODO Auto-generated method stub
		 * 
		 * } });
		 */

		for (int i = 0; i < linesArr.length; i++) {
			splitted = linesArr[i].split("\" ");
			problemSpoj.setProblemCode(splitted[0].replaceAll("\"", ""));
			problemSpoj.setProblemName(splitted[1].replaceAll("\"", ""));
			problemSpoj.setType(splitted[2].replaceAll("\"", ""));
			problemSpoj.setUrl(splitted[3].replaceAll("\"", "").replaceAll(
					"https", "http"));

			/*
			 * txt += " Problem Code " + problemSpoj.getProblemCode() +
			 * " Problem Name " + problemSpoj.getProblemName() +
			 * " Problem Type " + problemSpoj.getType() + "Problem URL " +
			 * problemSpoj.getUrl() + "\n";
			 */

			submissionService.saveSpojProblemtoDB(problemSpoj,
					new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							success += 1;

						}

						@Override
						public void onFailure(Throwable caught) {
							failures = failures + 1;
							// TODO Auto-generated method stub

						}
					});

		}
		textArea.setText(" Failures " + String.valueOf(failures) + " Success "
				+ String.valueOf(success));

		// TODO Auto-generated constructor stub
	}

}
