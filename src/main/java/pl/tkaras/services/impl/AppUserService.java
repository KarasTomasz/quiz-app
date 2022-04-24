package pl.tkaras.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tkaras.api.dto.AppUserDTO;
import pl.tkaras.api.mappers.impl.AppUserIMapper;
import pl.tkaras.exceptions.EmailNotFoundException;
import pl.tkaras.respositories.AppUserRepository;
import pl.tkaras.services.IAppUserService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserService implements IAppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserIMapper appUserMapper;

    public List<AppUserDTO> getAllAppUsers(){
        return appUserMapper.mapToDtos(appUserRepository.findAll());
    }

    public AppUserDTO getAppUser(String email){
        return appUserMapper.mapToDto(appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email)));
    }

    public AppUserDTO addAppUser(AppUserDTO userDto) {
        return null; //TODO: implement register method
    }

    public AppUserDTO updateAppUser(String email, AppUserDTO userDto) {
        return null; //TODO: implement update
    }

    public void deleteAppUser(String email) {
        //TODO: implement delete
    }
}
