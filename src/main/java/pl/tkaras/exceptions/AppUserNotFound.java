package pl.tkaras.exceptions;

import pl.tkaras.api.documents.AppUser;

public class AppUserNotFound extends NotFoundException {

    public AppUserNotFound(AppUser appUser) {
        super(appUser);
    }
}
