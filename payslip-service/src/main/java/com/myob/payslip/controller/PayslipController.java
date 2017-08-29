package com.myob.payslip.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myob.payslip.converter.CSVConverter;
import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.Employee;
import com.myob.payslip.service.PayslipService;

@RestController
public class PayslipController {

	@Autowired
	private PayslipService payslipService;

	@Autowired
	private CSVConverter csvConverter;

	@RequestMapping(value = "/exportPayslip", method = RequestMethod.POST)
	public ResponseEntity<?> exportPayslip(@RequestBody EmployeeWrapper ew, HttpServletResponse response) {
		try {
			StringBuilder sb = new StringBuilder(CSVConverter.CSV_HEADERS);
			List<String> payslips = new ArrayList<>();
			ew.getEmployees().forEach(employee -> {
				try {
					payslips.add(
							csvConverter.convert(payslipService.calculatePayslip(employee, employee.getSuperRatio(), employee.getRequestDates())));
				} catch (DateValidationException e) {
					throw new RuntimeException(e);
				}
			});
			payslips.forEach(ps -> sb.append(ps));

			return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

class EmployeeWrapper {
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
