package com.in28minutes.unitesting.unittesting.service;

import com.in28minutes.unitesting.unittesting.data.Item;
import com.in28minutes.unitesting.unittesting.resource.ItemController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBussinessService itemBussinessService;

    @Test
    public void getItem_test() throws Exception {
        //call GET "/hello"  application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: 10, name: \"Coco10\", location: \"Timisoara\" }"))
                .andReturn();
    }

    @Test
    public void getItemFromBussiness_test() throws Exception {
        //(arrange) mock retrive
        when(itemBussinessService.retriveHardcodedData()).thenReturn(
                new Item(10, "Buss", "Timisoara"));

        //(act) call GET "/hello"  application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-bussiness")
                .accept(MediaType.APPLICATION_JSON);

        //(assert) verify result "Hello World!"
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: 10, name: \"Buss\", location: \"Timisoara\" }"))
                .andReturn();
    }


    @Test
    public void retriveAllItems_test() throws Exception {
        //(arrange) mock retrive
        when(itemBussinessService.retriveAllItems()).thenReturn(
                Arrays.asList(new Item(1, "Buss", "Timisoara"),
                        new Item(2, "Buss", "Timisoara")));

        //(act) call GET "/hello"  application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/items")
                .accept(MediaType.APPLICATION_JSON);

        //(assert) verify result "Hello World!"
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{ id: 1, name: \"Buss\", location: \"Timisoara\" }, " +
                        "{ id: 2, name: \"Buss\", location: \"Timisoara\" }]"))
                .andReturn();
    }
}