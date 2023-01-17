package com.mstacey.springbootapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilitiesUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestListener.class)
@SpringBootTest(classes = SpringbootApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootApiApplicationTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	private String base;
	private WebDriver driver;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders
                 .webAppContextSetup(webApplicationContext)
                 .build();
		System.setProperty("webdriver.chrome.driver", "C:\\scm\\tools\\chromedriver.exe");
		driver = new ChromeDriver();
		this.base = "http://localhost:" + port;
		
	}

	@Test(description = "Greeting Test")
	public void testGreeting() throws Exception {
		mockMvc.perform(get("/greeting"))
		.andExpect(status().isOk());
	}

	@Test(description = "Selenium Test")
	public void testSelenium() {
		driver.get(base + "/greeting");
		driver.close();
	}

}
