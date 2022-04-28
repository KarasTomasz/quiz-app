package pl.tkaras.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tkaras.models.documents.Answer;
import pl.tkaras.models.dto.AnswerDTO;
import pl.tkaras.models.mappers.impl.AnswerMapper;
import pl.tkaras.services.impl.AnswerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RequestMapping("api/v1/answer")
@RestController
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @PostMapping("")
    public ResponseEntity<AnswerDTO> addAnswer(@RequestBody AnswerDTO answerDTO){
        Answer answer = answerMapper.mapToDocument(answerDTO);
        Answer returnedAnswer = answerService.addAnswer(answer);
        return new ResponseEntity<>(answerMapper.mapToDto(returnedAnswer), HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<AnswerDTO> checkAnswer(@RequestParam("questionID") String questionID, @RequestParam("num") Integer num){
        boolean isAnswerRight = answerService.checkAnswer(questionID, num);
        Answer answer = answerService.getAnswer(questionID);
        AnswerDTO answerDTO = answerMapper.mapToDto(answer);
        answerDTO.setAnswerRight(isAnswerRight);
        return new ResponseEntity<>(answerDTO , HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<AnswerDTO> updateAnswer(@RequestParam("id") String id, @RequestBody AnswerDTO answerDTO){
        Answer answer = answerMapper.mapToDocument(answerDTO);
        Answer returnedAnswer = answerService.updateAnswer(id, answer);
        return new ResponseEntity<>(answerMapper.mapToDto(returnedAnswer), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAnswer(@RequestParam("id") String id){
        answerService.deleteAnswer(id);
        return new ResponseEntity<>("" , HttpStatus.OK);
    }
}