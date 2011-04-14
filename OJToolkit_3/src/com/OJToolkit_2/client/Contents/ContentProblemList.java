package com.OJToolkit_2.client.Contents;

import com.OJToolkit_2.client.CoreContainer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Grid;

public class ContentProblemList extends Content {

//	private final LanguageServiceAsync languageService = GWT.create(LanguageService.class);
	
	public ContentProblemList() {
		super();

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);

		Grid grid = new Grid(5, 3);
		grid.setBorderWidth(2);
		absolutePanel.add(grid, 0, 0);
		grid.setWidth("100%");
		grid.setTitle("Problem List");
		grid.setText(0, 0, "Problem ID");
		grid.setText(0, 1, "Problem Name");
		grid.setText(0, 2, "Source Judge");

		grid.setText(1, 0, "TEST");
		Anchor problemName = new Anchor("Life, the Universe, and Everything");
		/*languageService.addLanguages(new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				Window.alert("Languages Added!");// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to add languages");
				// TODO Auto-generated method stub
				
			}
		});*/
		problemName.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CoreContainer.getInstance().setContent(new ContentProblemPage("TEST"));
			}
		});
		grid.setWidget(1, 1, problemName);

		grid.setText(1, 2, "SPOJ");
	}
}
