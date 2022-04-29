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
import pl.tkaras.models.mappers.impl.AppUserMapper;
import pl.tkaras.services.impl.AppUserService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class AppUserControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Order(6)
    @Test
    void http_post_shouldNotAddAppUserIfExist() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testUserDTO_1());

        //when + then
        mockMvc.perform(post("/api/v1/appUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isForbidden());

    }

    @Order(5)
    @Test
    void http_post_shouldAddAppUserIfNotExist() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testUserDTO_1());

        //when
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/appUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(testUserDTO_1())))
                .andReturn();

        //then
        AppUserDTO appUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AppUserDTO.class);
        assertThat(appUserDTO).isNotNull();
        assertThat(appUserDTO.getFirstName()).isEqualTo(testUserDTO_1().getFirstName());
        assertThat(appUserDTO.getLastName()).isEqualTo(testUserDTO_1().getLastName());
        assertThat(appUserDTO.getEmail()).isEqualTo(testUserDTO_1().getEmail());

    }

    @Order(1)
    @Test
    void http_get_shouldFetchEmptyListIfAppUsersNotExist() throws Exception {
        //given

        //when + then
        mockMvc.perform(get("/api/v1/appUser/all"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

    }

    @Order(7)
    @Test
    void http_get_shouldFetchAllAppUsersIfExist() throws Exception {
        //given

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/appUser/all")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        List<AppUserDTO> appUserDTOs = objectMapper.readValue(mvcResult.getResponse().getContentAsString() ,objectMapper.getTypeFactory().constructCollectionType(List.class,AppUserDTO.class));
        assertThat(appUserDTOs).isNotNull();
        assertThat(appUserDTOs.size()).isEqualTo(prepareAppUserDtos().size());
        assertThat(appUserDTOs.get(0).getFirstName()).isEqualTo(prepareAppUserDtos().get(0).getFirstName());
        assertThat(appUserDTOs.get(0).getLastName()).isEqualTo(prepareAppUserDtos().get(0).getLastName());
        assertThat(appUserDTOs.get(0).getEmail()).isEqualTo(prepareAppUserDtos().get(0).getEmail());

    }

    @Order(2)
    @Test
    void http_get_shouldReturn404IfAppUserNotExists() throws Exception {
        //given

        //when + then
        mockMvc.perform(get("/api/v1/appUser")
                        .param("email", testUser_1().getEmail())
                )
                .andExpect(status().isNotFound());
    }

    @Order(8)
    @Test
    void http_get_shouldFetchAppUserByEmailIfExists() throws Exception {
        //given

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/appUser")
                        .param("email", testUser_1().getEmail())
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        AppUserDTO appUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AppUserDTO.class);
        assertThat(appUserDTO).isNotNull();
        assertThat(appUserDTO.getFirstName()).isEqualTo(testUserDTO_1().getFirstName());
        assertThat(appUserDTO.getLastName()).isEqualTo(testUserDTO_1().getLastName());
        assertThat(appUserDTO.getEmail()).isEqualTo(testUserDTO_1().getEmail());
    }

    @Order(3)
    @Test
    void http_put_shouldReturn404IfNotExists() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testUserDTO_1());

        //when + then

        mockMvc.perform(put("/api/v1/appUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("email", testUser_1().getEmail())
                        .content(json)
                )
                .andExpect(status().isNotFound());

    }

    @Order(9)
    @Test
    void http_put_shouldUpdateAppUserIfExists() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testUserDTO_2());

        //when
        MvcResult mvcResult = mockMvc.perform(put("/api/v1/appUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("email", testUser_1().getEmail())
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        AppUserDTO appUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AppUserDTO.class);
        assertThat(appUserDTO).isNotNull();
        assertThat(appUserDTO.getFirstName()).isEqualTo(testUserDTO_2().getFirstName());
        assertThat(appUserDTO.getLastName()).isEqualTo(testUserDTO_2().getLastName());
        assertThat(appUserDTO.getEmail()).isNotEqualTo(testUserDTO_2().getEmail());

    }

    @Order(4)
    @Test
    void http_delete_shouldReturn404IfAppUserNotExists() throws Exception {
        //given

        //when + then
        mockMvc.perform(delete("/api/v1/appUser")
                        .param("email", testUser_1().getEmail())
                )
                .andExpect(status().isNotFound());

    }

    @Order(10)
    @Test
    void http_delete_shouldDeleteAppUserIfExists() throws Exception {

        mockMvc.perform(delete("/api/v1/appUser")
                        .param("email", testUser_1().getEmail())
                )
                .andExpect(status().isOk())
                .andDo(print());

    }

    private List<AppUserDTO> prepareAppUserDtos(){
        return Arrays.asList(testUserDTO_1());
    }

    private List<AppUser> prepareAppUsers(){
        return Arrays.asList(testUser_1(), testUser_2());
    }

    private AppUserDTO testUserDTO_1(){
        return AppUserDTO.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan.kowalski@mail.com")
                .password("password1")
                .build();
    }

    private AppUserDTO testUserDTO_2(){
        return AppUserDTO.builder()
                .firstName("Karol")
                .lastName("Nowak")
                .email("karol.nowak@mail.com")
                .password("password2")
                .build();
    }

    private AppUser testUser_1(){
        return AppUser.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan.kowalski@mail.com")
                .password("password1")
                .build();
    }

    private AppUser testUser_2(){
        return AppUser.builder()
                .firstName("Karol")
                .lastName("Nowak")
                .email("karol.nowak@mail.com")
                .password("password2")
                .build();
    }

}