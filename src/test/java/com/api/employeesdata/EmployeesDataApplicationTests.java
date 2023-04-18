package com.api.employeesdata;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.employeesdata.entities.Employee;
import com.api.employeesdata.entities.User;
import com.api.employeesdata.repositories.EmployeeRepository;
import com.google.gson.Gson;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeesDataApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private Gson gson = new Gson();

	private String mockId = "f66fb970-7d49-4831-abd5-668376aeb9b1";

	private List<String> mockIDs;

	private String mockToken;

	@Autowired
	EmployeeRepository mockEmployeeRepository;

	@BeforeAll
	public void setup() throws Exception {
		mockIDs = new ArrayList<>();
		mockToken = getUserToken();
	}

	private String getUserToken() throws Exception {
		
		User testUser = new User();
		testUser.setUsername("testUser");
		testUser.setPassword("password");
		
		String userJson = gson.toJson(testUser);
		MvcResult loginResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
				.contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andExpect(status().isOk()).andReturn();
		return loginResult.getResponse().getHeader("Authorization");
	}

	@Test
	public void getEmployeeListHttpRequest() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")
				.header("Authorization", mockToken)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void addEmployeeHttpRequestAndDeleteHttpRequest() throws Exception {

		Employee emp = new Employee();

		emp.setFirstName("Donald");
		emp.setLastName("Duck");
		emp.setEmail("donaldduck99@yahoo.com");
		emp.setAge(24);
		emp.setActive(false);
		String empJson = gson.toJson(emp);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
					.header("Authorization", mockToken)
					.contentType(MediaType.APPLICATION_JSON)
					.content(empJson))
					.andExpect(status().isOk()).andReturn();
		
		String id = mvcResult.getResponse().toString();
		mockIDs.add(id);

		assertNotNull(mockEmployeeRepository.findById(id));

		mockEmployeeRepository.deleteById(id);
	}

	@Test
	public void addEmployeeWithEmptyVariableFailedHttpRequest() throws Exception {

		Employee emp = new Employee();

		emp.setFirstName("Donald");
		emp.setLastName("");
		emp.setEmail("donaldduck99@yahoo.com");
		emp.setAge(24);
		emp.setActive(false);
		String empJson = gson.toJson(emp);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
			.header("Authorization", mockToken)
			.contentType(MediaType.APPLICATION_JSON)
			.content(empJson))
			.andExpect(status().is4xxClientError()).andReturn();
	}

	@Test
	public void editEmployeeHttpRequest() throws Exception {

		Employee emp = new Employee();

		emp.setFirstName("Donald");
		emp.setLastName("Duck");
		emp.setEmail("donaldduck99@yahoo.com");
		emp.setAge(80);
		emp.setActive(false);
		emp.setEmployee_id(mockId);
		String empJson = gson.toJson(emp);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/" + mockId)
			.header("Authorization", mockToken)
			.contentType(MediaType.APPLICATION_JSON)
			.content(empJson))
			.andExpect(status().isOk()).andReturn();
	}

	@AfterAll
	@Test
	public void cleanup() throws Exception {
		
		//change the edit employee variable back
		Employee emp = new Employee();

		emp.setFirstName("Donald");
		emp.setLastName("Duck");
		emp.setEmail("donaldduck99@yahoo.com");
		emp.setAge(24);
		emp.setActive(false);
		emp.setEmployee_id(mockId);
		String empJson = gson.toJson(emp);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/" + mockId)
			.header("Authorization", mockToken)
			.contentType(MediaType.APPLICATION_JSON)
			.content(empJson))
			.andExpect(status().isOk()).andReturn();
	}
}
