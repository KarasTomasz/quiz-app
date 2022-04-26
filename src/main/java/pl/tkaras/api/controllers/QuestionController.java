package pl.tkaras.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tkaras.api.documents.Category;
import pl.tkaras.api.documents.Question;
import pl.tkaras.api.dto.QuestionDTO;
import pl.tkaras.api.mappers.impl.QuestionMapper;
import pl.tkaras.api.services.impl.QuestionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;


    @GetMapping("/category/all")
    public ResponseEntity<List<QuestionDTO>> fetchAllQuestions(@RequestParam("category") Category category){
        List<Question> questions = questionService.getQuestionsByCategory(category);
        return new ResponseEntity<>(questionMapper.mapToDtos(questions), HttpStatus.OK);
    }

    @GetMapping("/category/random")
    public ResponseEntity<List<QuestionDTO>> fetchRandomQuestions(@RequestParam("number") Integer number, @RequestParam Category category){
        List<Question> questions = questionService.getQuestionsByCategory(number, category);
        return new ResponseEntity<>(questionMapper.mapToDtos(questions), HttpStatus.OK);
    }

    @PostMapping("/answer")
    public ResponseEntity<Boolean> checkAnswer(@RequestParam("questionId") String id, @RequestParam("answer") Integer answer){
        boolean result = questionService.checkAnswer(id, answer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<QuestionDTO> addQuestion(@RequestBody QuestionDTO questionDTO){
        Question question = questionMapper.mapToDocument(questionDTO);
        Question returnedQuestion = questionService.addQuestion(question);
        return new ResponseEntity<>(questionMapper.mapToDto(returnedQuestion), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestParam("id") String id, @RequestBody QuestionDTO questionDTO){
        Question question = questionMapper.mapToDocument(questionDTO);
        Question returnedQuestion =questionService.updateQuestion(id, question);
        return new ResponseEntity<>(questionMapper.mapToDto(returnedQuestion), HttpStatus.OK);

    }

    @DeleteMapping("")
    public ResponseEntity<QuestionDTO> deleteQuestion(@RequestParam("id") String id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }
}