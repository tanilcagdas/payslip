package com.myob.payslip.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAXRATE")
public class TaxRate {
	
	@Id
	@GeneratedValue
	long id;
	
	@Column(name = "ANUMMIN")
	int anumMin;
	@Column(name = "BASETAX")
	int baseTax;
	@Column(name = "ADDITIONALTAX")
	double additionalTax;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBaseTax() {
		return baseTax;
	}

	public void setBaseTax(int baseTax) {
		this.baseTax = baseTax;
	}

	public double getAdditionalTax() {
		return additionalTax;
	}

	public void setAdditionalTax(double additionalTax) {
		this.additionalTax = additionalTax;
	}

	public void setAnumMin(int anumMin) {
		this.anumMin = anumMin;
	}

	
	public int getAnumMin() {
		return anumMin;
	}

	@Override
	public String toString() {
		return "TaxRate [anumMin=" + anumMin + ", baseTax=" + baseTax + ", additionalTax=" + additionalTax + "]";
	}

	

	public TaxRate(int anumMin, int baseTax, double additionalTax) {
		super();
		this.anumMin = anumMin;
		this.baseTax = baseTax;
		this.additionalTax = additionalTax;
	}

	public TaxRate() {
	}
}
