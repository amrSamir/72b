package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.ContestServicesAsync;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.OJToolkit.client.presenter.JoinContestPresenter.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ViewContestPresenter implements Presenter {

	public interface Display {
		void setCoders(ArrayList<CoderData> coders);

		void setProblems(ArrayList<ProblemData> problems);

		void setContests(ArrayList<ContestData> contests);

		void setSubmissions(ArrayList<SubmissionData> submissions) ;
		

		HasClickHandlers getSubmitButton();

		String getContestName();

		Widget asWidget();
	}

	private final Display display;
	private final ContestServicesAsync contestServises;
	private final HandlerManager eventBus;

	public ViewContestPresenter(ContestServicesAsync contestServices,
			HandlerManager eventBus, Display display) {
		this.contestServises = contestServices;
		this.eventBus = eventBus;
		this.display = display;
		getContests();
		bind();
	}

	private void bind() {
		display.getSubmitButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {				
				String contestname = display.getContestName();
				getCoders(contestname);
				getProblems(contestname);
				getSubmissions(contestname) ;
			}
		});

	}

	private void getContests() {
		contestServises
				.getContestForAdmin(new AsyncCallback<ArrayList<ContestData>>() {
					ArrayList<ContestData> contests = new ArrayList<ContestData>();

					@Override
					public void onSuccess(ArrayList<ContestData> result) {
						contests.addAll(result);
						display.setContests(contests);
					}

					@Override
					public void onFailure(Throwable caught) {
						System.out
								.println("Magdi-viewContest- canot load contest");
					}
				});
	}

	private void getCoders(String contestname) {
		if (contestname.equals("")) {
			Window.alert("please Chosse Contest to View");
			return;
		}
		contestServises.getCodersForContest(contestname,
				new AsyncCallback<ArrayList<CoderData>>() {

					@Override
					public void onSuccess(ArrayList<CoderData> result) {
						display.setCoders(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						System.out
								.println("Magdi-viewContest- canot load coders");
					}
				});

	}

	private void getProblems(String contestname) {
		if (contestname.equals("")) {
			Window.alert("please Chosse Contest to View");
			return;
		}
		contestServises.getProblemForContest(contestname,
				new AsyncCallback<ArrayList<ProblemData>>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out
								.println("Magdi-viewContest- canot load problems");
					}

					@Override
					public void onSuccess(ArrayList<ProblemData> result) {
						display.setProblems(result);
					}
				});
	}

	private void getSubmissions(String contestname) {
		if (contestname.equals("")) {
			Window.alert("please Chosse Contest to View");
			return;
		}
		contestServises.getContestSubmissions(contestname,
				new AsyncCallback<ArrayList<SubmissionData>>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Magdi-viewContest- canot load Submissions");
					}

					@Override
					public void onSuccess(ArrayList<SubmissionData> result) {
						display.setSubmissions(result);
					}
				});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
