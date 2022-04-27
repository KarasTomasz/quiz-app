package pl.tkaras.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tkaras.models.documents.AppUser;
import pl.tkaras.services.IAppUserService;
import pl.tkaras.exceptions.impl.AppUserAlreadyExist;
import pl.tkaras.exceptions.impl.AppUserNotFound;
import pl.tkaras.exceptions.impl.EmailNotFound;
import pl.tkaras.respositories.AppUserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserService implements IAppUserService {

    private final AppUserRepository appUserRepository;

    public List<AppUser> getAllAppUsers(){
        return appUserRepository.findAll();
    }

    public AppUser getAppUser(String email){
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFound(email));
    }

    public AppUser addAppUser(AppUser appUser) {
        if(!appUserRepository.existsByEmail(appUser.getEmail())){
            return appUserRepository.save(appUser);
        }
        else{
            throw new AppUserAlreadyExist(appUser.getEmail());
        }
    }

    public AppUser updateAppUser(String email, AppUser appUser) {
        return null; //TODO: implement update
    }

    public void deleteAppUser(String email) {
        if(appUserRepository.existsByEmail(email)){
            appUserRepository.deleteByEmail(email);
        }
        else{
            throw new AppUserNotFound(email);
        }
    }
}
