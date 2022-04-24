package pl.tkaras.services;

import pl.tkaras.api.dto.AppUserDTO;

import java.util.List;

public interface IAppUserService {

    List<AppUserDTO> getAllAppUsers();
    AppUserDTO getAppUser(String email);

    AppUserDTO addAppUser(AppUserDTO userDto);

    AppUserDTO updateAppUser(String email, AppUserDTO userDto);

    void deleteAppUser(String email);

}
