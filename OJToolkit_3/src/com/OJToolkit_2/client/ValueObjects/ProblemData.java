package com.OJToolkit_2.client.ValueObjects;

import java.io.Serializable;

public class ProblemData implements Serializable {
	private String probCode, probName, URL, OJ;

	public ProblemData() {

		// TODO Auto-generated constructor stub
	}

	public ProblemData(String probID, String probName, String uRL, String oJ) {
		super();
		this.probCode = probID;
		this.probName = probName;
		URL = uRL;
		OJ = oJ;
	}

	public String getProbCode() {
		return probCode;
	}

	public void setProbID(String probID) {
		this.probCode = probID;
	}

	public String getProbName() {
		return probName;
	}

	public void setProbName(String probName) {
		this.probName = probName;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getOJ() {
		return OJ;
	}

	public void setOJ(String oJ) {
		OJ = oJ;
	}
	
}
