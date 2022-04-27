package pl.tkaras.exceptions.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tkaras.models.documents.AppUser;
import pl.tkaras.exceptions.NotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AppUserNotFound extends NotFoundException {

    public AppUserNotFound(AppUser appUser) {
        super(appUser);
    }

    public AppUserNotFound(String email) {
        super(email);
    }
}