package pl.tkaras.exceptions;

public abstract class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String className, Object o) {
        System.out.println(className + " : " + o + " already exist");
    }
}