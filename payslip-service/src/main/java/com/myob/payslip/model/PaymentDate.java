package com.myob.payslip.model;

import java.time.LocalDate;

public class PaymentDate {
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String dateAsString;

	public PaymentDate(LocalDate startDate, LocalDate endDate, String dateAsString) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.dateAsString = dateAsString;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDateAsString() {
		return dateAsString;
	}

	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}

}
