package com.OJToolkit.client.event;

import com.google.gwt.event.shared.EventHandler;


public interface AlreadyRegisteredEventHandler extends EventHandler {
	void ifRegistered(AlreadyRegisteredEvent event);
}
