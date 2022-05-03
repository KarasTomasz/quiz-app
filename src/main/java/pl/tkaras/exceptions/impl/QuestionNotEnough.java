package pl.tkaras.exceptions.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tkaras.exceptions.NotEnoughException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuestionNotEnough extends NotEnoughException {

    public QuestionNotEnough(String className, String s) {
        super(className, s);
    }
}