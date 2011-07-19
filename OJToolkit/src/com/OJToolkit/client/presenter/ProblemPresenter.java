package com.OJToolkit.client.presenter;

import gdurelle.tagcloud.client.tags.TagCloud;
import gdurelle.tagcloud.client.tags.WordTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.OJToolkit.client.Services.HintServiceAsync;
import com.OJToolkit.client.Services.LanguageServiceAsync;
import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.LanguageData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemTextData;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.weborient.codemirror.client.CodeMirrorEditorWidget;

public class ProblemPresenter implements Presenter {
	interface Binder extends UiBinder<Widget, ProblemPresenter> {
	}

	@UiField
	Anchor lblPrblmTitle;

	@UiField
	Hyperlink lblAnon;

	@UiField
	TagCloud tagCloud;

	@UiField
	DisclosurePanel dpDivided;

	@UiField
	DisclosurePanel dpNotDivided;

	@UiField
	HTML htmlProblemStatement;

	@UiField
	HTML htmlInputConstraints;

	@UiField
	HTML htmlOutputConstraints;

	@UiField
	HTML htmlTestCases;

	@UiField
	HTML htmlProblemText;

	@UiField
	CodeMirrorEditorWidget codeArea;

	@UiField
	ListBox listboxLanguages;

	@UiField
	ListBox listboxCategories;

	@UiField
	CheckBox cbIsCodeVisible;

	@UiField
	Button btnSubmit;

	private boolean isAnonymousSubmission;
	private final LanguageServiceAsync languageService;
	private final SourceCodeServiceAsync sourceCodeService;
	private final SubmissionServiceAsync submssionService;
	private final HintServiceAsync hintService;
	private final HandlerManager eventBus;
	private ProblemData problem;
	private final String problemCode;
	private final String ojType;

	public ProblemPresenter(String substring,
			SubmissionServiceAsync submssionService,
			LanguageServiceAsync languageService, HandlerManager eventBus,
			SourceCodeServiceAsync sourceCodeService,
			HintServiceAsync hintService) {
		super();
		this.languageService = languageService;
		this.submssionService = submssionService;
		this.eventBus = eventBus;
		String[] tokensArr = substring.split("-");
		this.problemCode = tokensArr[0];
		this.ojType = tokensArr[1];
		this.sourceCodeService = sourceCodeService;
		this.hintService = hintService;
	}

	public void go(HasWidgets container) {
		Binder uiBinder = GWT.create(Binder.class);
		Widget widget = uiBinder.createAndBindUi(this);

		getCategories();
		populateCategoriesList();
		addLanguages();
		setProblemInfo();
		setProblemText();

		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				submssionService.submitCode(isAnonymousSubmission, problem
						.getProblemCode(), problem.getOjType(), codeArea
						.getText(), listboxLanguages.getValue(listboxLanguages
						.getSelectedIndex()),

				new AsyncCallback<Long>() {
					@Override
					public void onSuccess(Long result) {

						eventBus.fireEvent(new ViewProblemSubmissionStatusEvent(
								problem, isAnonymousSubmission, codeArea
										.getText(), cbIsCodeVisible.getValue(),
								getCheckedCategories(), result));
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failed to submit!!");
						caught.printStackTrace();
					}
				});
			}
		});

		container.clear();
		container.add(widget);
	}

	private void generateTagCloud(ArrayList<String> categoriesList) {
		if (categoriesList.size() == 0)
			tagCloud.setVisible(false);
		HashMap<String, Integer> wordsFrequency = new HashMap<String, Integer>();
		for (int i = 0; i < categoriesList.size(); i++) {
			wordsFrequency.put(categoriesList.get(i),
					wordsFrequency.get(categoriesList.get(i)) == null ? 1
							: wordsFrequency.get(categoriesList.get(i)) + 1);
		}

		tagCloud.setColored(true);
		for (Entry<String, Integer> entry : wordsFrequency.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			WordTag wordTag = new WordTag(key);
			wordTag.setNumberOfOccurences(value);
			tagCloud.addWord(wordTag);
		}
	}

	private void populateCategoriesList() {
		// TODO: get the list from Datastore
		listboxCategories.addItem("String Manipulation");
		listboxCategories.addItem("String Parsing");
		listboxCategories.addItem("Simulation");
		listboxCategories.addItem("Encryption/Compression");
		listboxCategories.addItem("Sorting");
		listboxCategories.addItem("Brute Force");
		listboxCategories.addItem("Greedy");
		listboxCategories.addItem("Recursion");
		listboxCategories.addItem("Simple Search, Iteration");
		listboxCategories.addItem("Geometry");
		listboxCategories.addItem("Graph Theory");
		listboxCategories.addItem("Dynamic Programming");
		listboxCategories.addItem("Simple Math");
		listboxCategories.addItem("Advanced Math");
		listboxCategories.addItem("Binary Search");
	}

	private void setProblemInfo() {
		this.submssionService.getProblem(problemCode, ojType,
				new AsyncCallback<ProblemData>() {
					@Override
					public void onSuccess(ProblemData problem) {
						ProblemPresenter.this.problem = problem;
						lblPrblmTitle.setText(problem.getProblemName());
						lblPrblmTitle.setHref(problem.getUrl());
						lblPrblmTitle.setTarget("_blank");
						String addedAccountsCookie = Cookies
								.getCookie("addedAccountsCookie");
						if (!addedAccountsCookie.contains(problem.getOjType())) {
							isAnonymousSubmission = true;
							lblAnon.setVisible(true);
							lblAnon.setTargetHistoryToken("addAccount");
						} else {
							isAnonymousSubmission = false;
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Failed to load problem");
					}
				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#setLanguages(
	 * java.util.ArrayList)
	 */
	private void addLanguages() {
		this.languageService.getLanguages(ojType,
				new AsyncCallback<ArrayList<LanguageData>>() {

					@Override
					public void onSuccess(ArrayList<LanguageData> result) {
						for (int i = 0; i < result.size(); i++)
							listboxLanguages.addItem(result.get(i)
									.getLanguageName(), result.get(i)
									.getLanguageValue());
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failed to get list of languages!!");
					}
				});
	}

	private void getCategories() {
		this.sourceCodeService.getCategories(problemCode, ojType,
				new AsyncCallback<ArrayList<String>>() {

					@Override
					public void onSuccess(ArrayList<String> result) {
						generateTagCloud(result);
						for (int i = 0; i < result.size(); ++i)
							listboxCategories.addItem(result.get(i));
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

	}

	public void setProblemText() {
		this.submssionService.getProblemText(problemCode, ojType,
				new AsyncCallback<ProblemTextData>() {

					@Override
					public void onSuccess(ProblemTextData problemText) {
						if (problemText.getIsDividable().equals("false")) {
							htmlProblemText.setHTML(problemText.getFullText()
									.replaceAll("�", "'"));
							dpDivided.setVisible(false);
							dpNotDivided.setVisible(true);
						} else {
							dpDivided.setVisible(true);
							dpNotDivided.setVisible(false);
							htmlProblemStatement
									.setHTML(problemText.getProblemStatement()
											.replaceAll("�", "'"));

							htmlInputConstraints
									.setHTML(problemText.getInputConstraints()
											.replaceAll("�", "'"));

							htmlOutputConstraints.setHTML(problemText
									.getOutputConstraints()
									.replaceAll("�", "'"));

							htmlTestCases.setHTML(problemText.getIOTestCases()
									.replaceAll("�", "'"));
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("ae-probpres-getprobtext-failure");

					}
				});
	}

	private ArrayList<String> getCheckedCategories() {
		return null;
	}
}
