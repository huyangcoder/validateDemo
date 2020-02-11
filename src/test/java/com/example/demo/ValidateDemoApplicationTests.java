package com.example.demo;

import com.example.demo.controller.TestValidateController;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateDemoApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		//mockMvc = MockMvcBuilders.standaloneSetup(new TestValidateController()).build();
    }

    @Test
    public void contextLoads() throws Exception {
    	String json = "{\"userName\":\"huyang\",\"age\":\"20\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/test/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

	@Test
	public void contextLoadsNoUerName() throws Exception {
		String json = "{\"age\":\"20\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/test/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json)
		)
				.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("名字不该为空"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}
}
