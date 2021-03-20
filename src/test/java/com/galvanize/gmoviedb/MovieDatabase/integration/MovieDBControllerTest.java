package com.galvanize.gmoviedb.MovieDatabase.integration;


import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieDBControllerTest {

    @Autowired
    MockMvc mockMvc;
//    Given the GBDB is empty
//    When I visit GMDB movies
//    Then I should see no movies


    //Helper method to post a movie
    public RequestBuilder postMovie(String title, String release, String director){

        RequestBuilder req=post("/moviedb/movie")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("title",title)
                .queryParam("release",release)
                .queryParam("director",director);

        return req;
    }

    @Test
    public void getEmptyMovieDB_Test() throws Exception{

        RequestBuilder req = get("/moviedb/list")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)))
                .andDo(print());

    }
//
//    Given a new movie has released
//    When I submit this new movie to GMDB movies
//    Then I should see that movie in GMDB movies
//    Example
//    {
//        "title": "Steel",
//            "director": "Kenneth Johnson",
//            "actors": "Shaquille O'Neal, Annabeth Gish, Judd Nelson, Richard Roundtree",
//            "release": "1997",
//            "description": "A scientist for the military turns himself into a cartoon-like superhero when a version of one of his own weapons is being used against enemies.",
//            "rating": null
//    }

    @Test
    public void post_NewMovieDB_Test() throws Exception{

        RequestBuilder req=post("/moviedb/movie")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("title","Steel")
                .queryParam("release","1997")
                .queryParam("director","Kenneth Johnson");
        mockMvc.perform(req)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andDo(print());
    }

    @Test
    public void getSingleMovieTest() throws Exception{

        RequestBuilder req=post("/moviedb/movie")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("title","The Avengers")
                .queryParam("release","2012")
                .queryParam("director","Joss Whedon");

        mockMvc.perform(req)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andDo(print());

        RequestBuilder listReq = get("/moviedb/list")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(listReq)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("The Avengers"))
                .andDo(print());

    }

    @Test
    public void getPerticularMovieTest() throws Exception{

        RequestBuilder req1 = postMovie("Superman Returns","2006","Bryan Singer");
        RequestBuilder req2 = postMovie("Steel","1997","Kenneth Johnson");
        RequestBuilder req3 = postMovie("Unbreakable","2000","M. Night Shyamalan");


        mockMvc.perform(req1)
                .andExpect(status().isCreated())
                .andDo(print());


        mockMvc.perform(req2)
                .andExpect(status().isCreated())
                .andDo(print());


        mockMvc.perform(req3)
                .andExpect(status().isCreated())
                .andDo(print());

        RequestBuilder listReq = get("/moviedb/list")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(listReq)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Superman Returns"))
                .andExpect(jsonPath("$[1].title").value("Steel"))
                .andExpect(jsonPath("$[2].title").value("Unbreakable"))
                .andDo(print());

        RequestBuilder rq = get("/moviedb/movie")
                .queryParam("title","Steel")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq)
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("title").value("Steel"))
                .andExpect(jsonPath("release").value("1997"))
                .andExpect(jsonPath("director").value("Kenneth Johnson"))
                .andDo(print());

    }

//    Given the GMDB has many movies
//    When I visit GMDB movies with a non-existing title
//    Then I receive a friendly message that it doesn't exist
    @Test
    public void getParticularMovie_DoesNotExists_Test() throws Exception {

        RequestBuilder req1 = postMovie("The Lego Batman Movie","2017","Chris McKay");
        RequestBuilder req2 = postMovie("The Incredibles","2004","Brad Bird");
        RequestBuilder req3 = postMovie("Rocketeer","2012","Jay Light");


        mockMvc.perform(req1)
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(req2)
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(req3)
                .andExpect(status().isCreated())
                .andDo(print());

        RequestBuilder rq = get("/moviedb/movie")
                .queryParam("title","500 days with her")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("Movie Does Not Exists"))
                .andDo(print());


    }

    @Test
    public void postMovieRating_Test() throws Exception {

        RequestBuilder req1 = postMovie("The Lego Batman Movie","2017","Chris McKay");
        RequestBuilder req2 = postMovie("The Incredibles","2004","Brad Bird");
        RequestBuilder req3 = postMovie("Rocketeer","2012","Jay Light");

        mockMvc.perform(req1)
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(req2)
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(req3)
                .andExpect(status().isCreated())
                .andDo(print());

        RequestBuilder rq = post("/moviedb/movie/rating")
                .queryParam("title","The Incredibles")
                .queryParam("rating","5")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq)
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("id").isNotEmpty())
                .andDo(print());


    }
//
//    Given a movie with one 5 star rating and one 3 star rating
//    When I view the movie details
//    Then I expect the star rating to be 4.
    @Test
    public void getMovieRatingAvg_Test() throws Exception {

        RequestBuilder req1 = postMovie("The Lego Batman Movie","2017","Chris McKay");
        RequestBuilder req2 = postMovie("The Incredibles","2004","Brad Bird");
        RequestBuilder req3 = postMovie("Rocketeer","2012","Jay Light");

        mockMvc.perform(req1)
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(req2)
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(req3)
                .andExpect(status().isCreated())
                .andDo(print());

        RequestBuilder rq1 = post("/moviedb/movie/rating")
                .queryParam("title","The Incredibles")
                .queryParam("rating","4")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq1)
                .andExpect(status().isCreated())
                .andDo(print());

        RequestBuilder rq2 = post("/moviedb/movie/rating")
                .queryParam("title","The Incredibles")
                .queryParam("rating","3")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq2)
                .andExpect(status().isCreated())
                .andDo(print());

        RequestBuilder rq = get("/moviedb/movie/averagerating")
                .queryParam("title","The Incredibles")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("4.0"))
                .andDo(print());

    }



}
