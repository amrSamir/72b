package com.OJToolkit.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author 72B July 11, 2011
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Category {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long categoryID;

	@Persistent
	private String problemCode;

	@Persistent
	private String judgeType;

	@Persistent
	private String category;

	public Category() {
	}

	/**
	 * @param problemCode
	 * @param judgeType
	 * @param category
	 */
	public Category(String problemCode, String judgeType, String category) {
		super();
		this.problemCode = problemCode;
		this.judgeType = judgeType;
		this.category = category;
	}

	/**
	 * @return the categoryID
	 */
	public Long getCategoryID() {
		return categoryID;
	}

	/**
	 * @param categoryID
	 *            the categoryID to set
	 */
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * @return the problemCode
	 */
	public String getProblemCode() {
		return problemCode;
	}

	/**
	 * @param problemCode
	 *            the problemCode to set
	 */
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	/**
	 * @return the judgeType
	 */
	public String getJudgeType() {
		return judgeType;
	}

	/**
	 * @param judgeType
	 *            the judgeType to set
	 */
	public void setJudgeType(String judgeType) {
		this.judgeType = judgeType;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
