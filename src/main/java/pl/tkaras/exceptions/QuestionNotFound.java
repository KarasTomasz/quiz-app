package pl.tkaras.exceptions;

public class QuestionNotFound extends NotFoundException{

    public QuestionNotFound(String id) {
        super(id);
    }
}