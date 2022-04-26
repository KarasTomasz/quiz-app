package pl.tkaras.api.services;

import pl.tkaras.api.documents.AppUser;

import java.util.List;

public interface IAppUserService {

    List<AppUser> getAllAppUsers();

    AppUser getAppUser(String email);

    AppUser addAppUser(AppUser userDto);

    AppUser updateAppUser(String email, AppUser userDto);

    void deleteAppUser(String email);

}