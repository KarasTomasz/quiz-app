package pl.tkaras.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/check")
    public ResponseEntity<AnswerDTO> checkAnswer(@RequestParam("email") String email, @RequestParam("questionID") String questionID, @RequestParam("answer") Integer usersAnswer){
        boolean isAnswerRight = answerService.checkAnswer(email, questionID, usersAnswer);
        Answer answer = answerService.getAnswerByQuestionId(questionID);
        AnswerDTO answerDTO = answerMapper.mapToDto(answer);
        answerDTO.setAnswerRight(isAnswerRight);
        return new ResponseEntity<>(answerDTO , HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("")
    public ResponseEntity<AnswerDTO> updateAnswer(@RequestParam("questionId") String questionId, @RequestBody AnswerDTO answerDTO){
        Answer answer = answerMapper.mapToDocument(answerDTO);
        Answer returnedAnswer = answerService.updateAnswer(questionId, answer);
        return new ResponseEntity<>(answerMapper.mapToDto(returnedAnswer), HttpStatus.OK);
    }
}