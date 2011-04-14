package com.OJToolkit_2.client.Contents;

import com.OJToolkit_2.client.Services.SubmissionService;
import com.OJToolkit_2.client.Services.SubmissionServiceAsync;
import com.OJToolkit_2.client.ValueObjects.ProblemStatusData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;

public class ContentProblemStatus extends Content{

	private final SubmissionServiceAsync submissionService = GWT.create(SubmissionService.class);

	public ContentProblemStatus() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		Label lblNewLabel = new Label("Date");
		verticalPanel.add(lblNewLabel);
		
		final TextBox txtDate = new TextBox();
		verticalPanel.add(txtDate);
		
		Label lblProblemLink = new Label("Problem Link");
		verticalPanel.add(lblProblemLink);
		
		final TextBox txtProblemLink = new TextBox();
		verticalPanel.add(txtProblemLink);
		
		Label lblJudgeResult = new Label("Judge Result");
		verticalPanel.add(lblJudgeResult);
		
		final TextBox txtJudgeResult = new TextBox();
		verticalPanel.add(txtJudgeResult);
		
		Label lblTime = new Label("Time");
		verticalPanel.add(lblTime);
		
		final TextBox txtTime = new TextBox();
		verticalPanel.add(txtTime);
		
		Label lblMemory = new Label("Memory");
		verticalPanel.add(lblMemory);
		
		final TextBox txtMemory = new TextBox();
		verticalPanel.add(txtMemory);
		
		Button btnRefresh = new Button("New button");
		btnRefresh.setText("Refresh");
		
		btnRefresh.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				submissionService.getLastProblemStatus(new AsyncCallback<ProblemStatusData>() {
					
					@Override
					public void onSuccess(ProblemStatusData result) {
						txtDate.setText(result.getDate());
						txtJudgeResult.setText(result.getJudgeResult());
						txtMemory.setText(result.getMem());
						txtProblemLink.setText(result.getProblemLink());
						txtTime.setText(result.getTime());
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("can't retriee problem status");
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		});
		verticalPanel.add(btnRefresh);
		submissionService.getLastProblemStatus(new AsyncCallback<ProblemStatusData>() {
			
			@Override
			public void onSuccess(ProblemStatusData result) {
				txtDate.setText(result.getDate());
				txtJudgeResult.setText(result.getJudgeResult());
				txtMemory.setText(result.getMem());
				txtProblemLink.setText(result.getProblemLink());
				txtTime.setText(result.getTime());
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("can't retrieve problem status");
				// TODO Auto-generated method stub
				
			}
		});
		// TODO Auto-generated constructor stub
	}
	
	
}
