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
	Button btnAddXAccount;
	TextBox txtAccountUsername;
	TextBox txtAccountPassword;
	Label lblUsername;
	Label lblPassword;
	VerticalPanel absolutePanel;
	Button btnAddAccount;

	public AddAccountView() {
		absolutePanel = new VerticalPanel();
		initWidget(absolutePanel);

		Label lblTitle = new Label("Add Account");
		absolutePanel.add(lblTitle);

		btnAddSpojAccount = new Button("Add SPOJJ Account");
		btnAddSpojAccount.setSize("200", "200");
		absolutePanel.add(btnAddSpojAccount);

		btnAddXAccount = new Button("Add X Account");
		absolutePanel.add(btnAddXAccount);

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
		// TODO Auto-generated method stub
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#
	 * getAddSpojAccountButton()
	 */
	@Override
	public HasClickHandlers getAddSpojAccountButton() {
		// TODO Auto-generated method stub
		return btnAddSpojAccount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.presenter.AddAccountPresenter.Display#
	 * getAddXAccountButton()
	 */
	@Override
	public HasClickHandlers getAddXAccountButton() {
		// TODO Auto-generated method stub
		return btnAddXAccount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.AddAccountPresenter.Display#getAccountUserName
	 * ()
	 */
	@Override
	public HasValue<String> getAccountUserName() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		if (addedAccounts.contains("X")) {
			btnAddXAccount.setVisible(false);
		}

	}

}
