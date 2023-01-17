package com.mstacey.springbootapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Listeners(TestListener.class)
@SpringBootTest(classes = SpringbootApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootApiApplicationTest extends AbstractTestNGSpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(SpringbootApiApplicationTest.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	private String base;
	private WebDriver driver;
	public final static String GREETING_ENDPOINT = "/greeting";

	@BeforeClass
	public void setup() {
		logger.info("[INFO] Started setting up test environment");
		mockMvc = MockMvcBuilders
                 .webAppContextSetup(webApplicationContext)
                 .build();
		System.setProperty("webdriver.chrome.driver", "C:\\scm\\tools\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		this.base = "http://localhost:" + port;
		logger.info("[INFO] Finished setting up test environment");
		
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@Test(description = "Greeting Test")
	public void testGreeting() throws Exception {
		logger.info("[INFO] Testing greeting endpoint with mockmvc");
		mockMvc.perform(get(GREETING_ENDPOINT))
		.andExpect(status().isOk());
	}

	@Test(description = "Selenium Test")
	public void testSelenium() throws Exception {
		logger.info("[INFO] Testing greeting endpoint with selenium");
		driver.get(base + GREETING_ENDPOINT);
		logger.info("[INFO] zzzzzzz");
		Thread.sleep(2500);
	}

}
