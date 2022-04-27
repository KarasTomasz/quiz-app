package pl.tkaras.services;

import pl.tkaras.models.documents.AppUser;

import java.util.List;

public interface IAppUserService {

    List<AppUser> getAllAppUsers();

    AppUser getAppUser(String email);

    AppUser addAppUser(AppUser userDto);

    AppUser updateAppUser(String email, AppUser userDto);

    void deleteAppUser(String email);

}