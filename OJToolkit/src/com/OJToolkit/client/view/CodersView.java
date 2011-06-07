/**
 * 
 */
package com.OJToolkit.client.view;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.presenter.CodersPresenter;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

/**
 * @author 72B Apr 26, 2011
 */
public class CodersView extends Composite implements CodersPresenter.Display {

	AbsolutePanel absolutePanel;

	int numberOfCoders;
	int pageStart;
	ArrayList<CoderData> codersList;
	CellTable<CoderData> codersTable;
	final ListDataProvider<CoderData> coderDataProvider;

	/**
	 * view coder page 
	 */
	public CodersView() {
		System.out.println("in coder view");
		ScrollPanel ScrollCoders = new ScrollPanel() ;
		ScrollCoders.setSize("100%", "100%") ;
		initWidget(ScrollCoders);
		
		VerticalPanel vpanelCoder = new VerticalPanel() ;
		ScrollCoders.add(vpanelCoder);
		
		coderDataProvider = new ListDataProvider<CoderData>();
		codersTable = new CellTable<CoderData>() ;
		TextColumn<CoderData> coderUserNameColumn = new TextColumn<CoderData>() {
			@Override
			public String getValue(CoderData coder) {
				return coder.getUsername() ;
			}
		};
		TextColumn<CoderData> coderEmailColumn = new TextColumn<CoderData>() {
			@Override
			public String getValue(CoderData coder) {
				return coder.getEmail() ;
			}
		};
		TextColumn<CoderData> coderSpojNameColumn = new TextColumn<CoderData>() {
			@Override
			public String getValue(CoderData coder) {
				return coder.getSPOJUsername() ;
			}
		};

		codersTable.addColumn(coderUserNameColumn,"coder name") ;
		codersTable.addColumn(coderEmailColumn,"Email") ;
		codersTable.addColumn(coderSpojNameColumn,"spoj user name") ;
		codersTable.setRowCount(numberOfCoders,true) ;
		
		SimplePager coderTablePager = new SimplePager();
		coderTablePager.setDisplay(codersTable);
		coderTablePager.setPageSize(50);
		
		coderDataProvider.addDataDisplay(codersTable);
		vpanelCoder.add(codersTable);
		vpanelCoder.add(coderTablePager) ;
		// absolutePanel = new AbsolutePanel();
		// initWidget(absolutePanel);
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
	 * com.OJToolkit.client.presenter.CodersPresenter.Display#setCodersList(
	 * java.util.ArrayList)
	 */
	@Override
	public void setCodersList(ArrayList<CoderData> coders) {
//		if (coders.size() == 0)
//			Window.alert("erorr!!");
//		else
//			Window.alert("coders size = " + coders.size());
//		for (CoderData coder : coders) {
//			viewCoder(coder);
//		}
		this.codersList = coders ;
		coderDataProvider.setList(coders);
	}

//	public void viewCoder(CoderData coder) {
//
//		Label lblUserData = new Label("User Data");
//		absolutePanel.add(lblUserData);
//
//		Label lblNewLabel = new Label("UserID");
//		absolutePanel.add(lblNewLabel);
//
//		TextBox txtUserID = new TextBox();
//		txtUserID.setText(coder.getUserID().toString());
//		absolutePanel.add(txtUserID);
//
//		Label lblUsername = new Label("Username");
//		absolutePanel.add(lblUsername);
//
//		TextBox txtUserName = new TextBox();
//		txtUserName.setText(coder.getUsername());
//		absolutePanel.add(txtUserName);
//
//		Label lblEmail = new Label("Email");
//		absolutePanel.add(lblEmail);
//
//		TextBox txtEmail = new TextBox();
//		txtEmail.setText(coder.getEmail());
//		absolutePanel.add(txtEmail);
//
//		Label lblSpojusername = new Label("SPOJUsername");
//		absolutePanel.add(lblSpojusername);
//
//		TextBox txtSPOJUsername = new TextBox();
//		txtSPOJUsername.setText(coder.getSPOJUsername());
//		absolutePanel.add(txtSPOJUsername);
//
//		Label lblSpojpassword = new Label("SPOJPassword");
//		absolutePanel.add(lblSpojpassword);
//
//		TextBox txtSpojPassword = new TextBox();
//		txtSpojPassword.setText(coder.getSPOJPassword());
//		absolutePanel.add(txtSpojPassword);
//	}

	@Override
	public AbstractHasData<CoderData> getTable() {
		return codersTable ;
	}

	@Override
	public void setNumberOfCoders(int numOfCoders) {
		this.numberOfCoders = numOfCoders ;
	}

	@Override
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart ;
	}

}
