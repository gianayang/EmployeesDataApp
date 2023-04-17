package com.api.employeesdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.employeesdata.repositories.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
class EmployeesDataApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private static MockHttpServletRequest postRequest;

	@Autowired
	EmployeeRepository mockEmployeeRepository;

	@BeforeAll
	public static void setup() {
		postRequest = new MockHttpServletRequest();
		postRequest.setParameter("firstName", "Donald");
		postRequest.setParameter("lastName", "Duck");
		postRequest.setParameter("email", "donaldduck99@yahoo.com");
		postRequest.setParameter("birthday", "1999-04-15T14:40:41.000Z");
		postRequest.setParameter("age", "24");

		// MvcResult loginResult = MockMvc.perform(MockMvcRequestBuilders.post("authenticate")
		// 		.contentType(MediaType.APPLICATION_JSON)
		// 		.param(, null))
	}

	@Test
	public void getEmployeeListHttpRequest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void addEmployeeListHttpRequest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
					.contentType(MediaType.APPLICATION_JSON)
					.param("firstName", postRequest.getParameterValues("firstName"))
					.param("lastName", postRequest.getParameterValues("lastName"))
					.param("email", postRequest.getParameterValues("email"))
					.param("birthday", postRequest.getParameterValues("birthday"))
					.param("age", postRequest.getParameterValues("age")))
					.andExpect(status().isOk()).andReturn();
		// Employee emp = mockEmployeeRepository.findById()
		// assertNotNull(emp);
	}
}
