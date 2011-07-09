package com.OJToolkit.client;

import com.OJToolkit.client.Contents.MyResource;
import com.OJToolkit.client.Services.LanguageService;
import com.OJToolkit.client.Services.LanguageServiceAsync;
import com.OJToolkit.client.Services.SubmissionService;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemTextData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SpojProblemsToDBAdder {

	private final SubmissionServiceAsync submissionService = GWT
	        .create(SubmissionService.class);
	private final LanguageServiceAsync languageService = GWT
	        .create(LanguageService.class);
	int failures = 0;
	int success = 0;

	public SpojProblemsToDBAdder() {
		System.out.println("ahmedazraq-SpojProblemsToDBAdder-Initializer");
		// addSPOJProblems();
		// addLanguages();
		addSPOJProblemText();
	}

	private void addSPOJProblems() {
		String problems = MyResource.INSTANCE.defaultText().getText();
		String[] linesArr = problems.split("\n");
		ProblemData problem = new ProblemData();
		String[] splitted;

		for (int i = 0; i < linesArr.length; i++) {
			splitted = linesArr[i].split(" \\| ");
			problem.setProblemCode(splitted[0]);
			problem.setProblemName(splitted[1]);

			problem.setUrl(splitted[2].replaceAll("https", "http"));
			problem.setOjType("SPOJ");

			submissionService.saveSpojProblemtoDB(problem,
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
		System.out.println(" Failures " + String.valueOf(failures)
		        + " Success " + String.valueOf(success));
	}

	private void addSPOJProblemText() {
		String problemsText = MyResource.INSTANCE.SPOJProblemText().getText();
		String[] problemsArr = problemsText
		        .split("______________________________________________________");
		// ProblemData problem = new ProblemData();
		String[] problem;
		String[] problemTextDivisionArr;
		String isDividable = "";
		String problemCode = "";
		String fullText = "";
		String problemStatement = "";
		String inputConstraints = "";
		String outputConstraints = "";
		String IOTestCases = "";
		ProblemTextData problemTextData;
		try {
			for (int i = 0; i < problemsArr.length; i++) {
				problem = problemsArr[i].split("\\|{6}");
				problemCode = problem[0].trim();
				isDividable = problem[1].trim();
				if (isDividable.equals("false")) {
					fullText = problem[2];
					problemStatement = "";
					inputConstraints = "";
					outputConstraints = "";
					IOTestCases = "";
				} else {
					problemTextDivisionArr = problem[2]
					        .split("\\*{7}[a-zA-Z]*\\*{7}");
					problemStatement = problemTextDivisionArr[0];
					inputConstraints = problemTextDivisionArr[1];
					outputConstraints = problemTextDivisionArr[2];
					IOTestCases = problemTextDivisionArr[3];
				}
				problemTextData = new ProblemTextData("SPOJ", problemCode,
				        isDividable, fullText, problemStatement,
				        inputConstraints, outputConstraints, IOTestCases);

				submissionService.addProblemTextToDB(problemTextData,
				        new AsyncCallback<Void>() {
					        @Override
					        public void onSuccess(Void result) {
					        }

					        @Override
					        public void onFailure(Throwable caught) {
						        // TODO Auto-generated method stub
					        }
				        });

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(problemCode);
			System.out.println("problem state" + problemStatement);
			System.out.println("input Constraint" + inputConstraints);
			System.out.println("output constraint" + outputConstraints);
			System.out.println("io test cases" + IOTestCases);
		}
	}

	private void addLanguages() {
		languageService.addLanguages(new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				System.out.println("Languages added to DB");

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
}
