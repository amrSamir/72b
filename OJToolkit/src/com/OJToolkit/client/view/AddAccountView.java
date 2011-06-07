/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.presenter.AddAccountPresenter;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class AddAccountView extends Composite implements
        AddAccountPresenter.Display {

	Button [] btnAddAccount;
	TextBox [] txtAccountUsername;
	TextBox [] txtAccountPassword;
	TabLayoutPanel tabPanel;
	final String [] ojs = {"SPOJ","UVA","Timus"};
	

	/**
	 * add account view page  
	 */
	public AddAccountView() {
		tabPanel = new TabLayoutPanel(2.5, Unit.EM);
		tabPanel.setSize("100%", "100%");
	    tabPanel.setAnimationDuration(500);
	    initWidget(tabPanel);

	    btnAddAccount = new Button[ojs.length];
	    txtAccountUsername = new TextBox[ojs.length];
	    txtAccountPassword = new TextBox[ojs.length];
	    
	    for(int i =0  ; i < ojs.length; ++i) {
	    	VerticalPanel verticalPanel = new VerticalPanel();
	    	
	    	Label lblUsername = new Label(ojs[i] + " Username");
	    	verticalPanel.add(lblUsername);
	    	
	    	txtAccountUsername[i] = new TextBox();
	    	verticalPanel.add(txtAccountUsername[i]);
	    	
	    	Label lblPassword = new Label(ojs[i] + " Password");
	    	verticalPanel.add(lblPassword);
	    	
	    	txtAccountPassword[i] = new TextBox();
	    	verticalPanel.add(txtAccountPassword[i]);
	    	
	    	btnAddAccount[i] = new Button("Save Account");
	    	verticalPanel.add(btnAddAccount[i]);

	    	tabPanel.add(verticalPanel,"Add Account "+ojs[i]);
	    }
	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.ProblemPresenter.Display#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#
	 * getAddSpojAccountButton()
	 */
	@Override
	public HasClickHandlers getAddSpojAccountButton() {
		return btnAddAccount[0];
	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#
	 * getAddTimusAccountButton()
	 */
	@Override
	public HasClickHandlers getAddTimusAccountButton() {
		return btnAddAccount[1];
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.AddAccountPresenter.Display#getAccountUserName
	 * ()
	 */
	@Override
	public HasValue<String> getAccountUserName() {
		return txtAccountUsername[tabPanel.getSelectedIndex()];
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.AddAccountPresenter.Display#getAccountPassword
	 * ()
	 */
	@Override
	public HasValue<String> getAccountPassword() {
		return txtAccountPassword[tabPanel.getSelectedIndex()];
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.AddAccountPresenter.Display#setAccountType
	 * (java.lang.String)
	 */
	@Override
	public void setAccountType(String accountType) {

		/*
		 * lblUsername.setVisible(true);
		 * lblPassword.setVisible(true);
		 */
//		txtAccountUsername.setVisible(true);
//		txtAccountPassword.setVisible(true);
//		lblUsername.setText(accountType + " Username : ");
//		lblPassword.setText(accountType + " Password : ");
//		btnAddAccount.setVisible(true);

		// initWidget(absolutePanel);

	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#
	 * getAddAccountButton()
	 */
	@Override
	public HasClickHandlers getAddAccountButton() {
		return btnAddAccount[tabPanel.getSelectedIndex()];
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.AddAccountPresenter.Display#setAddedAccounts
	 * (java.lang.String)
	 */
	@Override
	public void setAddedAccounts(String addedAccounts) {
//		if (addedAccounts.contains("SPOJ")) {
//			btnAddSpojAccount.setVisible(false);
//		}
//		if (addedAccounts.contains("Timus")) {
//			btnAddTimusAccount.setVisible(false);
//		}
//		if(addedAccounts.contains("UVA")){
//			btnAddUVAAccount.setVisible(false);
//		}

	}

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#getAddUVAAccountButton()
     */
    @Override
    public HasClickHandlers getAddUVAAccountButton() {
	    return btnAddAccount[2];
    }

	@Override
	public void notifySave() {
	    DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
	    simplePopup.setAnimationEnabled(true);
	    simplePopup.setWidth("150px");
	    simplePopup.setWidget(new Label("Saved"));
	    simplePopup.setPopupPosition(200, 200);
	    simplePopup.show();
	}
}
