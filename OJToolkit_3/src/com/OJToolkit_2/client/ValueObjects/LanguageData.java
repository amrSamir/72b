package com.OJToolkit_2.client.ValueObjects;

import java.io.Serializable;

public class LanguageData implements Serializable {
	private String languageName, languageValue, ojType;

	public LanguageData(String languageName, String languageValue) {
		super();
		this.languageName = languageName;
		this.languageValue = languageValue;
		this.ojType = "SPOJ";
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getLanguageValue() {
		return languageValue;
	}

	public void setLanguageValue(String languageValue) {
		this.languageValue = languageValue;
	}

	public String getOjType() {
		return ojType;
	}

	public void setOjType(String ojType) {
		this.ojType = ojType;
	}

	public LanguageData() {
	
		// TODO Auto-generated constructor stub
	}

	
	
}
