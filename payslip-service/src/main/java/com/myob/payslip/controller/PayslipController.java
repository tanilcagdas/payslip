package com.myob.payslip.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myob.payslip.converter.CSVConverter;
import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.Employee;
import com.myob.payslip.model.Payslip;
import com.myob.payslip.service.PayslipService;

@RestController
public class PayslipController {
	
	@Autowired
	private PayslipService payslipService;

	@Autowired
	private CSVConverter csvConverter;
	
	
	@RequestMapping(value = "/exportPayslip/{superRatio}/{requestDates}", method = RequestMethod.POST)
	public ResponseEntity<?> exportPayslip(@RequestBody EmployeeWrapper ew, @PathVariable Double superRatio, @PathVariable String requestDates, HttpServletResponse response){
		try {
			Payslip payslip = payslipService.calculatePayslip(ew.getEmpolyee(), superRatio, requestDates);
			String payslipCsv = csvConverter.convert(payslip);
			return new ResponseEntity<>(payslipCsv, HttpStatus.OK);
	    }catch(DateValidationException e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
	

}
class EmployeeWrapper{
	private Employee empolyee;
	
	public Employee getEmpolyee() {
		return empolyee;
	}
	
	public void setEmpolyee(Employee empolyee) {
		this.empolyee = empolyee;
	}
}
