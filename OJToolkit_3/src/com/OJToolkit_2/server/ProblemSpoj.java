package com.OJToolkit_2.server;

public class ProblemSpoj {

	private String url;
	private double accP;
	private int noUser;
	private String type;

	public ProblemSpoj(String u, double a, int n) {
		url = u;
		accP = a;
		noUser = n;
	}

	ProblemSpoj() {
	}

	public void setType(String t) {
		type = t;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getAccP() {
		return accP;
	}

	public void setAccP(double accP) {
		this.accP = accP;
	}

	public int getNoUser() {
		return noUser;
	}

	public void setNoUser(int noUser) {
		this.noUser = noUser;
	}

}
