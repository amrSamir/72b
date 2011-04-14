package com.OJToolkit_2.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Language {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long languageID;

	@Persistent
	private String languageName;

	public Language() {
		OJType = "SPOJ";
	}

	public Language(String languageName, String languageValue, String oJType) {
		this.languageName = languageName;
		this.languageValue = languageValue;
		OJType = oJType;
	}

	public Long getLanguageID() {
		return languageID;
	}

	public void setLanguageID(Long languageID) {
		this.languageID = languageID;
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

	public String getOJType() {
		return OJType;
	}

	public void setOJType(String oJType) {
		OJType = oJType;
	}

	@Persistent
	private String languageValue;

	@Persistent
	private String OJType;

}
