package com.OJToolkit.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
	}
	
	private final Display display;
	
	public TopPanelPresenter(final Display display) {
		this.display = display;
		bind();
	}
	
	private void bind() {
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
