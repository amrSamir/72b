/**
 * 
 */
package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.event.ViewProblemEvent;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * @author 72B Apr 26, 2011
 */
public class ProblemListPresenter implements Presenter {

	public interface Display {
		/*
		 * HasClickHandlers getSubmitButton(); void setProblemData(ProblemData
		 * problem); void setLanguages(ArrayList<LanguageData> languages);
		 * String getCode(); String getSelectedLanguageValue();
		 */

//		AbstractHasData<ProblemData> getTable();
		
		CellTable<ProblemData> getTable();


		TextArea getSearchBox();

		void setProblemList(ArrayList<ProblemData> problemsList);

		void setNumberOfProblems(int numberOfProblems);

		void setPageStart(int pageStart);

		Widget asWidget();

		// HasValue<String> getProblemTitle();

	}

	private final Display display;
	private final SubmissionServiceAsync submissionService;
	private final HandlerManager eventBus;
	private final CellTable<ProblemData> table;
	private final ArrayList<ProblemData> problemsList;
	private TextArea searchBox;
	private RegExp searchRegExp;
	private final int numberOfProblems = 6684;
	private int pageStart = 0;

	public ProblemListPresenter(SubmissionServiceAsync submissionService,
			HandlerManager eventBus, final Display display) {
		this.submissionService = submissionService;
		this.eventBus = eventBus;
		this.display = display;
		table = display.getTable();
		this.display.setNumberOfProblems(numberOfProblems);

		this.problemsList = new ArrayList<ProblemData>();
		for (int i = 0; i <= numberOfProblems; i++) {
			problemsList.add(null);
		}
		this.display.setProblemList(problemsList);
		this.display.setPageStart(pageStart);
		
		table.addColumn(new Column<ProblemData, String>(new HighlightCell()) {

			@Override
			public String getValue(ProblemData object) {
				return object.getProblemName();
			}
		});
		
		bind();
		fitchFiftyProblems();

	}

	/**
	 * A cell used to highlight search text.
	 */
	public class HighlightCell extends AbstractCell<String> {

		private static final String replaceString = "<span style='color:red;font-weight:bold;'>$1</span>";

		@Override
		public void render(Context context,
				String value, SafeHtmlBuilder sb) {
			if (value != null) {
				if (searchRegExp != null) {
					value = searchRegExp.replace(value, replaceString);
				}
				sb.appendHtmlConstant(value);
			}
		}
	}

	public void fitchFiftyProblems() {
		submissionService.getProblems(pageStart,
				new AsyncCallback<ArrayList<ProblemData>>() {

					@Override
					public void onSuccess(ArrayList<ProblemData> result) {

						for (int i = 0; i < result.size(); i++) {
							problemsList.set(pageStart + i, result.get(i));

						}
						table.setRowData(0, problemsList);
						// display.setProblemList(problemsList);

					}

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Failure");
						// TODO Auto-generated method stub

					}
				});

	}

	/**
	 * Listens to onclick event. If a problem is clicked, it opens the
	 * corresponding problem page for submission
	 */
	private void onClickHandler() {
		final SingleSelectionModel mySelectionModel = new SingleSelectionModel<ProblemData>();
		table.setSelectionModel(mySelectionModel);
		mySelectionModel.addSelectionChangeHandler(new Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				ProblemData problemData = (ProblemData) mySelectionModel
						.getSelectedObject();
				eventBus.fireEvent(new ViewProblemEvent(problemData));

			}
		});
	}

	/**
	 * Listens to OnPageChange Event When the current page changes, it fetches
	 * the 50 problems of the corresponding index from the databse
	 */
	private void onPageChangeHandler() {
		table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
			@Override
			public void onRangeChange(RangeChangeEvent event) {
				pageStart = table.getPageStart();
				if (problemsList.get(pageStart) == null) {
					fitchFiftyProblems();
				}
			}
		});
	}

	/**
     * 
     */
	private void bind() {
		onClickHandler();
		onPageChangeHandler();
		searchHandler();
		// TODO Auto-generated method stub

	}

	public void searchHandler() {
		searchBox = display.getSearchBox();
		searchBox.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// Highlight as the user types.
				String text = searchBox.getText();
				if (text.length() > 0) {
					searchRegExp = RegExp.compile("(" + text + ")", "ig");
				} else {
					searchRegExp = null;
				}
				table.redraw();

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.Presenter#go(com.google.gwt.user.client
	 * .ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		// TODO Auto-generated method stub

	}

}
