package com.OJToolkit_2.client;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ContentProblemPage extends Content{
	
	public ContentProblemPage(String prblmID) {
		ScrollPanel scrollPanel = new ScrollPanel();
		initWidget(scrollPanel);
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSize("100%", "100%");
		scrollPanel.add(verticalPanel);
		
		Label lblNewLabel = new Label("Problem Title: ");
		verticalPanel.add(lblNewLabel);
		
		Label lblPrblmTitle = new Label(prblmID);
		verticalPanel.add(lblPrblmTitle);
		
		Frame frame = new Frame("http://www.google.com");
		verticalPanel.add(frame);
		frame.setSize("90%", "300px");
		
		
		Label lblNewLabel_1 = new Label("Code");
		verticalPanel.add(lblNewLabel_1);
		
		TextArea textArea = new TextArea();
		verticalPanel.add(textArea);
		textArea.setSize("90%", "300px");
		
		Label lblLanguage = new Label("Language");
		verticalPanel.add(lblLanguage);
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("C++");
		comboBox.addItem("Java");
		verticalPanel.add(comboBox);
		
		Button btnSubmit = new Button("Submit");
		verticalPanel.add(btnSubmit);
		btnSubmit.setSize("100px", "28px");
		
	
		// TODO Auto-generated constructor stub
	}
}
