package com.controllertest.demo.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import com.jayway.jsonpath.JsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void initAll() {
        System.out.println("this is BeforeAll");
    }

    @AfterAll
    static void endAll() {
        System.out.println("this is AfterAll");
    }


    @BeforeEach
    public void initOne() {
        System.out.println("this is BeforeEach");
    }

    @AfterEach
    public void endOne() {
        System.out.println("this is AfterEach");
    }

    @Test
    @DisplayName("测试直接返回字符串")
    void home1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/home/home1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, equalTo("1111"));
    }

    @Test
    @DisplayName("测试get方式登录")
    void loginget() throws Exception {
        //使用expect处理json
        MvcResult mvcResult = mockMvc.perform(get("/home/loginget")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("failed"))
                .andReturn();

        //使用jsonpath处理json,然后用assertThat断言
        mvcResult = mockMvc.perform(get("/home/loginget")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","laoliu")
                .param("password","aaa"))
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.data").value("success"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int code = JsonPath.parse(content).read("$.code");
        assertThat(code, equalTo(0));
        String data = JsonPath.parse(content).read("$.data");
        assertThat(data, equalTo("success"));

    }


    @Test
    @DisplayName("测试post方式登录")
    void loginpost() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/home/loginpost")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, equalTo("error"));

        mvcResult = mockMvc.perform(post("/home/loginpost")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","laoliu")
                .param("password","aaa"))
                .andExpect(status().isOk())
                .andReturn();
        //mvcResult.

        content = mvcResult.getResponse().getContentAsString();
        assertThat(content, equalTo("success"));
    }


}