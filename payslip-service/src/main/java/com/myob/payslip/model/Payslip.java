package com.myob.payslip.model;

import java.util.Iterator;

public class Payslip implements Iterator<Payslip>, Cloneable {

	private Employee employee;
	private int grossIncome;
	private int incomeTax;
	private int netIncome;
	private int superannuation;
	private PaymentDate paymentDate;
	public PaymentDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(PaymentDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	private double superRatio;
	private String paymentDateRequest;
	public double getSuperRatio() {
		return superRatio;
	}

	public void setSuperRatio(double superRatio) {
		this.superRatio = superRatio;
	}

	public String getPaymentDateRequest() {
		return paymentDateRequest;
	}

	public void setPaymentDateRequest(String paymentDateRequest) {
		this.paymentDateRequest = paymentDateRequest;
	}

	public int getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(int netIncome) {
		this.netIncome = netIncome;
	}

	private Payslip next;

	public void setIncomeTax(int incomeTax) {
		this.incomeTax = incomeTax;
	}
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	private Payslip(PayslipBuilder builder) {
		this.employee = builder.employee;
		this.grossIncome = builder.grossIncome;
		this.incomeTax = builder.incomeTax;
		this.netIncome = builder.netIncome;
		this.superannuation = builder.superannuation;
		this.paymentDateRequest = builder.paymentDateRequest;
		this.superRatio = builder.superRatio;
	}

	public int getSuperannuation() {
		return superannuation;
	}

	public void setSuperannuation(int superannuation) {
		this.superannuation = superannuation;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getIncomeTax() {
		return incomeTax;
	}

	public int getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(int grossIncome) {
		this.grossIncome = grossIncome;
	}

	@Override
	public boolean hasNext() {
		if(next != null){
			return true;
		}
		return false;
	}

	@Override
	public Payslip next() {
		return next;
	}
	
	public static PayslipBuilder PayslipBuilder(Employee employee){
		return new PayslipBuilder(employee);
	}
	
	public static PayslipBuilder PayslipBuilder(Employee employee, double superRatio, String paymentDateAsString) {
		return new PayslipBuilder(employee, superRatio, paymentDateAsString);
	}

	public static class PayslipBuilder {

		public int superannuation;
		public int netIncome;
		public int incomeTax;
		private Employee employee;
		private int grossIncome;
		private double superRatio;
		private String paymentDateRequest;

		private PayslipBuilder(Employee employee) {
			this.employee = employee;
		}

		public PayslipBuilder(Employee employee, double superRatioPercent, String paymentDateAsString) {
			this.employee = employee;
			this.superRatio = superRatioPercent/100;
			this.paymentDateRequest = paymentDateAsString;
		}

		public int getSuperannuation() {
			return superannuation;
		}

		public void setSuperannuation(int superannuation) {
			this.superannuation = superannuation;
		}

		public int getNetIncome() {
			return netIncome;
		}

		public void setNetIncome(int netIncome) {
			this.netIncome = netIncome;
		}

		public int getIncomeTax() {
			return incomeTax;
		}

		public void setIncomeTax(int incomeTax) {
			this.incomeTax = incomeTax;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public int getGrossIncome() {
			return grossIncome;
		}

		public void setGrossIncome(int grossIncome) {
			this.grossIncome = grossIncome;
		}

		public double getSuperRatio() {
			return superRatio;
		}

		public void setSuperRatio(double superRatio) {
			this.superRatio = superRatio;
		}

		public String getPaymentDateAsString() {
			return paymentDateRequest;
		}

		public void setPaymentDateAsString(String paymentDateAsString) {
			this.paymentDateRequest = paymentDateAsString;
		}

		public PayslipBuilder grossIncome(int grossIncome) {
			this.grossIncome = grossIncome;
			return this;
		}
		public PayslipBuilder incomeTax(int incomeTax) {
			this.incomeTax = incomeTax;
			return this;
		}

		public Payslip build() {
			return new Payslip(this);
		}

	}

	public void setNext(Payslip next) {
		this.next = next;
		
	}

	


}
