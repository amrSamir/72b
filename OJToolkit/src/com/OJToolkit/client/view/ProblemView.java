/**
 * 
 */
package com.OJToolkit.client.view;

import gdurelle.tagcloud.client.tags.TagCloud;
import gdurelle.tagcloud.client.tags.WordTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.OJToolkit.client.ValueObjects.LanguageData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemTextData;
import com.OJToolkit.client.presenter.ProblemPresenter;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B Apr 26, 2011
 */
public class ProblemView extends Composite implements ProblemPresenter.Display {

	private final Button btnSubmit;
	VerticalPanel masterPanel;
	StackLayoutPanel splitPanel;
	Label lblPrblmTitle;
	Label lblNotRegistered;
	Hyperlink hprAddAccounts;
	ListBox comboBox;
	TextArea txtCode;
	Frame problemStatementFrame;
	private boolean isAnonymousSubmission = false;
	Anchor problemLink;
	VerticalPanel problemStatementPanel;
	CheckBox cbIsCodeVisible;
	ArrayList<CheckBox> categoriesList;
	TagCloud tagCloud;

	public ProblemView() {
		masterPanel = new VerticalPanel();
		masterPanel.setSize("100%", "100%");
		initWidget(masterPanel);

		lblPrblmTitle = new Label();
		lblPrblmTitle.setStylePrimaryName("ProblemTitle");
		lblPrblmTitle.setSize("100%", "100%");
		masterPanel.add(lblPrblmTitle);
		masterPanel.setCellHeight(lblPrblmTitle, "5%");

		lblNotRegistered = new Label("fds");
		lblNotRegistered.setVisible(false);
		lblNotRegistered.setSize("100%", "100%");
		lblNotRegistered.setStylePrimaryName("ProblemTitle");
		masterPanel.add(lblNotRegistered);
		// masterPanel.setCellHeight(lblNotRegistered, "5%");

		hprAddAccounts = new Hyperlink("fdsf", "addAccount");
		hprAddAccounts.setVisible(false);
		hprAddAccounts.setStylePrimaryName("ProblemTitle");
		hprAddAccounts.setSize("100%", "100%");
		// masterPanel.setCellHeight(hprAddAccounts, "5%");
		masterPanel.add(hprAddAccounts);

		problemLink = new Anchor();
		problemLink.setStyleName("ProblemTitle");
		problemLink.setSize("100%", "100%");
		masterPanel.setCellHeight(problemLink, "5%");
		masterPanel.add(problemLink);
		tagCloud = new TagCloud();
		masterPanel.add(tagCloud);
		
		splitPanel = new StackLayoutPanel(Unit.EM);
		splitPanel.setSize("100%", "100%");
		masterPanel.add(splitPanel);
		masterPanel.setCellHeight(splitPanel, "100%");

		problemStatementPanel = new VerticalPanel();
		problemStatementPanel.setSize("100%", "100%");
		ScrollPanel problemStatementScrollPanel = new ScrollPanel();
		problemStatementScrollPanel.setSize("100%", "100%");
		problemStatementScrollPanel.add(problemStatementPanel);
		splitPanel.add(problemStatementScrollPanel, "Problem Statement", 2);

		

		VerticalPanel verticalPanel2 = new VerticalPanel();
		verticalPanel2.setSize("100%", "100%");
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setSize("100%", "100%");
		scrollPanel.add(verticalPanel2);
		splitPanel.add(scrollPanel, "Submit Code", 2);

		txtCode = new TextArea();
		verticalPanel2.add(txtCode);
		txtCode.setSize("100%", "300px");

		cbIsCodeVisible = new CheckBox("Is code Visible");
		cbIsCodeVisible.setChecked(true);
		cbIsCodeVisible.setVisible(true);
		verticalPanel2.add(cbIsCodeVisible);

		categoriesList = new ArrayList<CheckBox>();
		addCategoriesToList();
		verticalPanel2.add(new Label("Category"));
		HorizontalPanel categoryPanel = new HorizontalPanel();
		for (int i = 0; i < categoriesList.size() / 2; i++) {
			categoryPanel.add(categoriesList.get(i));
		}
		verticalPanel2.add(categoryPanel);
		HorizontalPanel categoryPanel2 = new HorizontalPanel();
		for (int i = categoriesList.size() / 2; i < categoriesList.size(); i++) {
			categoryPanel2.add(categoriesList.get(i));
		}
		verticalPanel2.add(categoryPanel2);

		Label lblLanguage = new Label("Language");
		verticalPanel2.add(lblLanguage);

		comboBox = new ListBox();

		verticalPanel2.add(comboBox);

		btnSubmit = new Button("Submit");
		verticalPanel2.add(btnSubmit);
		btnSubmit.setSize("100px", "28px");

	}

	private TagCloud generateTagCloud(TagCloud tagCloud, ArrayList<String> categoriesList) {

		HashMap<String, Integer> wordsFrequency = new HashMap<String, Integer>();
		for (int i = 0; i < categoriesList.size(); i++) {
			wordsFrequency.put(categoriesList.get(i),
			        wordsFrequency.get(categoriesList.get(i))==null?1:wordsFrequency.get(categoriesList.get(i)) + 1);
		}

		tagCloud.setColored(true);
		for (Entry<String, Integer> entry : wordsFrequency.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
			WordTag wordTag = new WordTag(key);
			wordTag.setNumberOfOccurences(value);
			tagCloud.addWord(wordTag);
		    
		}

	

		return tagCloud;
	}

