/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.Contents.MyResource;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author 72B May 10, 2011
 */
public class ProblemSubmissionStatusView extends Composite implements
        ProblemSubmissionStatusPresenter.Display {

	VerticalPanel verticalPanel;
	TextBox txtDate;
	TextBox txtProblemLink;
	TextBox txtJudgeResult;
	TextBox txtTime;
	TextBox txtMemory;
	Label lblTime;
	Label lblMemory;
	Label lblJudgeResult;
	HorizontalPanel compilingPanel;

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

		lblJudgeResult = new Label("Judge Result");
		verticalPanel.add(lblJudgeResult);

		txtJudgeResult = new TextBox();
		verticalPanel.add(txtJudgeResult);
		compilingPanel = new HorizontalPanel();
		Label lblCompiling = new Label("Compiling");
		compilingPanel.add(lblCompiling);
		ImageResource imgLoadingResource = MyResource.INSTANCE.imgLoading();
		Image imgLoadingWidget = new Image(imgLoadingResource.getURL());
		compilingPanel.add(imgLoadingWidget);
		compilingPanel.setVisible(false);
		verticalPanel.add(compilingPanel);

		lblTime = new Label("Time");
		verticalPanel.add(lblTime);

		txtTime = new TextBox();
		verticalPanel.add(txtTime);

		lblMemory = new Label("Memory");
		verticalPanel.add(lblMemory);

		txtMemory = new TextBox();
		verticalPanel.add(txtMemory);
		compilingPanel.setVisible(true);
		lblMemory.setVisible(false);
		lblTime.setVisible(false);
		txtMemory.setVisible(false);
		txtTime.setVisible(false);
		txtJudgeResult.setVisible(false);
		lblJudgeResult.setVisible(false);


	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter.Display
	 * #setSubmissionResult(com.OJToolkit.client.ValueObjects.ProblemStatusData)
	 */
	@Override
	public void setSubmissionResult(ProblemStatusData result) {
		System.out.println("JUDGE RESULT");
		System.out.println(result.getJudgeResult());
		if (result.getJudgeResult() == null
		        || result.getJudgeResult().equals("")) {
			compilingPanel.setVisible(true);
			lblMemory.setVisible(false);
			lblTime.setVisible(false);
			txtMemory.setVisible(false);
			txtTime.setVisible(false);
			txtJudgeResult.setVisible(false);
			lblJudgeResult.setVisible(false);
		} else if (result.getJudgeResult().equals("compilation error")
		        || result.getJudgeResult().equals("Wrong answer")) {
			txtJudgeResult.setVisible(true);
			lblJudgeResult.setVisible(true);
			lblMemory.setVisible(false);
			lblTime.setVisible(false);
			txtMemory.setVisible(false);
			txtTime.setVisible(false);
			compilingPanel.setVisible(false);
		} else {
			compilingPanel.setVisible(false);
			lblMemory.setVisible(true);
			lblTime.setVisible(true);
			txtMemory.setVisible(true);
			txtTime.setVisible(true);
			txtJudgeResult.setVisible(true);
			lblJudgeResult.setVisible(true);

		}
		txtDate.setText(result.getDate().toString());
		txtJudgeResult.setText(result.getJudgeResult());
		txtMemory.setText(result.getMem());
		txtProblemLink.setText(result.getProblemLink());
		txtTime.setText(result.getTime());
	}

}
