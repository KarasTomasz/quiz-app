package pl.tkaras.exceptions;


public abstract class NotFoundException extends RuntimeException{

    public NotFoundException(Object o){
        System.out.println(String.format("%s not found", o));
    }

}
