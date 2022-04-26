package pl.tkaras.exceptions.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tkaras.exceptions.AlreadyExistException;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AppUserAlreadyExist extends AlreadyExistException {

    public AppUserAlreadyExist(String email) {
        super(email);
    }
}