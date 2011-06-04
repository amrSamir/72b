/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author 72B
 *         May 10, 2011
 */
public class ProblemSubmissionStatusView extends Composite implements
        ProblemSubmissionStatusPresenter.Display {

	private Button btnRefresh;
	VerticalPanel verticalPanel;
	TextBox txtDate;
	TextBox txtProblemLink;
	TextBox txtJudgeResult;
	TextBox txtTime;
	TextBox txtMemory;

	public ProblemSubmissionStatusView() {
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);

		Label lblNewLabel = new Label("Date");
		verticalPanel.add(lblNewLabel);

		txtDate = new TextBox();
		verticalPanel.add(txtDate);

		Label lblProblemLink = new Label("Problem Link");
		verticalPanel.add(lblProblemLink);

		txtProblemLink = new TextBox();
		verticalPanel.add(txtProblemLink);

		Label lblJudgeResult = new Label("Judge Result");
		verticalPanel.add(lblJudgeResult);

		txtJudgeResult = new TextBox();
		verticalPanel.add(txtJudgeResult);

		Label lblTime = new Label("Time");
		verticalPanel.add(lblTime);

		txtTime = new TextBox();
		verticalPanel.add(txtTime);

		Label lblMemory = new Label("Memory");
		verticalPanel.add(lblMemory);

		txtMemory = new TextBox();
		verticalPanel.add(txtMemory);

		btnRefresh = new Button("New button");
		btnRefresh.setText("Refresh");
		verticalPanel.add(btnRefresh);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter.Display
	 * #getRefreshButton()
	 */
	@Override
	public HasClickHandlers getRefreshButton() {
		// TODO Auto-generated method stub
		return btnRefresh;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter.Display
	 * #setSubmissionResult(com.OJToolkit.client.ValueObjects.ProblemStatusData)
	 */
	@Override
	public void setSubmissionResult(ProblemStatusData result) {
		txtDate.setText(result.getDate());
		txtJudgeResult.setText(result.getJudgeResult());
		txtMemory.setText(result.getMem());
		txtProblemLink.setText(result.getProblemLink());
		txtTime.setText(result.getTime());
		// TODO Auto-generated method stub

	}

}
