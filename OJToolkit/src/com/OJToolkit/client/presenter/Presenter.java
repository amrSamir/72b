package com.OJToolkit.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract interface Presenter {
	public abstract void go(final HasWidgets container);
	// void go(HasWidgets core, HasWidgets topPanel, HasWidgets leftPanel);
}
