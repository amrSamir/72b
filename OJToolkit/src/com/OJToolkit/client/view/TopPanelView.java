package com.OJToolkit.client.view;

import com.OJToolkit.client.Contents.MyResource;
import com.OJToolkit.client.presenter.TopPanelPresenter;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelView extends Composite implements
		TopPanelPresenter.Display {

	public TopPanelView() {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("TitlePanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");

		
		SuggestBox searchBox = new SuggestBox();
		searchBox.setText("Currently not available");
		searchBox.setSize("30%", "");
		absolutePanel.add(searchBox, 25, 20);
		
		Button search = new Button("Search");
		absolutePanel.add(search, 320, 22);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
