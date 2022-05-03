package pl.tkaras.exceptions.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tkaras.models.documents.AppUser;
import pl.tkaras.exceptions.NotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AppUserNotFound extends NotFoundException {

    public AppUserNotFound(String className, AppUser appUser) {
        super(className, appUser);
    }

    public AppUserNotFound(String className, String email) {
        super(className, email);
    }
}