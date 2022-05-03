package pl.tkaras.exceptions.impl;

import pl.tkaras.exceptions.NotFoundException;

public class AnswerNotFound extends NotFoundException {

    public AnswerNotFound(String className, String s) {
        super(className, s);
    }
}