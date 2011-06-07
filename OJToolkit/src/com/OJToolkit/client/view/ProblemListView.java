/**
 * 
 */
package com.OJToolkit.client.view;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.presenter.ProblemListPresenter;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

/**
 * @author 72B Apr 26, 2011
 */
public class ProblemListView extends Composite implements
		ProblemListPresenter.Display {

	/**
	 * Number of problems in database. It's constant as it costs much time to
	 * query all problems in the database so
	 */
	int numberOfProblems;

	/**
	 * The starting index of the page
	 */
	int pageStart;

	/**
	 * ArrayList that contains the fetched problems from database
	 */
	ArrayList<ProblemData> problemsList;

	/**
	 * The widget which displays the problems
	 */
	CellTable<ProblemData> table;

	final ListDataProvider<ProblemData> dataProvider;

	ScrollPanel scrollPanel;

	public ProblemListView() {
		scrollPanel = new ScrollPanel();
		scrollPanel.setSize("100%", "100%");
		VerticalPanel vPanel = new VerticalPanel();

		initWidget(scrollPanel);

		scrollPanel.add(vPanel);
		
		

		
		table = new CellTable<ProblemData>();
		table.setSize("100%", "100%");

		dataProvider = new ListDataProvider<ProblemData>();

		// Set the columns
		TextColumn<ProblemData> problemCodeColumn = new TextColumn<ProblemData>() {
			@Override
			public String getValue(ProblemData problem) {
				return problem.getProblemCode();
			}
		};
		TextColumn<ProblemData> problemTitleColumn = new TextColumn<ProblemData>() {
			@Override
			public String getValue(ProblemData problem) {
				return problem.getProblemName();
			}
		};
		TextColumn<ProblemData> problemTypeColumn = new TextColumn<ProblemData>() {
			@Override
			public String getValue(ProblemData problem) {
				return problem.getOjType();
			}
		};

		// Add the columns to the table
		table.setRowStyles(new RowStyles<ProblemData>() {
			
			@Override
			public String getStyleNames(ProblemData row, int rowIndex) {
				// TODO Auto-generated method stub
				return "TableRow";
			}
		});
		
		table.addColumn(problemCodeColumn, "Problem Code");
		table.setColumnWidth(problemCodeColumn, 20, Unit.PC);
		
		table.addColumn(problemTitleColumn, "Problem Title");
		table.setColumnWidth(problemTitleColumn, 60, Unit.PC);
		
		table.addColumn(problemTypeColumn, "Online Judge");
		table.setColumnWidth(problemTypeColumn, 20, Unit.PC);
		

		// Set the total row count. This isn't strictly necessary, but it
		// affects paging calculations, so its good habit to keep the row count
		// up to date.
		table.setRowCount(numberOfProblems, true);

		

		dataProvider.addDataDisplay(table);
		
		SimplePager pager = new SimplePager();
		pager.setDisplay(table);
		pager.setPageSize(50);
		pager.setStylePrimaryName("AlignCenter");
		
		vPanel.add(table);
		vPanel.add(pager);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.ProblemListPresenter.Display#getTable()
	 */
	@Override
	public AbstractHasData<ProblemData> getTable() {
		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.ProblemListPresenter.Display#setProblemList
	 * (java.util.ArrayList)
	 */
	@Override
	public void setProblemList(ArrayList<ProblemData> problemsList) {
		this.problemsList = problemsList;
		dataProvider.setList(problemsList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.OJToolkit.client.presenter.ProblemListPresenter.Display#
	 * setNumberOfProblems(int)
	 */
	@Override
	public void setNumberOfProblems(int numberOfProblems) {
		this.numberOfProblems = numberOfProblems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.ProblemListPresenter.Display#setPageStart
	 * (int)
	 */
	@Override
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.presenter.ProblemListPresenter.Display#setPageStart
	 * (int)
	 */
	@Override
	public Widget asWidget() {
		return this;
	}

}
