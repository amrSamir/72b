package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("sourceCodeService")
public interface SourceCodeService  extends RemoteService {
	boolean isCodeVisible(long submissionID);
	
	SourceCodeData getSourceCode(long submissionID);
	
	void addCategories(String problemCode, String judgeType, ArrayList<String> categoriesList);
	
	ArrayList<String> getCategories(String problemCode, String judgeType);
	
	
}
