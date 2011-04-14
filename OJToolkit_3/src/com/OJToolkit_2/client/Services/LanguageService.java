package com.OJToolkit_2.client.Services;

import java.util.ArrayList;

import com.OJToolkit_2.client.Exceptions.NotLoggedInException;
import com.OJToolkit_2.client.ValueObjects.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("language")
public interface LanguageService extends RemoteService {
	public ArrayList<LanguageData> getLanguages() throws NotLoggedInException;
	public void addLanguages();

}
