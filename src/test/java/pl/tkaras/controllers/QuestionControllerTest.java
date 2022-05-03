package pl.tkaras.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.tkaras.config.BaseIntegrationTest;
import pl.tkaras.models.documents.Category;
import pl.tkaras.models.documents.Question;
import pl.tkaras.models.dto.QuestionDTO;
import pl.tkaras.models.mappers.impl.QuestionMapper;
import pl.tkaras.services.impl.QuestionService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class QuestionControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

    @Order(7)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_get_shouldFetchAllQuestionsIfExist() throws Exception {
        //given

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/question/category/all")
                        .param("category", Category.IT.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        List<Question> questions = objectMapper.readValue(mvcResult.getResponse().getContentAsString() ,objectMapper.getTypeFactory().constructCollectionType(List.class,Question.class));
        assertThat(questions.get(0)).isNotNull();
        assertThat(questions.get(0).getContent()).isEqualTo(testQuestionDTO_1().getContent());
        assertThat(questions.get(0).getAnswers()).isEqualTo(testQuestionDTO_1().getAnswers());
  }

    @Order(1)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_get_shouldFetchEmptyListIfQuestionsNotExist() throws Exception {
        //given

        //when + then
        mockMvc.perform(get("/api/v1/question/category/all")
                        .param("category", Category.IT.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

    }

    @Order(8)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_get_shouldFetchRandomQuestionsIfExist() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testQuestionDTO_1());

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/question/category/random")
                        .param("number", "1")
                        .param("category", Category.IT.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        List<Question> questions = objectMapper.readValue(mvcResult.getResponse().getContentAsString() ,objectMapper.getTypeFactory().constructCollectionType(List.class,Question.class));
        assertThat(questions.get(0)).isNotNull();
        assertThat(questions.get(0).getContent()).isEqualTo(testQuestionDTO_1().getContent());
        assertThat(questions.get(0).getAnswers()).isEqualTo(testQuestionDTO_1().getAnswers());
    }

    @Order(2)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_get_shouldReturn404IfQuestionsNotExist() throws Exception {
        //given

        //when + then
        mockMvc.perform(get("/api/v1/question/category/random")
                        .param("number", "1")
                        .param("category", Category.IT.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Order(3)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_post_shouldReturn404IfCheckAnswerAndNotExist() throws Exception {
        //given

        //when + then
        mockMvc.perform(post("/api/v1/question/answer")
                        .param("questionId", "abc")
                        .param("answer", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Order(6)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_post_shouldAddQuestion() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testQuestionDTO_1());

        //when
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        Question question = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Question.class);
        assertThat(question).isNotNull();
        assertThat(question.getCategory()).isEqualTo(testQuestionDTO_1().getCategory());
        assertThat(question.getContent()).isEqualTo(testQuestionDTO_1().getContent());
        assertThat(question.getAnswers()).isEqualTo(testQuestionDTO_1().getAnswers());
    }

    @Order(9)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_put_shouldUpdateQuestionIfExist() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testQuestionDTO_2());

        //when
        MvcResult mvcResult = mockMvc.perform(put("/api/v1/question")
                        .param("id", getSavedId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        Question question = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Question.class);
        assertThat(question).isNotNull();
        assertThat(question.getContent()).isEqualTo(testQuestionDTO_2().getContent());
        assertThat(question.getAnswers()).isEqualTo(testQuestionDTO_2().getAnswers());

    }

    @Order(4)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_put_shouldReturn404IfUpdateQuestionAndNotExist() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(testQuestionDTO_1());

        //when + then
        mockMvc.perform(put("/api/v1/question")
                        .param("id", "abc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Order(10)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_delete_shouldDeleteQuestionIfExist() throws Exception {
        //given

        //when + then
        MvcResult mvcResult = mockMvc.perform(delete("/api/v1/question")
                        .param("id", getSavedId())
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Order(5)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void http_delete_shouldReturn404IfDeleteQuestionAndNotExist() throws Exception {
        //given

        //when + then
        mockMvc.perform(delete("/api/v1/question")
                        .param("id", "abc")
                )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    private String getSavedId() throws Exception {

        MvcResult result = mockMvc.perform(get("/api/v1/question/category/all")
                        .param("category", Category.IT.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        List<Question> questions = objectMapper.readValue(result.getResponse().getContentAsString() ,objectMapper.getTypeFactory().constructCollectionType(List.class,Question.class));
        return questions.get(0).getId();

    }

    private List<QuestionDTO> prepareQestionDTOs(){
        return Arrays.asList(testQuestionDTO_1());
    }

    private QuestionDTO testQuestionDTO_1(){
        return QuestionDTO.builder()
                .category(Category.IT)
                .content("content_1")
                .answers(Arrays.asList("1", "2", "3", "4"))
                .correctAnswer(1)
                .build();
    }

    private QuestionDTO testQuestionDTO_2(){
        return QuestionDTO.builder()
                .category(Category.IT)
                .content("content_2")
                .answers(Arrays.asList("5", "6", "7", "8"))
                .correctAnswer(2)
                .build();
    }
}