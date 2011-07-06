package com.OJToolkit.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.presenter.ContestAdminPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;
import com.google.gwt.user.datepicker.client.DatePicker;

public class ContestAdminView extends Composite implements
		ContestAdminPresenter.Display {

	TextBox txtContestName;
	TextBox txtContestAccessCode;
	Button btnAddContest ;
	DateBox startTime ;
	DateBox endTime ;
	ListBox listContests ;
	ArrayList<ContestData> contests ;
	
	public ContestAdminView() {
		listContests = new ListBox() ;
		startTime = new DateBox(); 
		//startTime.setFormat((Format) TimeUtility.dateFormat) ;
		endTime = new DateBox();
		//endTime.setFormat((Format) TimeUtility.dateFormat) ;
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("center");
		initWidget(verticalPanel);

		HorizontalPanel pnlJoinContest = new HorizontalPanel();
		Label lblChosseContestName = new Label("choose contest");
		listContests = new ListBox() ;
		
		pnlJoinContest.add(lblChosseContestName);
		pnlJoinContest.add(listContests) ;
		verticalPanel.add(pnlJoinContest);
		
		HorizontalPanel HPanContestName = new HorizontalPanel();
		Label lblContestName = new Label("contest name");
		HPanContestName.add(lblContestName);
		txtContestName = new TextBox();
		HPanContestName.add(txtContestName);

		verticalPanel.add(HPanContestName);

		HorizontalPanel HPanContestAccessCode = new HorizontalPanel();
		Label lblContestAccessCode = new Label("contest Access Code");
		HPanContestAccessCode.add(lblContestAccessCode);
		txtContestAccessCode = new TextBox();
		HPanContestAccessCode.add(txtContestAccessCode);
		verticalPanel.setCellHorizontalAlignment(txtContestAccessCode,
				HasHorizontalAlignment.ALIGN_CENTER);
		HorizontalPanel HPContestDate = new HorizontalPanel();
		HPContestDate.add(startTime) ;
		HPContestDate.add(endTime) ;
		verticalPanel.add(HPContestDate) ;
		
		verticalPanel.add(HPanContestAccessCode);

		btnAddContest = new Button("add contest");
		verticalPanel.add(btnAddContest);
		btnAddContest.setSize("100px", "28px");
		verticalPanel.setCellHorizontalAlignment(btnAddContest,
				HasHorizontalAlignment.ALIGN_CENTER);
	}

	@Override
	public HasClickHandlers getSubmitButton() {
		return btnAddContest;
	}

	@Override
	public HasValue<String> getContestName() {
		return txtContestName;
	}

	@Override
	public HasValue<String> getContestAccessCode() {
		return txtContestAccessCode;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public Date getStartDate() {
		return startTime.getValue();
	}

	@Override
	public Date getEndDate() {
		return endTime.getValue();
	}
	@Override
	public void setContests(ArrayList<ContestData> contests) {
		this.contests = contests ;
		System.out.println("Magdi-presnter-join- Number of contests = " + contests.size());
		listContests.addItem("New Contest");
		for(int i = 0 ; i < contests.size() ; i++){
			listContests.addItem(contests.get(i).getContestName());
		}
		
	}

	@Override
	public String getContestNameDropList() {
		System.out.println("Magdi-contestAdminView-check chosen contest id "+listContests.getSelectedIndex());
		return listContests.getItemText(listContests.getSelectedIndex());
	}
	
}
