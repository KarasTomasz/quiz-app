package pl.tkaras.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.tkaras.config.BaseIntegrationTest;
import pl.tkaras.models.documents.Category;
import pl.tkaras.models.dto.AnswerDTO;
import pl.tkaras.models.dto.AppUserDTO;
import pl.tkaras.models.dto.QuestionDTO;
import pl.tkaras.respositories.AnswerRepository;
import pl.tkaras.respositories.AppUserRepository;
import pl.tkaras.respositories.QuestionRepository;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class AnswerControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AppUserRepository appUserRepository;


    @AfterEach
    void cleanUp(){
        System.out.println("after");
        questionRepository.deleteAll();
        answerRepository.deleteAll();
        appUserRepository.deleteAll();

    }

    @Order(1)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_get_shouldReturn404IfCheckAnswerAndAppUserNotExists() throws Exception {
        //given

        //when + then
        mockMvc.perform(get("/api/v1/answer/check")
                        .param("email", "")
                        .param("questionID", "")
                        .param("answer", String.valueOf(1)))
                .andExpect(status().isNotFound());
    }

    @Order(2)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_get_shouldReturn404IfCheckAnswerAndQuestionNotExists() throws Exception {
        //given
        String questionId = addQuestion();

        //when + then
        mockMvc.perform(get("/api/v1/answer/check")
                        .param("email", "")
                        .param("questionID", questionId)
                        .param("answer", String.valueOf(1)))
                .andExpect(status().isNotFound());
    }

    @Order(3)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_get_shouldCheckAnswerIfExists() throws Exception {
        //given
        String questionId = addQuestion();
        String email = addAppUser();

        //when + then
        mockMvc.perform(get("/api/v1/answer/check")
                        .param("email", email)
                        .param("questionID", questionId)
                        .param("answer", String.valueOf(1)))
                .andExpect(status().isOk());
    }

    @Order(4)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_put_shouldReturn404IfUpdateAnswerAndNotExists() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testQuestionDTO_1());

        //when + then
        mockMvc.perform(put("/api/v1/answer")
                        .param("questionId", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Order(5)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_put_shouldUpdateAnswerIfExists() throws Exception {
        //given
        String questionId = addQuestion();
        String json = objectMapper.writeValueAsString(testAnswerDto_1());

        //when + then
        MvcResult mvcResult = mockMvc.perform(put("/api/v1/answer")
                        .param("questionId", questionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        AnswerDTO answerDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AnswerDTO.class);

        assertThat(answerDTO).isNotNull();

    }

    private String addQuestion() throws Exception {
        String json = objectMapper.writeValueAsString(testQuestionDTO_1());

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andReturn();

        QuestionDTO questionDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), QuestionDTO.class);
        return questionDTO.getId();
    }

/*    private String getQuestionId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/question/category/all")
                        .param("category", Category.IT.toString())
                )
                .andExpect(status().isOk())
                .andReturn();

        List<Question> questions = objectMapper.readValue(mvcResult.getResponse().getContentAsString() ,objectMapper.getTypeFactory().constructCollectionType(List.class,Question.class));
        return questions.get(0).getId();
    }*/

    private String addAppUser() throws Exception {
        String json = objectMapper.writeValueAsString(testAppUserDTO_1());

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/appUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andReturn();

        AppUserDTO appUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AppUserDTO.class);
        return appUserDTO.getEmail();
    }


    private AppUserDTO testAppUserDTO_1(){
        return AppUserDTO.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan.kowalski@mail.com")
                .password("password1")
                .build();
    }

    private QuestionDTO testQuestionDTO_1(){
        return QuestionDTO.builder()
                .id("abc")
                .category(Category.IT)
                .content("content_1")
                .answers(Arrays.asList("1", "2", "3", "4"))
                .correctAnswer(1)
                .build();
    }
    private AnswerDTO testAnswerDto_1(){
        return AnswerDTO.builder()
                .questionId("ghi")
                .correctAnswer(1)
                .build();
    }
}