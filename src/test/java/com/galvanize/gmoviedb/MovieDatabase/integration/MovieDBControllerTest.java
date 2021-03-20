package com.galvanize.gmoviedb.MovieDatabase.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieDBControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void emptyMovieDBTest() throws Exception{

        RequestBuilder req = get("/moviedb/list")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)))
                .andDo(print());

    }
}
