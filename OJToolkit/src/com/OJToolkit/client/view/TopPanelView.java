package com.OJToolkit.client.view;

import com.OJToolkit.client.Contents.MyResource;
import com.OJToolkit.client.presenter.TopPanelPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelView extends Composite implements
		TopPanelPresenter.Display {

	Hyperlink hprlnkUserProfile;
	Anchor hprlnkSignout;
	
	public TopPanelView() {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("TitlePanel");
		initWidget(horizontalPanel);
		horizontalPanel.setSize("100%", "100%");
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Label logo = new Label("{}");
		logo.setStylePrimaryName("logo");
//		ImageResource img = MyResource.INSTANCE.imgLogo();
//		Image widget = new Image(img.getURL());
		logo.setSize("", "70px");
		horizontalPanel.add(logo);//, 30, 10);
		horizontalPanel.setCellHorizontalAlignment(logo, HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel.setCellWidth(logo, "70px");
		
		HTML title = new HTML(new SafeHtmlBuilder().appendEscapedLines("code\nmashup").toSafeHtml());
		title.setStylePrimaryName("title");
		horizontalPanel.add(title);//, 100, 10);
		horizontalPanel.setCellHorizontalAlignment(title, HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel.setCellWidth(title, "130px");
		
		/*
		Anchor hprlnkFeedback = new Anchor("Feedback", false, "http://goo.gl/ZsQxt");
		absolutePanel.add(hprlnkFeedback, 718, 10);
		hprlnkFeedback.setSize("93px", "27px");
		
		Anchor hprlnkReportABug = new Anchor("Report a Bug", false, "http://goo.gl/aV0kZ");
		absolutePanel.add(hprlnkReportABug, 718, 25);
		hprlnkReportABug.setSize("93px", "27px");
		*/
		VerticalPanel verticalPanel = new VerticalPanel();
		
		hprlnkUserProfile = new Hyperlink();
		hprlnkUserProfile.setStyleName("topPanel-Label");
		hprlnkUserProfile.setVisible(true);
		verticalPanel.add(hprlnkUserProfile);
		
		hprlnkSignout = new Anchor("Sign out", false);
		hprlnkSignout.setStyleName("topPanel-Label");
		hprlnkSignout.setVisible(false);
		verticalPanel.add(hprlnkSignout);
		
		horizontalPanel.add(verticalPanel);
		horizontalPanel.setCellHorizontalAlignment(verticalPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		
		
		
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
    	String username = Cookies.getCookie("isRegisteredCookie");
    	hprlnkSignout.setVisible(true);
    	hprlnkSignout.setHref(logoutURL);
    	hprlnkUserProfile.setVisible(true);
    	hprlnkUserProfile.setText(username);
    	hprlnkUserProfile.setTargetHistoryToken("profile="+username);
    }

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.TopPanelPresenter.Display#getLogoutButton()
     */
    @Override
    public HasClickHandlers getLogoutButton() {
	    return hprlnkSignout;
    }
}
