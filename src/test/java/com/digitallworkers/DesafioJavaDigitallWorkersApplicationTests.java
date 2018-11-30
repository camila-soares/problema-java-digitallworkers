package com.digitallworkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.digitallworkers.controllers.ReceiptController;


public class DesafioJavaDigitallWorkersApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private ReceiptController receiptController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(receiptController).build();
	}
	
	@Test
	public void testReceiptInsert() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/receipt")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	protected Logger logger = Logger.getLogger(DesafioJavaDigitallWorkersApplicationTests.class.getName());
    TestRestTemplate template = new TestRestTemplate();
 
    @Test
    public void testGetReport() throws InterruptedException {
        List<HttpStatus> responses = new ArrayList<>();
        int i = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int id = 1;
                    long start = System.currentTimeMillis();
                    ResponseEntity<InputStreamResource> res = template.getForEntity("http://localhost:8080/receipt/download/{id}", InputStreamResource.class, id);
                    logger.info("Response (" +  (System.currentTimeMillis()-start) + "): " + res.getStatusCode());
                    responses.add(res.getStatusCode());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
 
        while (responses.size() != i) {
            Thread.sleep(500);
        }
        logger.info("Test finished");
    }
}
