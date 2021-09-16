package com.alb.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_all_should_return_list() throws Exception {
        this.mockMvc.perform(get("/tasks/all")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"label\":\"Créer une route /tasks/all\", \"status\":\"in_progress\"},{\"label\":\"Créer une route /tasks/{id}\", \"status\":\"to_do\"}]"));
    }

    @Test
    void put_should_return_id() throws Exception {
        String body = "{\"label\":\"Tâche terminée\", \"status\":\"done\"}";
        this.mockMvc.perform(put("/tasks").content(body).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("3"));
    }

    @Test
    void get_by_id_should_return_not_found() throws Exception {
        this.mockMvc.perform(get("/tasks/20")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void get_by_id_should_return_model() throws Exception {
        this.mockMvc.perform(get("/tasks/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1, \"label\":\"Créer une route /tasks/all\", \"status\":\"in_progress\"}"));
    }

    @Test
    void get_by_status_should_return_list() throws Exception {
        this.mockMvc.perform(get("/tasks?status=to_do")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":2,\"label\":\"Créer une route /tasks/{id}\", \"status\":\"to_do\"}]"));
    }

    @Test
    void get_by_status_should_return_400() throws Exception {
        this.mockMvc.perform(get("/tasks?status=in_todo")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void post_should_return_404() throws Exception {
        String body = "{\"status\":\"done\"}";
        this.mockMvc.perform(post("/tasks/20").content(body).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void post_should_return_ok() throws Exception {
        String body = "{\"status\":\"done\"}";
        this.mockMvc.perform(post("/tasks/1").content(body).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
}
