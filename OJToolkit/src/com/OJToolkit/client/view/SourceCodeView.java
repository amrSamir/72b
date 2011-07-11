/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.OJToolkit.client.presenter.SourceCodePresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B June 11, 2011
 */
public class SourceCodeView extends Composite implements
		SourceCodePresenter.Display {

	Label lblProblemTitle;
	Label lblProblemCode;
	Label lblJudgeType;
	Label lblUsername;
	Label lblJudgeResult;
	Label lblDate;
	Label lblTime;
	Label lblMemory;
	TextBox txtSourceCode;

	public SourceCodeView() {
		
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("center");
		initWidget(verticalPanel);
		
		Label lbl1 = new Label("Problem Code: ");
		Label lbl2 = new Label("Judge Type: ");
		Label lbl3 = new Label("Solved by: ");
		Label lbl4 = new Label("Judge Result: ");
		Label lbl5 = new Label("Submission Date: ");
		Label lbl6 = new Label("time: ");
		Label lbl7 = new Label("memory: ");
		Label lbl8 = new Label("Code: ");
		
		 lblProblemTitle= new Label();
		 lblProblemCode= new Label();
		 lblJudgeType= new Label();
		 lblUsername= new Label();
		 lblJudgeResult= new Label();
		 lblDate= new Label();
		 lblTime= new Label();
		 lblMemory= new Label();
		 
		 txtSourceCode = new TextBox();
		 verticalPanel.add(lblProblemTitle);
		 verticalPanel.add(lbl1);
		 verticalPanel.add(lblProblemCode);
		 
		 verticalPanel.add(lbl2);
		 verticalPanel.add(lblJudgeType);
		 verticalPanel.add(lbl3);
		 verticalPanel.add(lblUsername);
		 verticalPanel.add(lbl4);
		 verticalPanel.add(lblJudgeResult);
		 verticalPanel.add(lbl5);
		 verticalPanel.add(lblDate);
		 
		 verticalPanel.add(lbl6);
		 verticalPanel.add(lblTime);
		 
		 verticalPanel.add(lbl7);
		 verticalPanel.add(lblMemory);
		 
		 verticalPanel.add(lbl8);
		 verticalPanel.add(txtSourceCode);
		 
		
	
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
     * @see com.OJToolkit.client.presenter.SourceCodePresenter.Display#setSourceCode(com.OJToolkit.client.ValueObjects.SourceCodeData)
     */
    @Override
    public void setSourceCode(SourceCodeData sourceCode) {
	   lblDate.setText(sourceCode.getDate());
	   lblProblemCode.setText(sourceCode.getProblemCode());
	   lblProblemTitle.setText(sourceCode.getProblemTitle());
	   lblJudgeType.setText(sourceCode.getJudgeType());
	   lblUsername.setText(sourceCode.getUsername());
	   lblJudgeResult.setText(sourceCode.getJudgeResult());
	   lblTime.setText(sourceCode.getTime());
	   lblMemory.setText(sourceCode.getMemory());
	   txtSourceCode.setText(sourceCode.getSourceCode());
    }

}
