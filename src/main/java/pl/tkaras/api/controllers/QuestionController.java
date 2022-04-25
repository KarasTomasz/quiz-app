package pl.tkaras.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tkaras.api.documents.Category;
import pl.tkaras.api.dto.QuestionDTO;
import pl.tkaras.services.impl.QuestionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<QuestionDTO>> fetchAllQuestions(@RequestParam("category") Category category){
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/category/")
    public ResponseEntity<List<QuestionDTO>> fetchQuestions(@RequestParam("number") Integer number, @RequestParam Category category){
        return new ResponseEntity<>(questionService.getQuestionsByCategory(number, category), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<QuestionDTO> fetchRandomQuestion(@RequestParam("category") Category category){
        return new ResponseEntity<>(questionService.getRandomQuestion(category), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<QuestionDTO> addQuestion(@RequestBody QuestionDTO questionDTO){
        return new ResponseEntity<>(questionService.addQuestion(questionDTO), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestParam("id") String id, @RequestBody QuestionDTO questionDTO){
        return new ResponseEntity<>(questionService.updateQuestion(id, questionDTO), HttpStatus.OK);

    }

    @DeleteMapping("")
    public ResponseEntity<QuestionDTO> deleteQuestion(@RequestParam("id") String id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }
}