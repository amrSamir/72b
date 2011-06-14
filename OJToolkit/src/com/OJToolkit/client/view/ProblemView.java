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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
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
	ListBox comboBox;
	TextArea txtCode;
	Frame problemStatementFrame;

	public ProblemView() {
		masterPanel = new VerticalPanel();
		masterPanel.setSize("100%", "100%");
		initWidget(masterPanel);
		
		lblPrblmTitle = new Label();
		lblPrblmTitle.setStylePrimaryName("ProblemTitle");
		lblPrblmTitle.setSize("100%", "100%");
		masterPanel.add(lblPrblmTitle);
		masterPanel.setCellHeight(lblPrblmTitle, "50px");
		splitPanel = new StackLayoutPanel(Unit.EM);
		splitPanel.setSize("100%", "100%");
		masterPanel.add(splitPanel);
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
		String addedAccountsCookie = Cookies.getCookie("addedAccountsCookie");
		if (!addedAccountsCookie.contains(problem.getOjType())) {
			txtCode.setVisible(false);
			btnSubmit.setVisible(false);
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

}
