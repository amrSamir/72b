package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author 72B Team Holds the data of the languages
 */
@SuppressWarnings("serial")
public class LanguageData implements Serializable {
	private String languageName, languageValue, ojType;

	/**
	 * Creates an object holding the data of language
	 * 
	 * @param languageName
	 *            The name of the language
	 * @param languageValue
	 *            The value of the language
	 * @param ojType
	 *            The location of the language existence in online judge
	 */
	public LanguageData(String languageName, String languageValue, String ojType) {
		super();
		this.languageName = languageName;
		this.languageValue = languageValue;
		this.ojType = ojType;
	}

	/**
	 * @return The name of the language
	 */
	public String getLanguageName() {
		return languageName;
	}

	/**
	 * @param languageName
	 *            The name of the language
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * @return value of the language
	 */
	public String getLanguageValue() {
		return languageValue;
	}

	/**
	 * @param languageValue
	 *            value of the language
	 */
	public void setLanguageValue(String languageValue) {
		this.languageValue = languageValue;
	}

	/**
	 * @return The location of the language existence in online judge
	 */
	public String getOjType() {
		return ojType;
	}

	/**
	 * @param ojType
	 *            The location of the language existence in online judge
	 */
	public void setOjType(String ojType) {
		this.ojType = ojType;
	}

	/**
	 * Creates an object of the language data
	 */
	public LanguageData() {
		
	}

}
