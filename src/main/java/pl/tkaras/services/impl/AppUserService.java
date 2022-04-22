package pl.tkaras.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tkaras.api.dto.AppUserDto;
import pl.tkaras.api.mappers.AppUserMapper;
import pl.tkaras.exceptions.EmailNotFoundException;
import pl.tkaras.respositories.AppUserRepository;
import pl.tkaras.services.IAppUserService;

import java.util.List;

@AllArgsConstructor
@Service
public class AppUserService implements IAppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    public List<AppUserDto> getAllAppUsers(){
        return appUserMapper.mapToDtos(appUserRepository.findAll());
    }

    public AppUserDto getAppUser(String email){
        return appUserMapper.mapToDto(appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email)));
    }

    public AppUserDto addAppUser(AppUserDto userDto) {
        return null; //TODO: implement register method
    }

    public AppUserDto updateAppUser(String email, AppUserDto userDto) {
        return null; //TODO: implement update
    }

    public void deleteAppUser(String email) {
        //TODO: implement delete
    }
}
