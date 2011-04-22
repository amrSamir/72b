package com.OJToolkit_2.client.Contents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.OJToolkit_2.client.CoreContainer;
import com.OJToolkit_2.client.Services.SubmissionService;
import com.OJToolkit_2.client.Services.SubmissionServiceAsync;
import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class ContentProblemPagingList extends Content {

	// private final LanguageServiceAsync languageService =
	// GWT.create(LanguageService.class);
	private final SubmissionServiceAsync submissionService = GWT
			.create(SubmissionService.class);
	  public static class Contact {
		    private final String address;
		    private final String name;

		    public Contact(String name, String address) {
		      this.name = name;
		      this.address = address;
		    }
		  }

	  
	 private static List<Contact> CONTACTS = Arrays.asList(
			    new Contact("John", "123 Fourth Road"),
			    new Contact("John", "123 Fourth Road"),
			    new Contact("John", "123 Fourth Road"),
			    new Contact("John", "123 Fourth Road"),
			    new Contact("John", "123 Fourth Road"),
			    new Contact("John", "123 Fourth Road"),
			    new Contact("Mary", "222 Lancer Lane"));
	 
	public ContentProblemPagingList() {

	    final CellTable<Contact> table = new CellTable<Contact>();

	    final ListDataProvider<Contact> dataProvider = new
	    ListDataProvider<Contact>();
	    
	

	    // Create name column.
	    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
	      @Override
	      public String getValue(Contact contact) {
	        return contact.name;
	      }
	    };

	    // Create address column.
	    TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
	      @Override
	      public String getValue(Contact contact) {
	        return contact.address;
	      }
	    };

	    // Add the columns.
	    table.addColumn(nameColumn, "Name");
	    table.addColumn(addressColumn, "Address");

	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    table.setRowCount(CONTACTS.size(), true);

	    // Push the data into the widget.
	 //  table.setRowData(0, CONTACTS);


	    // Create a SimplePager.
	    SimplePager pager = new SimplePager();
	    
	    
	    dataProvider.addDataDisplay(table);
	    table.addRangeChangeHandler(
	            new RangeChangeEvent.Handler() {
	              public void onRangeChange(RangeChangeEvent event) {
	            	  
	            	  System.out.println(table.getPageStart());
	              }
	            });
	    final SingleSelectionModel mySelectionModel =
	    	new SingleSelectionModel<Contact>();
	    	   table.setSelectionModel(mySelectionModel);
	    	mySelectionModel.addSelectionChangeHandler(new Handler() {
				
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {
					System.out.println("Clicked");
					Contact c = (Contact) mySelectionModel.getSelectedObject();
					System.out.println(c.name);
					//System.out.println(table.get);
					// TODO Auto-generated method stub
					
				}
			}); 
	    
        dataProvider.setList(new ArrayList<Contact>(CONTACTS));
	    // Set the cellList as the display.
	    pager.setDisplay(table);
	    pager.setPageSize(2);
	   // System.out.println(dataProvider.getRanges()[pager.getPage()]);
	    // Add the pager and list to the page.
	   // System.out.println(table.getPageStart());
	    //pager.setPage(1);
	    //System.out.println(table.getPageStart());
	    
	

	    //PagerListener.onPageChange() ;
	    VerticalPanel vPanel = new VerticalPanel();
	    vPanel.add(pager);
	    vPanel.add(table);
	    initWidget(vPanel);
	

	}
  
}
