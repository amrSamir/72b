package com.OJToolkit.client.presenter;

import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.weborient.codemirror.client.CodeMirrorConfiguration;
import com.weborient.codemirror.client.CodeMirrorEditorWidget;

public class SourceCodePresenter implements Presenter {
	interface Binder extends UiBinder<Widget, SourceCodePresenter> {
	}

	@UiField
	Label lblProblemCode;

	@UiField
	Label lblJudgeType;

	@UiField
	Label lblUsername;

	@UiField
	Label lblJudgeResult;

	@UiField
	Label lblDate;

	@UiField
	Label lblTime;

	@UiField
	Label lblMemory;

	@UiField
	CodeMirrorEditorWidget txtSourceCode;

	private final SourceCodeServiceAsync sourceCodeService;
	private final long submissionID;

	public SourceCodePresenter(long submissionID,
			SourceCodeServiceAsync sourceCodeService) {
		super();
		this.submissionID = submissionID;
		this.sourceCodeService = sourceCodeService;

	}

	public void go(HasWidgets container) {
		Binder uiBinder = GWT.create(Binder.class);
		Widget widget = uiBinder.createAndBindUi(this);
		// CodeMirrorConfiguration config = new CodeMirrorConfiguration();
		// config.setLineNumbers(true);
		// config.setReadOnly(true);
		// config.set text/x-c++src
		// txtSourceCode.setConfiguration();

		sourceCodeService.getSourceCode(submissionID,
				new AsyncCallback<SourceCodeData>() {

					@Override
					public void onSuccess(SourceCodeData result) {
						lblProblemCode.setText(result.getProblemCode());
						lblJudgeType.setText(result.getJudgeType());
						lblUsername.setText(result.getUsername());
						lblJudgeResult.setText(result.getJudgeResult());
						lblDate.setText(result.getDate());
						lblTime.setText(result.getTime());
						lblMemory.setText(result.getMemory());
						txtSourceCode.setText(result.getSourceCode());
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Could not retreive submission");
					}
				});

		container.clear();
		container.add(widget);
	}
}
