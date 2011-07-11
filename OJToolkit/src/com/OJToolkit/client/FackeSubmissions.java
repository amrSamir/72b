package com.OJToolkit.client;

import java.util.Date;

import com.OJToolkit.client.Services.SubmissionService;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FackeSubmissions {
	public SubmissionServiceAsync submissionService = GWT
			.create(SubmissionService.class);

	public FackeSubmissions() {
/*
		System.out.println("Facke Start");

		Date d = new Date(1309514500000L);

		submissionService.addSubmissionResult("amrSamir", "amrSamir", "ACODE",
				"SPOJ", "Accepted", "1.0", "2.00", d,
				new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						System.out.println("facke 1");

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}
				});

		d = new Date(1309514600000L);
		submissionService.addSubmissionResult("amrSamir", "amrSamir", "ACARGO",
				"SPOJ", "Accepted", "1.0", "2.00", d,
				new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						System.out.println("facke 1");
					}

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Fail to facke ");
					}
				});
		d = new Date(1309514700000L);
		submissionService.addSubmissionResult("amrSamir", "amrSamir",
				"AEROLITE", "SPOJ", "Wrong Answer", "1.0", "2.00", d,
				new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						System.out.println("facke 2");

					}

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Fail 2");
					}
				});
		System.out.println("Facke end");*/
	}
}
