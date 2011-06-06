/**
 * 
 */
package com.OJToolkit.client.view;

import com.OJToolkit.client.presenter.AddAccountPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class AddAccountView extends Composite implements
        AddAccountPresenter.Display {

	Button btnAddSpojAccount;
	Button btnAddTimusAccount;
	Button btnAddUVAAccount;
	TextBox txtAccountUsername;
	TextBox txtAccountPassword;
	Label lblUsername;
	Label lblPassword;
	VerticalPanel absolutePanel;
	Button btnAddAccount;

	/**
	 * add account view page  
	 */
	public AddAccountView() {
		absolutePanel = new VerticalPanel();
		initWidget(absolutePanel);

		Label lblTitle = new Label("Add Account");
		absolutePanel.add(lblTitle);

		btnAddSpojAccount = new Button("Add SPOJ Account");
		btnAddSpojAccount.setSize("200", "200");
		absolutePanel.add(btnAddSpojAccount);

		btnAddTimusAccount = new Button("Add Timus Account");
		absolutePanel.add(btnAddTimusAccount);
		
		btnAddUVAAccount = new Button("Add UVA Account");
		absolutePanel.add(btnAddUVAAccount);

		lblUsername = new Label();
		// lblUsername.setVisible(false);
		// absolutePanel.add(lblUsername);

		lblPassword = new Label();
		// lblPassword.setVisible(false);
		// absolutePanel.add(lblPassword);

		txtAccountUsername = new TextBox();
		absolutePanel.add(lblUsername);
		txtAccountUsername.setVisible(false);
		absolutePanel.add(txtAccountUsername);

		txtAccountPassword = new TextBox();
		txtAccountPassword.setVisible(false);
		absolutePanel.add(lblPassword);
		// txtAccountPassword.setVisible(false);
		absolutePanel.add(txtAccountPassword);

		btnAddAccount = new Button("Add Account");
		btnAddAccount.setVisible(false);
		absolutePanel.add(btnAddAccount);

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
		return btnAddSpojAccount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#
	 * getAddTimusAccountButton()
	 */
	@Override
	public HasClickHandlers getAddTimusAccountButton() {
		return btnAddTimusAccount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.AddAccountPresenter.Display#getAccountUserName
	 * ()
	 */
	@Override
	public HasValue<String> getAccountUserName() {
		return txtAccountUsername;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.AddAccountPresenter.Display#getAccountPassword
	 * ()
	 */
	@Override
	public HasValue<String> getAccountPassword() {
		return txtAccountPassword;
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
		txtAccountUsername.setVisible(true);
		txtAccountPassword.setVisible(true);
		lblUsername.setText(accountType + " Username : ");
		lblPassword.setText(accountType + " Password : ");
		btnAddAccount.setVisible(true);

		// initWidget(absolutePanel);

	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#
	 * getAddAccountButton()
	 */
	@Override
	public HasClickHandlers getAddAccountButton() {
		return btnAddAccount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.AddAccountPresenter.Display#setAddedAccounts
	 * (java.lang.String)
	 */
	@Override
	public void setAddedAccounts(String addedAccounts) {
		if (addedAccounts.contains("SPOJ")) {
			btnAddSpojAccount.setVisible(false);
		}
		if (addedAccounts.contains("Timus")) {
			btnAddTimusAccount.setVisible(false);
		}
		if(addedAccounts.contains("UVA")){
			btnAddUVAAccount.setVisible(false);
		}

	}

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#getAddUVAAccountButton()
     */
    @Override
    public HasClickHandlers getAddUVAAccountButton() {
	    return btnAddUVAAccount;
    }



}
