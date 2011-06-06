package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.HintData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("Hint")
public interface HintService extends RemoteService {
	void addHint(String problemID, String hintString);
	
	ArrayList<HintData> getHints(String problemID);
}
