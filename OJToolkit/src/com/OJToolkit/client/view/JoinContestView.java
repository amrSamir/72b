package com.OJToolkit.client.view;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.presenter.JoinContestPresenter;
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

public class JoinContestView extends Composite implements
		JoinContestPresenter.Display {

	TextBox txtContestAccessCode;
	ListBox listContests;
	Button btnJoinContest;
	int contestsSize;
	ArrayList<ContestData> contests;

	public JoinContestView() {
		contests = new ArrayList<ContestData>();
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("center");
		initWidget(verticalPanel);

		HorizontalPanel pnlJoinContest = new HorizontalPanel();
		Label lblContestName = new Label("choose contest");
		listContests = new ListBox();
		System.out.println("Magdi-view-joincontest-number of contest ="
				+ contests.size());

		pnlJoinContest.add(lblContestName);
		pnlJoinContest.add(listContests);

		verticalPanel.add(pnlJoinContest);

		HorizontalPanel pnlAccessCodeContest = new HorizontalPanel();
		Label lblAccessCode = new Label("Contest Access code");
		txtContestAccessCode = new TextBox();
		pnlAccessCodeContest.add(lblAccessCode);
		pnlAccessCodeContest.add(txtContestAccessCode);
		verticalPanel.add(pnlAccessCodeContest);

		btnJoinContest = new Button("join Contest");
		btnJoinContest.setSize("100px", "28px");
		verticalPanel.add(btnJoinContest);
		verticalPanel.setCellHorizontalAlignment(btnJoinContest,
				HasHorizontalAlignment.ALIGN_CENTER);

	}

	@Override
	public HasClickHandlers getSubmitButton() {
		return btnJoinContest;
	}

	@Override
	public String getContestName() {
		return listContests.getValue(listContests.getSelectedIndex());
	}

	@Override
	public HasValue<String> getContestAccessCode() {
		return txtContestAccessCode;
	}

	@Override
	public void setContests(ArrayList<ContestData> contests) {
		this.contests = contests;
		System.out.println("Magdi-presnter-join- Number of contests = "
				+ contests.size());
		for (int i = 0; i < contests.size(); i++) {
			String s = "-";
			if (contests.get(i).getContestAccessCode().equalsIgnoreCase(""))
				s = "+";
			listContests.addItem(s + contests.get(i).getContestName());
		}

	}

	@Override
	public void setContestSize(int size) {
		contestsSize = size;

	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
