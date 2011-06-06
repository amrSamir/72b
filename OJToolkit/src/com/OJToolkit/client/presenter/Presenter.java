/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public abstract interface Presenter {
	public abstract void go(final HasWidgets container);

	/**
	 * @param core
	 * @param topPanel
	 * @param leftPanel
	 */
	// void go(HasWidgets core, HasWidgets topPanel, HasWidgets leftPanel);

}
