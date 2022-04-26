package pl.tkaras.exceptions;

public abstract class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String object) {
        System.out.println(object + " already exist");
    }
}