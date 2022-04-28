package pl.tkaras.exceptions.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tkaras.exceptions.NotEnoughException;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AnswerAlreadyExist extends NotEnoughException {

    public AnswerAlreadyExist(String s) {
        super(s);
    }
}