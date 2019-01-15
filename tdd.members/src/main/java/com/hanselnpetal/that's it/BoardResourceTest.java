package com.taskboard.exposition.presentation.apirest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class BoardResourceTest {
//    @Mock
//    @Autowired
//    private BoardService boardService;
//
//    @InjectMocks
//    private BoardResource boardController;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
//    }
//
    @Test
    public void getAllBoards() {
    }
//    @Test
//    public void getAllBoards() throws Exception {
////        //(arrange) mock retrive
////        when(boardService.getAllBoards()).thenReturn(
////                Arrays.asList(new BoardDto("Test1 Board"),
////                        new BoardDto("Test2 Board")));
////
////        //(act) call GET "/boards"  application/json
////        RequestBuilder request = MockMvcRequestBuilders
////                .get("/api/boards")
////                .accept(MediaType.APPLICATION_JSON);
////
////        //(assert) verify result and http result
////        MvcResult result = mockMvc.perform(request)
////                .andExpect(status().isOk())
////                .andExpect(content().string(containsString("Test1 Board")))
////                .andReturn();
//    }
//
//    @Test
//    public void getBoardById() throws Exception {
////        //(arrange) mock retrive
////        when(boardService.getBoardById(any())).thenReturn(
////                new BoardDto("Test1 Board"));
////
////        //(act) call GET "/boards/{id}"  application/json
////        RequestBuilder request = MockMvcRequestBuilders
////                .get("/api/boards/"+ "{id}", new Long(1))
////                .accept(MediaType.APPLICATION_JSON);
////
////        //(assert) verify result and http result
////        MvcResult result = mockMvc.perform(request)
////                .andExpect(status().isOk())
////                //.andExpect(content().json("{ id: 10, title: \"Test1 Board\" }"))
////                .andExpect(content().string(containsString("Test1 Board")))
////                .andReturn();
//    }
//
//    @Test
//    public void createBoard() throws Exception {
////        //(arrange) mock retrive
////        BoardDto test1_board = new BoardDto("Test1 Board");
////        when(boardService.createBoard(any())).thenReturn(
////                test1_board);
////
////        //(act) call POST "/boards/{id}"  application/json
////        RequestBuilder request = MockMvcRequestBuilders
////                .post("/api/boards")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(convertObjectToJson(test1_board))
////                .accept(MediaType.APPLICATION_JSON);
////
////        //(assert) verify result and http result
////        MvcResult result = mockMvc.perform(request)
////                .andExpect(status().isOk())
////                .andExpect(content().string(containsString("Test1 Board")))
////                .andReturn();
//    }
//
//    @Test
//    public void updateBoard() throws Exception {
////        //(arrange) mock retrive
////        BoardDto test1_board = new BoardDto("Test1 Board");
////        when(boardService.updateBoard(any(), any())).thenReturn(
////                test1_board);
////
////        //(act) call PUT "/boards/{id}"  application/json
////        RequestBuilder request = MockMvcRequestBuilders
////                .put("/api/boards/"+ "{id}", new Long(1))
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(convertObjectToJson(test1_board))
////                .accept(MediaType.APPLICATION_JSON);
////
////        //(assert) verify result and http result
////        MvcResult result = mockMvc.perform(request)
////                .andExpect(status().isOk())
////                .andExpect(content().string(containsString("Test1 Board")))
////                .andReturn();
//    }
//
//    @Test
//    public void deleteBoard() throws Exception {
////        //(arrange) mock retrive
////        when(boardService.deleteBoard(any())).thenReturn(
////                new BoardDto("Test1 Board"));
////
////        //(act) call DELETE "/boards/{id}"  application/json
////        RequestBuilder request = MockMvcRequestBuilders
////                .delete("/api/boards/"+ "{id}", new Long(1))
////                .accept(MediaType.APPLICATION_JSON);
////
////        //(assert) verify result and http result
////        MvcResult result = mockMvc.perform(request)
////                .andExpect(status().isNoContent())
////                .andExpect(content().string(containsString("Test1 Board")))
////                .andReturn();
//    }
//
//    public String convertObjectToJson(Object object) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        return mapper.writeValueAsString(object );
//    }
}