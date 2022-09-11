package com.gerimedica.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;


@ToString
@Entity
@Table(name = "csvfile")
public class CsvFile {

	public CsvFile() {

	}

	public CsvFile(String source, String codeListCode, String code, String displayValue, String longDescription,
			String fromDate, String toDate, String sortingPriority) {
		super();
		this.source = source;
		this.codeListCode = codeListCode;
		this.code = code;
		this.displayValue = displayValue;
		this.longDescription = longDescription;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.sortingPriority = sortingPriority;
	}

	@Column(name = "SOURCE")
	private String source;

	@Column(name = "CODE_LIST_CODE")
	private String codeListCode;

	@Id
	@Column(name = "CODE", unique = true)
	private String code;

	@Column(name = "DISPLAY_VALUE")
	private String displayValue;

	@Column(name = "LONG_DESCRIPTION")
	private String longDescription;

	@Column(name = "FROM_DATE")
	private String fromDate;

	@Column(name = "TO_DATE")
	private String toDate;

	@Column(name = "SORTING_PRIORITY")
	private String sortingPriority;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCodeListCode() {
		return codeListCode;
	}

	public void setCodeListCode(String codeListCode) {
		this.codeListCode = codeListCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getSortingPriority() {
		return sortingPriority;
	}

	public void setSortingPriority(String sortingPriority) {
		this.sortingPriority = sortingPriority;
	}
	
	

}