	void addCategoriesToList() {

		categoriesList.add(new CheckBox("String Manipulation"));
		categoriesList.add(new CheckBox("String Parsing"));
		categoriesList.add(new CheckBox("Simulation"));
		categoriesList.add(new CheckBox("Encryption/Compression"));
		categoriesList.add(new CheckBox("Sorting"));
		categoriesList.add(new CheckBox("Brute Force"));
		categoriesList.add(new CheckBox("Greedy"));
		categoriesList.add(new CheckBox("Recursion"));
		categoriesList.add(new CheckBox("Simple Search, Iteration"));
		categoriesList.add(new CheckBox("Geometry"));
		categoriesList.add(new CheckBox("Graph Theory"));
		categoriesList.add(new CheckBox("Dynamic Programming"));
		categoriesList.add(new CheckBox("Simple Math"));
		categoriesList.add(new CheckBox("Advanced Math"));
		categoriesList.add(new CheckBox("Binary Search"));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#getSubmitButton()
	 */
	@Override
	public HasClickHandlers getSubmitButton() {
		return btnSubmit;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#setProblem(com
	 * .OJToolkit.client.ValueObjects.ProblemData)
	 */
	@Override
	public void setProblem(ProblemData problem) {
		// problemStatementFrame.setUrl(problem.getUrl());
		lblPrblmTitle.setText(problem.getProblemName());
		problemLink.setText("Open the problem in " + problem.getOjType());
		problemLink.setHref(problem.getUrl());
		problemLink.setTarget("_blank");
		String addedAccountsCookie = Cookies.getCookie("addedAccountsCookie");
		if (!addedAccountsCookie.contains(problem.getOjType())) {
			isAnonymousSubmission = true;
			lblNotRegistered
			        .setText("You are not registered at "
			                + problem.getOjType()
			                + " or you haven't provided us with your account details.\n"
			                + "You can submit code anonymously or ");
			lblNotRegistered.setVisible(true);
			hprAddAccounts.setText("Add " + problem.getOjType() + " Details");
			hprAddAccounts.setTargetHistoryToken("addAccount");
			hprAddAccounts.setVisible(true);
			masterPanel.setCellHeight(lblNotRegistered, "5%");
			masterPanel.setCellHeight(hprAddAccounts, "5%");
		}

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#setLanguages(
	 * java.util.ArrayList)
	 */
	@Override
	public void setLanguages(ArrayList<LanguageData> languages) {
		for (int i = 0; i < languages.size(); i++)
			comboBox.addItem(languages.get(i).getLanguageName(),
			        languages.get(i).getLanguageValue());

	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#getCode()
	 */
	@Override
	public String getCode() {
		return txtCode.getText();
	}

	@Override
	public boolean isVisible() {
		return cbIsCodeVisible.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#getProblemCode()
	 */

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#
	 * getSelectedLanguageValue()
	 */
	@Override
	public String getSelectedLanguageValue() {
		return comboBox.getValue(comboBox.getSelectedIndex());
	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#isAnonymousSubmission
	 * ()
	 */
	@Override
	public boolean isAnonymousSubmission() {
		return isAnonymousSubmission;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#setProblemText
	 * (com.OJToolkit.client.ValueObjects.ProblemTextData)
	 */
	@Override
	public void setProblemText(ProblemTextData problemText) {
		HTML HTMLFullText;
		HTML HTMLProblemStatement;
		HTML HTMLInputConstraints;
		HTML HTMLOutputConstraints;
		HTML HTMLIOTestCases;
		if (problemText.getIsDividable().equals("false")) {
			HTMLFullText = new HTML(problemText.getFullText().replaceAll("�", "'"));
			problemStatementPanel.add(HTMLFullText);
		} else {
			HTMLProblemStatement = new HTML(problemText.getProblemStatement().replaceAll("�", "'"));
			problemStatementPanel.add(new Label(
			        "-------Problem Statement-------"));
			problemStatementPanel.add(HTMLProblemStatement);

			HTMLInputConstraints = new HTML(problemText.getInputConstraints().replaceAll("�", "'"));
			problemStatementPanel.add(new Label(
			        "-------Input Constraints-------"));
			problemStatementPanel.add(HTMLInputConstraints);

			HTMLOutputConstraints = new HTML(problemText.getOutputConstraints().replaceAll("�", "'"));
			problemStatementPanel.add(new Label(
			        "-------Output Constraints-------"));
			problemStatementPanel.add(HTMLOutputConstraints);

			HTMLIOTestCases = new HTML(problemText.getIOTestCases().replaceAll("�", "'"));
			problemStatementPanel.add(new Label("-------IO Test Cases-------"));
			problemStatementPanel.add(HTMLIOTestCases);

		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#getCheckedCategories
	 * ()
	 */
	@Override
	public ArrayList<String> getCheckedCategories() {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < categoriesList.size(); i++) {
			if (categoriesList.get(i).isChecked()) {
				ret.add(categoriesList.get(i).getText());
				System.out.println(categoriesList.get(i).getText());
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#setCategories
	 * (java.util.ArrayList)
	 */
	@Override
	public void setCategories(ArrayList<String> categoriesList) {
		if(categoriesList.size()==0)
			tagCloud.setVisible(false);
		else
			generateTagCloud(tagCloud, categoriesList);

	}

}
