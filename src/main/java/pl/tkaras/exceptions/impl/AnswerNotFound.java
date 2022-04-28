package pl.tkaras.exceptions.impl;

import pl.tkaras.exceptions.NotFoundException;

public class AnswerNotFound extends NotFoundException {

    public AnswerNotFound(Object o) {
        super(o);
    }
}