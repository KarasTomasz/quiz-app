package pl.tkaras.services;

import pl.tkaras.api.dto.AppUserDto;

import java.util.List;
import java.util.Optional;

public interface IAppUserService {

    List<AppUserDto> getAllAppUsers();
    AppUserDto getAppUser(String email);

    AppUserDto addAppUser(AppUserDto userDto);

    AppUserDto updateAppUser(String email, AppUserDto userDto);

    void deleteAppUser(String email);

}
