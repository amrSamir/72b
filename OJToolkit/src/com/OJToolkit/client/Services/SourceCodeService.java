package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("sourceCodeService")
public interface SourceCodeService  extends RemoteService {
	
	void addCode (String code, String problemCode, String problemName,String url ); 
	
	ArrayList<SourceCodeData> getCodes (Long userID, String problemID);
	
}
