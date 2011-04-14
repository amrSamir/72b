package com.OJToolkit_2.client.Contents;

import java.util.ArrayList;
import java.util.HashMap;

import com.OJToolkit_2.client.CoreContainer;
import com.OJToolkit_2.client.Services.SubmissionService;
import com.OJToolkit_2.client.Services.SubmissionServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.OJToolkit_2.client.Services.LanguageService;
import com.OJToolkit_2.client.Services.LanguageServiceAsync;
import com.OJToolkit_2.client.ValueObjects.LanguageData;


public class ContentProblemPage extends Content{
	
	private final SubmissionServiceAsync submissionService = GWT.create(SubmissionService.class);
	private final LanguageServiceAsync languageService = GWT.create(LanguageService.class);
	public ContentProblemPage(final String prblmID) {
		ScrollPanel scrollPanel = new ScrollPanel();
		initWidget(scrollPanel);
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSize("100%", "100%");
		scrollPanel.add(verticalPanel);
		
		Label lblNewLabel = new Label("Problem Title: ");
		verticalPanel.add(lblNewLabel);
		
		Label lblPrblmTitle = new Label(prblmID);
		verticalPanel.add(lblPrblmTitle);
		
		Frame frame = new Frame("http://www.spoj.pl/problems/"+prblmID +"/");
		verticalPanel.add(frame);
		frame.setSize("90%", "300px");
		
		
		Label lblNewLabel_1 = new Label("Code");
		verticalPanel.add(lblNewLabel_1);
		
		final TextArea textArea = new TextArea();
		verticalPanel.add(textArea);
		textArea.setSize("90%", "300px");
		
		Label lblLanguage = new Label("Language");
		verticalPanel.add(lblLanguage);
		
		final ListBox comboBox = new ListBox();

		languageService.getLanguages(new AsyncCallback<ArrayList<LanguageData>>() {
			
			@Override
			public void onSuccess(ArrayList<LanguageData> result) {
				for(int i=0;i<result.size();i++)
					comboBox.addItem(result.get(i).getLanguageName(), result.get(i).getLanguageValue());
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to get list of languages");
				// TODO Auto-generated method stub
				
			}
		});
		
		verticalPanel.add(comboBox);
		
		Button btnSubmit = new Button("Submit");
		verticalPanel.add(btnSubmit);
		btnSubmit.setSize("100px", "28px");
		
		btnSubmit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//Window.alert(comboBox.getValue(comboBox.getSelectedIndex()));
				submissionService.submitCode(prblmID, textArea.getText(), comboBox.getValue(comboBox.getSelectedIndex()), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						Window.alert("Submitted successfully");
						CoreContainer.getInstance().setContent(new ContentProblemStatus());
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				// TODO Auto-generated method stub
				
			}
		});
	
		// TODO Auto-generated constructor stub
	}
}
