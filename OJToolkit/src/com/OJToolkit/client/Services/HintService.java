package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.HintData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("Hint")
public interface HintService extends RemoteService {
	/**
	 * add hint to a problem 
	 * @param problemID
	 * @param hintString
	 */
	void addHint(String problemID, String hintString);
	
	/**
	 * get hints for a problem 
	 * @param problemID
	 * @return
	 */
	ArrayList<HintData> getHints(String problemID);
}
