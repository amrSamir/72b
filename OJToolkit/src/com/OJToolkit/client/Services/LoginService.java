package com.OJToolkit.client.Services;

import com.OJToolkit.client.ValueObjects.LoginInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author 72B The login Service
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

	/**
	 * Login
	 * 
	 * @param requestUri
	 *            The login URL
	 * @return An object of type LoginInfo containing all the login details
	 */
	public LoginInfo login(String requestUri);
}
