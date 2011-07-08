package com.OJToolkit.client.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.OJToolkit.client.event.AddAccountEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.ContestAdminEvent;
import com.OJToolkit.client.event.ContestProblemEvent;
import com.OJToolkit.client.event.JoinContestEvent;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.OJToolkit.client.event.ViewContestEvent;
import com.OJToolkit.client.event.ViewSubmissionsEvent;
import com.OJToolkit.client.presenter.LeftPanelPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LeftPanelView extends Composite implements
		LeftPanelPresenter.Display {

	public enum Labels {
		ViewCoders, AddAccounts, ViewProblems, ContestAdmin, JoinContest, ViewContest, AddProblemToContest, Status
	};

	HashMap<Labels, Label> allLabels;

	Label lblStatus;
	Label lblViewCoders;
	Label lblAddAccount;
	Label lblViewProblem;
	Label lblContestAdmin;
	Label lblJoinContest;
	Label lblViewContest;
	Label lblAddProblemsToContest;

	// Hack to be fixed
	public LeftPanelView(final HandlerManager eventBus) {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setStyleName("LeftPanel");
		initWidget(verticalPanel);
		int Panel_height = 8 * 48;
		verticalPanel.setSize("100%", Panel_height + "px");
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		allLabels = new HashMap<LeftPanelView.Labels, Label>();

		lblViewCoders = addLeftPanel_lable("Coders", eventBus,
				Labels.ViewCoders);
		verticalPanel.add(lblViewCoders);

		lblAddAccount = addLeftPanel_lable("Accounts", eventBus,
				Labels.AddAccounts);
		verticalPanel.add(lblAddAccount);

		lblViewProblem = addLeftPanel_lable("Problems", eventBus,
				Labels.ViewProblems);
		verticalPanel.add(lblViewProblem);

		lblContestAdmin = addLeftPanel_lable("Contest", eventBus,
				Labels.ContestAdmin);
		verticalPanel.add(lblContestAdmin);

		lblJoinContest = addLeftPanel_lable("JoinContest", eventBus,
				Labels.JoinContest);
		verticalPanel.add(lblJoinContest);

		lblViewContest = addLeftPanel_lable("ViewContest", eventBus,
				Labels.ViewContest);
		verticalPanel.add(lblViewContest);

		lblAddProblemsToContest = addLeftPanel_lable("AddProblemToContest",
				eventBus, Labels.AddProblemToContest);
		verticalPanel.add(lblAddProblemsToContest);

		lblStatus = addLeftPanel_lable("Status", eventBus, Labels.Status);
		verticalPanel.add(lblStatus);
	}

	private Label addLeftPanel_lable(String LableName,
			final HandlerManager eventBus, final Labels LinkType) {

		Label lblLabel = new Label(LableName);
		lblLabel.setStylePrimaryName("LeftPanel-Label");
		lblLabel.setSize("100%", "");
		allLabels.put(LinkType, lblLabel);
		lblLabel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (LinkType.equals(Labels.ViewCoders))
					eventBus.fireEvent(new ViewCodersEvent());
				else if (LinkType.equals(Labels.AddAccounts))
					eventBus.fireEvent(new AddAccountEvent());
				else if (LinkType.equals(Labels.ViewProblems))
					eventBus.fireEvent(new AlreadyRegisteredEvent());
				else if (LinkType.equals(Labels.ContestAdmin))
					eventBus.fireEvent(new ContestAdminEvent());
				else if (LinkType.equals(Labels.JoinContest))
					eventBus.fireEvent(new JoinContestEvent());
				else if (LinkType.equals(Labels.ViewContest))
					eventBus.fireEvent(new ViewContestEvent());
				else if (LinkType.equals(Labels.AddProblemToContest))
					eventBus.fireEvent(new ContestProblemEvent());
				else if (LinkType.equals(Labels.Status))
					eventBus.fireEvent(new ViewSubmissionsEvent());
			}
		});
		return lblLabel;

	}

	@Override
	public Widget asWidget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.LeftPanelPresenter.Display#setEnabled(
	 * boolean)
	 */
	@Override
	public void setEnabled(boolean isEnabled) {
		Iterator<Entry<Labels, Label>> it = allLabels.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Labels, Label> pairs = it.next();
			pairs.getValue().setVisible(isEnabled);
		}
	}

	@Override
	public void setSelected(Labels LinkType) {
		Iterator<Entry<Labels, Label>> it = allLabels.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Labels, Label> pairs = it.next();
			if (pairs.getKey().equals(LinkType))
				pairs.getValue().addStyleDependentName("selected");
			else
				pairs.getValue().removeStyleDependentName("selected");
		}
	}
}
