package com.myob.payslip.model;

public class Employee {

	public Employee() {

	}

	private Employee(EmployeeBuilder employeeBuilder) {
		this.firstName = employeeBuilder.firstName;
		this.lastName = employeeBuilder.lastName;
		this.annualSalary = employeeBuilder.annualSalary;
	}

	private String firstName;
	private String lastName;
	private int annualSalary;
	private Double superRatio;
	private String requestDates;

	public Double getSuperRatio() {
		return superRatio;
	}

	public void setSuperRatio(Double superRatio) {
		this.superRatio = superRatio;
	}

	public String getRequestDates() {
		return requestDates;
	}

	public void setRequestDates(String requestDates) {
		this.requestDates = requestDates;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(int annualSalary) {
		this.annualSalary = annualSalary;
	}

	public static EmployeeBuilder EmployeeBuilder() {
		return new EmployeeBuilder();
	}
	
	@Override
	public boolean equals(Object obj) {
		Employee employee = (Employee) obj;
		if(employee.annualSalary == annualSalary && employee.firstName.equals(firstName) && employee.lastName.equals(lastName) ){
			return true;
		}
		return false;
	}

	public static class EmployeeBuilder {

		public int annualSalary;
		public String lastName;
		public String firstName;

		public Employee build() {
			return new Employee(this);
		}

		public EmployeeBuilder annualSalary(int annualSalary) {
			this.annualSalary = annualSalary;
			return this;
		}

		public EmployeeBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public EmployeeBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
	}

}
