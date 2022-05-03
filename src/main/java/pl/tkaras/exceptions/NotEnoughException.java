package pl.tkaras.exceptions;

public abstract class NotEnoughException extends RuntimeException {

    public NotEnoughException(String className, Object o) {
        System.out.println(className + ": Not enough " + o +  " was found");
    }
}
