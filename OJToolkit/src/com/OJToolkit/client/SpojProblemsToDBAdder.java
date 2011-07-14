package com.OJToolkit.client;

import com.OJToolkit.client.Contents.MyResource;
import com.OJToolkit.client.Services.LanguageService;
import com.OJToolkit.client.Services.LanguageServiceAsync;
import com.OJToolkit.client.Services.SubmissionService;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemTextData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
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
	addLanguages();
		/*try {
			addProblems("Timus");
			addProblemsTextFromTextResource("Timus");
			
			//addProblems("SPOJ");
			//addProblemsTextFromTextResource("SPOJ");
		//	addProblems("UVA");
	//		addProblemsTextFromTextResource("UVA");
			
			//addProblems("LiveArchive");
		} catch (ResourceException e) {
			System.out.println("CAn't load file");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*
		 * try {
		 * addProblems("Timus");
		 * } catch (ResourceException e) {
		 * // TODO Auto-generated catch block
		 * e.printStackTrace();
		 * }
		 */

	}

	private void addProblems(final String judgeType) throws ResourceException {
		String problems = "";
		if (judgeType.equals("Timus")) {
			MyResource.INSTANCE.TimusProblems().getText(
			        new ResourceCallback<TextResource>() {

				        @Override
				        public void onSuccess(TextResource resource) {
					        System.out.println("Timus Problems");
					        addProblemsHelper(judgeType, resource.getText());
				        }

				        @Override
				        public void onError(ResourceException e) {
					        // TODO Auto-generated method stub

				        }
			        });
		}
		else if (judgeType.equals("SPOJ")) {
			MyResource.INSTANCE.SPOJProblems().getText(
					new ResourceCallback<TextResource>() {
						
						@Override
						public void onSuccess(TextResource resource) {
							System.out.println("SPOJ Problems");
							addProblemsHelper(judgeType, resource.getText());
						}
						
						@Override
						public void onError(ResourceException e) {
							// TODO Auto-generated method stub
							
						}
					});
		}
		else if (judgeType.equals("UVA")) {
			MyResource.INSTANCE.UVAProblems().getText(
					new ResourceCallback<TextResource>() {
						
						@Override
						public void onSuccess(TextResource resource) {
							System.out.println("UVA Problems");
							addProblemsHelper(judgeType, resource.getText());
						}
						
						@Override
						public void onError(ResourceException e) {
							// TODO Auto-generated method stub
							
						}
					});
		}
		else if (judgeType.equals("LiveArchive")) {
			MyResource.INSTANCE.LiveArchiveProblems().getText(
					new ResourceCallback<TextResource>() {
						
						@Override
						public void onSuccess(TextResource resource) {
							System.out.println("LiveArchive Problems");
							addProblemsHelper(judgeType, resource.getText());
						}
						
						@Override
						public void onError(ResourceException e) {
							// TODO Auto-generated method stub
							
						}
					});
		}

	}

	public void addProblemsHelper(String judgeType, String problems) {
		String[] linesArr = problems.split("\n");
		ProblemData problem = new ProblemData();
		String[] splitted;
		for (int i = 0; i < linesArr.length; i++) {
			splitted = linesArr[i].split(" \\| ");
			problem.setProblemCode(splitted[0]);
			problem.setProblemName(splitted[1]);
			problem.setUrl(splitted[2].replaceAll("https", "http"));
			problem.setOjType(judgeType);
			submissionService.saveProblemstoDB(problem,
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

	private void addProblemsText(String judgeType, String problemsText) {
		String[] problemsArr = problemsText
		        .split("______________________________________________________");
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
		int i = 0;
		try {

			for (i = 0; i < problemsArr.length; i++) {
				// System.out.println(i);
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
				problemTextData = new ProblemTextData(judgeType, problemCode,
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
			System.out.println("Finished el7");
		} catch (Exception e) {
			System.out.println("EXCEEEEEEEEEEEEPTION");
			System.out.println(problemsArr[i - 1].substring(0, 10));
			System.out.println(problemsArr[i].substring(0, 10));
			System.out.print("PROBLEM CODE: ");
			System.out.println(problemCode);
			System.out.println("problem state" + problemStatement);
			System.out.println("input Constraint" + inputConstraints);
			System.out.println("output constraint" + outputConstraints);
			System.out.println("io test cases" + IOTestCases);
		}

	}

	private void uva2() throws ResourceException {
		MyResource.INSTANCE.UVAProblemText2().getText(
		        new ResourceCallback<TextResource>() {

			        @Override
			        public void onSuccess(TextResource resource) {
				        System.out.println("uva2");
				        addProblemsText("UVA", resource.getText());
				        try {
					        uva3();
				        } catch (ResourceException e) {
					        // TODO Auto-generated catch block
					        e.printStackTrace();
				        }
			        }

			        @Override
			        public void onError(ResourceException e) {
				        // TODO Auto-generated method stub

			        }
		        });
	}

	private void uva3() throws ResourceException {
		MyResource.INSTANCE.UVAProblemText3().getText(
		        new ResourceCallback<TextResource>() {

			        @Override
			        public void onSuccess(TextResource resource) {
				        System.out.println("uva3");
				        addProblemsText("UVA", resource.getText());
				        try {
					        uva4();
				        } catch (ResourceException e) {
					        // TODO Auto-generated catch block
					        e.printStackTrace();
				        }
			        }

			        @Override
			        public void onError(ResourceException e) {
				        // TODO Auto-generated method stub

			        }
		        });
	}

	private void uva4() throws ResourceException {
		MyResource.INSTANCE.UVAProblemText4().getText(
		        new ResourceCallback<TextResource>() {

			        @Override
			        public void onSuccess(TextResource resource) {
				        System.out.println("uva4");
				        addProblemsText("UVA", resource.getText());
				        try {
					        uva5();
				        } catch (ResourceException e) {
					        // TODO Auto-generated catch block
					        e.printStackTrace();
				        }
			        }

			        @Override
			        public void onError(ResourceException e) {
				        // TODO Auto-generated method stub

			        }
		        });
	}

	private void uva5() throws ResourceException {
		MyResource.INSTANCE.UVAProblemText5().getText(
		        new ResourceCallback<TextResource>() {

			        @Override
			        public void onSuccess(TextResource resource) {
				        System.out.println("uva5");
				        addProblemsText("UVA", resource.getText());
				        try {
					        uva6();
				        } catch (ResourceException e) {
					        // TODO Auto-generated catch block
					        e.printStackTrace();
				        }
			        }

			        @Override
			        public void onError(ResourceException e) {
				        // TODO Auto-generated method stub

			        }
		        });
	}

	private void uva6() throws ResourceException {
		MyResource.INSTANCE.UVAProblemText6().getText(
		        new ResourceCallback<TextResource>() {

			        @Override
			        public void onSuccess(TextResource resource) {
				        System.out.println("uva6");
				        addProblemsText("UVA", resource.getText());
				        try {
					        uva7();
				        } catch (ResourceException e) {
					        // TODO Auto-generated catch block
					        e.printStackTrace();
				        }
			        }

			        @Override
			        public void onError(ResourceException e) {
				        // TODO Auto-generated method stub

			        }
		        });
	}

	private void uva7() throws ResourceException {
		MyResource.INSTANCE.UVAProblemText7().getText(
		        new ResourceCallback<TextResource>() {

			        @Override
			        public void onSuccess(TextResource resource) {
				        System.out.println("uva7");
				        addProblemsText("UVA", resource.getText());
				        try {
					        uva8();
				        } catch (ResourceException e) {
					        // TODO Auto-generated catch block
					        e.printStackTrace();
				        }
			        }

			        @Override
			        public void onError(ResourceException e) {
				        // TODO Auto-generated method stub

			        }
		        });
	}

	private void uva8() throws ResourceException {
		MyResource.INSTANCE.UVAProblemText8().getText(
		        new ResourceCallback<TextResource>() {

			        @Override
			        public void onSuccess(TextResource resource) {
				        System.out.println("uva8");
				        addProblemsText("UVA", resource.getText());
				        System.out.println("el7777777777 done");
			        }

			        @Override
			        public void onError(ResourceException e) {
				        // TODO Auto-generated method stub

			        }
		        });
	}

	private void addProblemsTextFromTextResource(String judgeType)
	        throws ResourceException {

		if (judgeType.equals("SPOJ")) {
			MyResource.INSTANCE.SPOJProblemText1().getText(
			        new ResourceCallback<TextResource>() {

				        @Override
				        public void onSuccess(TextResource resource) {
					        System.out.println("SPOJ1");
					        addProblemsText("SPOJ", resource.getText());
							try {
	                            MyResource.INSTANCE.SPOJProblemText2().getText(
	                                    new ResourceCallback<TextResource>() {

	                            	        @Override
	                            	        public void onSuccess(TextResource resource) {
	                            		        System.out.println("SPOJ2");
	                            		        addProblemsText("SPOJ", resource.getText());

	                            	        }

	                            	        @Override
	                            	        public void onError(ResourceException e) {
	                            		        // TODO Auto-generated method stub

	                            	        }
	                                    });
                            } catch (ResourceException e) {
	                            // TODO Auto-generated catch block
	                            e.printStackTrace();
                            }

				        }

				        @Override
				        public void onError(ResourceException e) {
					        // TODO Auto-generated method stub

				        }
			        });
		} else if (judgeType.equals("Timus")) {
			MyResource.INSTANCE.TimusProblemText().getText(
			        new ResourceCallback<TextResource>() {

				        @Override
				        public void onSuccess(TextResource resource) {
					        System.out.println("timus");
					        addProblemsText("Timus", resource.getText());

				        }

				        @Override
				        public void onError(ResourceException e) {
					        // TODO Auto-generated method stub

				        }
			        });
		} else if (judgeType.equals("UVA")) {
			MyResource.INSTANCE.UVAProblemText1().getText(
			        new ResourceCallback<TextResource>() {

				        @Override
				        public void onSuccess(TextResource resource) {
					        System.out.println("uva1");
					        addProblemsText("UVA", resource.getText());
					        try {
						        uva2();
					        } catch (ResourceException e) {
						        // TODO Auto-generated catch block
						        System.out.println("uva 2 file exception");
					        }
				        }

				        @Override
				        public void onError(ResourceException e) {
					        // TODO Auto-generated method stub

				        }
			        });
			/*
			 * MyResource.INSTANCE.UVAProblemText3().getText(
			 * new ResourceCallback<TextResource>() {
			 * @Override
			 * public void onSuccess(TextResource resource) {
			 * System.out.println("uva3");
			 * addProblemsText("UVA", resource.getText());
			 * }
			 * @Override
			 * public void onError(ResourceException e) {
			 * // TODO Auto-generated method stub
			 * }
			 * });
			 * MyResource.INSTANCE.UVAProblemText4().getText(
			 * new ResourceCallback<TextResource>() {
			 * @Override
			 * public void onSuccess(TextResource resource) {
			 * System.out.println("uva4");
			 * addProblemsText("UVA", resource.getText());
			 * }
			 * @Override
			 * public void onError(ResourceException e) {
			 * // TODO Auto-generated method stub
			 * }
			 * });
			 * MyResource.INSTANCE.UVAProblemText5().getText(
			 * new ResourceCallback<TextResource>() {
			 * @Override
			 * public void onSuccess(TextResource resource) {
			 * System.out.println("uva5");
			 * addProblemsText("UVA", resource.getText());
			 * }
			 * @Override
			 * public void onError(ResourceException e) {
			 * // TODO Auto-generated method stub
			 * }
			 * });
			 * MyResource.INSTANCE.UVAProblemText6().getText(
			 * new ResourceCallback<TextResource>() {
			 * @Override
			 * public void onSuccess(TextResource resource) {
			 * System.out.println("uva6");
			 * addProblemsText("UVA", resource.getText());
			 * }
			 * @Override
			 * public void onError(ResourceException e) {
			 * // TODO Auto-generated method stub
			 * }
			 * });
			 * MyResource.INSTANCE.UVAProblemText7().getText(
			 * new ResourceCallback<TextResource>() {
			 * @Override
			 * public void onSuccess(TextResource resource) {
			 * System.out.println("uva7");
			 * addProblemsText("UVA", resource.getText());
			 * }
			 * @Override
			 * public void onError(ResourceException e) {
			 * // TODO Auto-generated method stub
			 * }
			 * });
			 * MyResource.INSTANCE.UVAProblemText8().getText(
			 * new ResourceCallback<TextResource>() {
			 * @Override
			 * public void onSuccess(TextResource resource) {
			 * System.out.println("uva8");
			 * addProblemsText("UVA", resource.getText());
			 * }
			 * @Override
			 * public void onError(ResourceException e) {
			 * // TODO Auto-generated method stub
			 * }
			 * });
			 */
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
