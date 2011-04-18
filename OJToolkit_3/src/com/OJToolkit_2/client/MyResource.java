package com.OJToolkit_2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface MyResource extends ClientBundle {

	MyResource INSTANCE = GWT.create(MyResource.class);

	@Source("problemsSPOJ.txt")
	public TextResource defaultText();
	
}
