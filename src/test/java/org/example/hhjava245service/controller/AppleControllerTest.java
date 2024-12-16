package org.example.hhjava245service.controller;

import org.example.hhjava245service.model.Apple;
import org.example.hhjava245service.repo.AppleRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AppleControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private AppleRepo repo;

   @Test
    void getAll_shouldReturnEmptyList_whenCalledInitially() throws Exception {
       //GIVEN

       //WHEN & THEN
       mockMvc.perform(get("/api/apple"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
   }

   @Test
   void getById_shouldReturnGala_whenCalledWithValidId() throws Exception {
      //GIVEN
      Apple apple = new Apple(
              "1",
              "Gala",
              "Red",
              0.1,
              0.2);
      repo.save(apple);
      //WHEN & THEN
      mockMvc.perform(get("/api/apple/" + apple.id()))
              .andExpect(status().isOk())
              .andExpect(content().json("""
                                       {
                                         "id": "1",
                                         "name": "Gala",
                                         "color": "Red",
                                         "retailPrice": 0.2
                                        }
                                        """));
   }

   @Test
   void createApple_shouldReturnGalaAsDTO_whenCalledWithGala() throws Exception {

      mockMvc.perform(post("/api/apple")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("""
                                {
                                         "id": "1",
                                         "name": "Gala",
                                         "color": "Red",
                                         "price": 0.1,
                                         "retailPrice": 0.2
                                        }
                                """))
              .andExpect(status().isOk())
              .andExpect(content().json("""
                                       {
                                         "name": "Gala",
                                         "color": "Red",
                                         "retailPrice": 0.2
                                        }
                                        """))
              .andExpect(jsonPath("$.id").isNotEmpty());
   }

}