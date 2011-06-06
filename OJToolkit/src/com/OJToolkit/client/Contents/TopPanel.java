package com.OJToolkit.client.Contents;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;

public class TopPanel extends Content {

	public TopPanel() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("TitlePanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");

		ImageResource img = MyResource.INSTANCE.imgBanner();
		Image widget = new Image(img);

		absolutePanel.add(widget);
	}
}
