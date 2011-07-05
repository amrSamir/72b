/**
 * 
 */
package com.OJToolkit.client.view;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.LanguageData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.presenter.ProblemPresenter;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
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
		//masterPanel.setCellHeight(lblNotRegistered, "5%");
		
		
		hprAddAccounts = new Hyperlink("fdsf","addAccount");
		hprAddAccounts.setVisible(false);
		hprAddAccounts.setStylePrimaryName("ProblemTitle");
		hprAddAccounts.setSize("100%", "100%");
		//masterPanel.setCellHeight(hprAddAccounts, "5%");
		masterPanel.add(hprAddAccounts);
		
		
		problemLink = new Anchor();
		problemLink.setStyleName("ProblemTitle");
		problemLink.setSize("100%", "100%");
		masterPanel.setCellHeight(problemLink, "5%");
		masterPanel.add(problemLink);
		
	
		splitPanel = new StackLayoutPanel(Unit.EM);
		splitPanel.setSize("100%", "100%");
		masterPanel.add(splitPanel);
		masterPanel.setCellHeight(splitPanel, "100%");
		
		AbsolutePanel problemStatementPanel = new AbsolutePanel();
		problemStatementPanel.setSize("100%", "100%");
		problemStatementFrame = new Frame();
		problemStatementFrame.setSize("100%", "100%");
		problemStatementPanel.add(problemStatementFrame, 0, 0);
		splitPanel.add(problemStatementPanel, "Problem Statement", 2);

		VerticalPanel verticalPanel2 = new VerticalPanel();
		verticalPanel2.setSize("100%", "100%");
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setSize("100%", "100%");
		scrollPanel.add(verticalPanel2);

		splitPanel.add(scrollPanel, "Submit Code", 2);

		txtCode = new TextArea();
		verticalPanel2.add(txtCode);
		txtCode.setSize("100%", "300px");

		Label lblLanguage = new Label("Language");
		verticalPanel2.add(lblLanguage);

		comboBox = new ListBox();

		verticalPanel2.add(comboBox);

		btnSubmit = new Button("Submit");
		verticalPanel2.add(btnSubmit);
		btnSubmit.setSize("100px", "28px");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#getSubmitButton()
	 */
	@Override
	public HasClickHandlers getSubmitButton() {
		return btnSubmit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#setProblem(com
	 * .OJToolkit.client.ValueObjects.ProblemData)
	 */
	@Override
	public void setProblem(ProblemData problem) {
		problemStatementFrame.setUrl(problem.getUrl());
		lblPrblmTitle.setText(problem.getProblemName());
		problemLink.setText("Open the problem in " + problem.getOjType());
		problemLink.setHref(problem.getUrl());
		problemLink.setTarget("_blank");
		String addedAccountsCookie = Cookies.getCookie("addedAccountsCookie");
		if (!addedAccountsCookie.contains(problem.getOjType())) {
			isAnonymousSubmission = true;
			lblNotRegistered.setText("You are not registered at " + problem.getOjType() + 
					" or you haven't provided us with your account details.\n" + 
					"You can submit code anonymously or ");
			lblNotRegistered.setVisible(true);
			hprAddAccounts.setText("Add "+problem.getOjType() +" Details");
			hprAddAccounts.setTargetHistoryToken("addAccount");
			hprAddAccounts.setVisible(true);
			masterPanel.setCellHeight(lblNotRegistered, "5%");
			masterPanel.setCellHeight(hprAddAccounts, "5%");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
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
	 * 
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#getCode()
	 */
	@Override
	public String getCode() {
		return txtCode.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.ProblemPresenter.Display#getProblemCode()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#
	 * getSelectedLanguageValue()
	 */
	@Override
	public String getSelectedLanguageValue() {
		return comboBox.getValue(comboBox.getSelectedIndex());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return this;
	}

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#isAnonymousSubmission()
     */
    @Override
    public boolean isAnonymousSubmission() {
	    return isAnonymousSubmission;
    }

}
