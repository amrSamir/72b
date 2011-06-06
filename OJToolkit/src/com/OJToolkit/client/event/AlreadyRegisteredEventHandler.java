/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public interface AlreadyRegisteredEventHandler extends EventHandler {
	void ifRegistered(AlreadyRegisteredEvent event);
}
