package com.OJToolkit.client.Contents;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;

public class ContentSubmissionList extends Content {

	public ContentSubmissionList() {
		super();

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);

		FlexTable flexTable = new FlexTable();
		flexTable.setBorderWidth(2);
		absolutePanel.add(flexTable, 0, 0);
		flexTable.setWidth("100%");
		flexTable.setTitle("Problem List");
		flexTable.setText(0, 0, "Problem ID");
		flexTable.setText(0, 1, "User Name");
		flexTable.setText(0, 2, "Status");

		flexTable.setText(1, 0, "TEST");

		flexTable.setText(1, 1, "amrSamir");

		flexTable.setText(1, 2, "WA");
	}
}
