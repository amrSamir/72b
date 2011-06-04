/**
 * 
 */
package com.OJToolkit.server.engine;

/**
 * @author 72B
 *         May 18, 2011
 */
public class Problem {
	private String id;
	private String type;
	private String url;
	private String name;
	private String numberOfAccepted;
	private String numberOfTried;

	public Problem() {
	}

	public Problem(String id, String url, String name, String numberOfAccepted,
	        String numberOfTried) {
		this.id = id;
		this.url = url;
		this.name = name;
		this.numberOfAccepted = numberOfAccepted;
		this.numberOfTried = numberOfTried;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberOfAccepted() {
		return numberOfAccepted;
	}

	public void setNumberOfAccepted(String numberOfAccepted) {
		this.numberOfAccepted = numberOfAccepted;
	}

	public String getNumberOfTried() {
		return numberOfTried;
	}

	public void setNumberOfTried(String numberOfTried) {
		this.numberOfTried = numberOfTried;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
