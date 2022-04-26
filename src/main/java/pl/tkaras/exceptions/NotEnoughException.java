package pl.tkaras.exceptions;

public abstract class NotEnoughException extends RuntimeException {

    public NotEnoughException(String s) {
        System.out.println("Not enough " + s +  " was found");
    }
}
