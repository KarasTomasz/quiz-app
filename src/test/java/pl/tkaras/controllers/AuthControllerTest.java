package pl.tkaras.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.tkaras.config.BaseIntegrationTest;
import pl.tkaras.models.documents.AppUser;
import pl.tkaras.models.dto.AppUserDTO;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class AuthControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Order(2)
    @Test
    void http_post_shouldReturnTokenIfAppUserExist() throws Exception {
        //given
        addUserToDB();

        //when + then
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/signIn")
                        .param("email", testUser_1().getEmail())
                        .param("password", testUser_1().getPassword())
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String token = mvcResult.getResponse().getContentAsString();
        assertThat(token).isNotNull();
        assertThat(token).startsWith("Bearer ");
    }

    @Order(1)
    @Test
    void http_post_shouldReturn404IfAppUserNotExist() throws Exception {
        //given

        //when + then
        mockMvc.perform(post("/api/v1/auth/signIn")
                        .param("email", testUser_1().getEmail())
                        .param("password", testUser_1().getPassword())
                )
                .andExpect(status().isNotFound());
    }

    public void addUserToDB() throws Exception {
        String json = objectMapper.writeValueAsString(testUserDTO_1());

        mockMvc.perform(post("/api/v1/appUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

    private AppUser testUser_1(){
        return AppUser.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan.kowalski@mail.com")
                .password("password1")
                .build();
    }

    private AppUserDTO testUserDTO_1(){
        return AppUserDTO.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan.kowalski@mail.com")
                .password("password1")
                .build();
    }
}