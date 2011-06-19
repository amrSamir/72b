package com.OJToolkit.client.view;

import com.OJToolkit.client.presenter.TopPanelPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelView extends Composite implements
		TopPanelPresenter.Display {

	Anchor hprlnkSignout;
	public TopPanelView() {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("TitlePanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");

		
		SuggestBox searchBox = new SuggestBox();
		searchBox.setText("Currently not available");
		searchBox.setSize("279px", "17px");
		absolutePanel.add(searchBox, 25, 20);
		
		Button search = new Button("Search");
		absolutePanel.add(search, 320, 22);
		
		Anchor hprlnkFeedback = new Anchor("Feedback", false, "http://goo.gl/ZsQxt");
		absolutePanel.add(hprlnkFeedback, 418, 10);
		hprlnkFeedback.setSize("93px", "27px");
		
		Anchor hprlnkReportABug = new Anchor("Report a Bug", false, "http://goo.gl/aV0kZ");
		absolutePanel.add(hprlnkReportABug, 418, 25);
		hprlnkReportABug.setSize("93px", "27px");
		
		 hprlnkSignout = new Anchor("Sign out", false);
		absolutePanel.add(hprlnkSignout, 418, 40);
		hprlnkSignout.setSize("93px", "27px");
		hprlnkSignout.setVisible(false);
		
		
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.TopPanelPresenter.Display#setLogoutURL(java.lang.String)
     */
    @Override
    public void setLogoutURL(String logoutURL) {
    	hprlnkSignout.setVisible(true);
    	hprlnkSignout.setHref(logoutURL);
    }

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.TopPanelPresenter.Display#getLogoutButton()
     */
    @Override
    public HasClickHandlers getLogoutButton() {
	    return hprlnkSignout;
    }
}
