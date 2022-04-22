package pl.tkaras.exceptions;

public class EmailNotFoundException extends NotFoundException{


    public EmailNotFoundException(String email) {
        super(email);
    }
}
