package com.mstacey.springbootapi.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mstacey.springbootapi.SpringbootApiApplication;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringbootApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberStepsTest extends AbstractTestNGSpringContextTests {

    private static Logger logger = LoggerFactory.getLogger(CucumberStepsTest.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

    public final static String GREETING_ENDPOINT = "/greeting";

    @When("^the client calls /greeting$")
    public void the_client_issues_GET_greeting() throws Throwable{
		logger.info("[INFO] Testing greeting endpoint with mockmvc");
		mockMvc = MockMvcBuilders
                 .webAppContextSetup(webApplicationContext)
                 .build();
		mockMvc.perform(get(GREETING_ENDPOINT))
		.andExpect(status().isOk());
    }
    
    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
		mockMvc = MockMvcBuilders
                 .webAppContextSetup(webApplicationContext)
                 .build();
		mockMvc.perform(get(GREETING_ENDPOINT))
		.andExpect(status().isOk());
    }

}
