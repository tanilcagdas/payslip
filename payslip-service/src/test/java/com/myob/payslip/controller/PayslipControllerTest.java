package com.myob.payslip.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.myob.payslip.converter.CSVConverter;
import com.myob.payslip.model.Employee;
import com.myob.payslip.model.Payslip;
import com.myob.payslip.service.PayslipService;




@RunWith(SpringRunner.class)
@WebMvcTest(PayslipController.class)
public class PayslipControllerTest {
	



	    @Autowired
	    private MockMvc mvc;

	    @MockBean
	    private PayslipService payslipService;

	    @MockBean
	    private CSVConverter csvConverter;
	    
	    private static final String RESPONSE_STRING = "NAME	PAY_PERIOD	GROSS_INCOME	INCOME_TAX	NET_INCOME	SUPER"
        		+CSVConverter.NEXT_LINE+"David Rudd	01 March - 31 March	5004	922	4082	450"
        		+CSVConverter.NEXT_LINE+"David Rudd	01 April - 30 April	5004	922	4082	450"
        		+CSVConverter.NEXT_LINE+"David Rudd	01 May - 31 May	5004	922	4082	450";

	    @Test
	    public void testExport() throws Exception {
	    	Employee employee = Employee.EmployeeBuilder().firstName("David").lastName("Rudd").annualSalary(60050).build();
	    	double superRatio = 9.0;
	    	String paymentDateAsString = "01 March - 31 May";
			Payslip payslip = Payslip.PayslipBuilder(employee, superRatio, paymentDateAsString).build();
			given(this.payslipService.calculatePayslip(employee, superRatio, paymentDateAsString))
	                .willReturn(payslip );
			given(this.csvConverter.convert(payslip))
            .willReturn(RESPONSE_STRING);
	        String  requestJson = "{\"empolyee\":{\"firstName\":\"David\",\"lastName\":\"Rudd\",\"annualSalary\":\"60050\"}}";
			this.mvc.perform(post("/exportPayslip/9/01 March - 31 May")
					.contentType(MediaType.APPLICATION_JSON).content(requestJson))
	                .andExpect(status().isOk()).andExpect(content().string(RESPONSE_STRING));
	}

}
