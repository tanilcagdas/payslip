package com.myob.payslip.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.myob.payslip.repository.TaxRate;
import com.myob.payslip.service.TaxRateService;

@RunWith(SpringRunner.class)
@WebMvcTest(TaxRateController.class)
public class TaxRateControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TaxRateService taxRateService;

	@Test
	public void testFindAll() throws Exception {
		List<TaxRate> list = Arrays.asList(new TaxRate(1, 2, 3.4), new TaxRate(2, 3, 4.5));
		given(this.taxRateService.findAll()).willReturn(list);
		this.mvc.perform(get("/taxRate/findAll").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("[{\"id\":0,\"anumMin\":1,\"baseTax\":2,\"additionalTax\":3.4},{\"id\":0,\"anumMin\":2,\"baseTax\":3,\"additionalTax\":4.5}]"));
	}

}
