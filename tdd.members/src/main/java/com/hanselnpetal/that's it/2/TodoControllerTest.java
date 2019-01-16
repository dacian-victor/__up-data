package com.example.data.auditing.tdd.web;

import com.example.data.auditing.tdd.service.TodoNotFoundException;
import com.example.data.auditing.tdd.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Before
    public void setUp() {
        Mockito.reset(todoService);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete_TodoEntryFound_ShouldDeleteTodoEntryAndReturnIt() {
    }

    @Test
    public void delete_TodoIsNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(todoService.delete(3L)).thenThrow(new TodoNotFoundException(3L));

        mockMvc.perform(delete("/api/todo/{id}", 3L))
                .andExpect(status().isNotFound());

        verify(todoService, times(1)).delete(3L);
        verifyNoMoreInteractions(todoService);
    }
}