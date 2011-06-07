/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.presenter.InvitationPresenter;
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
 * @author 72B Apr 26, 2011
 */
public class InvitationView extends Composite implements
		InvitationPresenter.Display {

	TextBox invitationStr;
	Button btnSubmit;

	public InvitationView() {
		
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("center");
		initWidget(verticalPanel);
		
		Label msg = new Label("You should be invited to use the pre-alpha release of the site");
		verticalPanel.add(msg);

		Label lblRegistraionForm = new Label("Invitation Form");
		verticalPanel.add(lblRegistraionForm);
		verticalPanel.setCellHorizontalAlignment(lblRegistraionForm, HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel userNamePabel = new HorizontalPanel() ;
		Label lblUsername = new Label("Invitation String: ");
		userNamePabel.add(lblUsername);
		invitationStr = new TextBox();
		userNamePabel.add(invitationStr);
		invitationStr.setSize("141px", "16px");

		verticalPanel.add(userNamePabel);
		verticalPanel.setCellHorizontalAlignment(userNamePabel, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		/*
		 * Label lblSpojUsername = new Label("SPOJ Username");
		 * absolutePanel.add(lblSpojUsername); txtSPOJUsername = new TextBox();
		 * absolutePanel.add(txtSPOJUsername); txtSPOJUsername.setSize("141px",
		 * "16px"); Label lblSpojPassword = new Label("SPOJ Password");
		 * absolutePanel.add(lblSpojPassword); pwdSPOJ = new PasswordTextBox();
		 * absolutePanel.add(pwdSPOJ); pwdSPOJ.setSize("141px", "16px");
		 */

		btnSubmit = new Button("Submit");
		verticalPanel.add(btnSubmit);
		btnSubmit.setSize("100px", "28px");
		verticalPanel.setCellHorizontalAlignment(btnSubmit, HasHorizontalAlignment.ALIGN_CENTER);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.RegistrationPresenter.Display#getSubmitButton
	 * ()
	 */
	@Override
	public HasClickHandlers getSubmitButton() {
		return btnSubmit;
	}



	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.InvitationPresenter.Display#getInvitationString()
     */
    @Override
    public HasValue<String> getInvitationString() {
	    // TODO Auto-generated method stub
	    return invitationStr;
    }

}
