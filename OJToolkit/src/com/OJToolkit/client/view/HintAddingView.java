 
package com.OJToolkit.client.view;

 
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
 
import com.google.gwt.user.client.ui.HasValue;
 
import com.google.gwt.user.client.ui.Label;
 
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.OJToolkit.client.presenter.HintAddingPresenter; 
 
import com.google.gwt.user.client.ui.TextArea;

/**
 * @author 72B Apr 26, 2011
 */
public class HintAddingView extends Composite implements
	HintAddingPresenter.Display {
	Button btnSubmit;
	TextArea HintString;
	public HintAddingView() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		Label lblAddHint = new Label("Add Hint ");
		verticalPanel.add(lblAddHint);
		
		Label lblHint = new Label("Hint:");
		verticalPanel.add(lblHint);
		
		 HintString = new TextArea();
		verticalPanel.add(HintString);
		HintString.setSize("443px", "109px");
		
		  btnSubmit = new Button("Submit");
		verticalPanel.add(btnSubmit);
	}

	
	
	
	
	
	
	
	
	@Override
	public HasClickHandlers getSubmitButton() {
		// TODO Auto-generated method stub
		return btnSubmit;
	}

	@Override
	public HasValue<String> getHintString() {
		// TODO Auto-generated method stub
		return HintString;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return this;
	}
	

}
