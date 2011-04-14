package com.OJToolkit_2.client.Services;

import java.util.ArrayList;

import com.OJToolkit_2.client.Exceptions.NotLoggedInException;
import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("problem")
public interface ProblemService extends RemoteService{
	public ArrayList<ProblemData> getAllProblems() throws NotLoggedInException;
	public ProblemData getPorblem(String probID)throws NotLoggedInException;
	public void addProblem(ProblemData probData) throws NotLoggedInException;
}
