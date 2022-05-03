package pl.tkaras.exceptions;


public abstract class NotFoundException extends RuntimeException{

    public NotFoundException(String className, Object o){
        System.out.printf(className + " : " + o +" not found");
    }

}
