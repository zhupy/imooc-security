package com.imooc.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author zhupeiyou
 * @since 2020-07-01 22:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    WebApplicationContext mac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(mac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username", "jojo")
                .param("age", "18")
                .param("ageTo", "50")
                .param("xxx", "YYY")
                /* .param("size","15")
                 .param("page","3")
                 .param("sort","age,desc")*/
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String tom = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(tom);

    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date birthday = new Date();
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+ birthday.getTime() +"}";
        //String content= "username=tom&password=pwd&id=1";
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                                             //.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                              .contentType(MediaType.APPLICATION_JSON_UTF8)
                                              .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }
    @Test
    public void whenUpdateSuccess() throws Exception {
        Date birthday = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":"+ birthday.getTime() +"}";
        //String content= "username=tom&password=pwd&id=1";
        mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                                             //.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                              .contentType(MediaType.APPLICATION_JSON_UTF8)
                                              .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }
}
