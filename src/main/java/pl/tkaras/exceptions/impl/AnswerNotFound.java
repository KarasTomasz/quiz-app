package pl.tkaras.exceptions.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tkaras.exceptions.NotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AnswerNotFound extends NotFoundException {

    public AnswerNotFound(String className, String s) {
        super(className, s);
    }
}