package pl.tkaras.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tkaras.models.documents.AppUser;
import pl.tkaras.models.documents.AppUserRole;
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

    private final PasswordEncoder passwordEncoder;

    public List<AppUser> getAllAppUsers(){
        return appUserRepository.findAll();
    }

    public AppUser getAppUser(String email){
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFound(this.getClass().getSimpleName(), email));
    }

    public AppUser addAppUser(AppUser appUser) {
        if(!appUserRepository.existsByEmail(appUser.getEmail())){
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            appUser.setRole(AppUserRole.USER);
            appUser.setScore(0L);
            return appUserRepository.save(appUser);
        }
        else{
            throw new AppUserAlreadyExist(this.getClass().getSimpleName(), appUser.getEmail());
        }
    }

    public AppUser updateAppUser(String email, AppUser appUser) {
        AppUser foundAppUser = appUserRepository.findByEmail(email)
                .orElseThrow((() -> new AppUserNotFound(this.getClass().getSimpleName(), email)));

        foundAppUser.setFirstName(appUser.getFirstName());
        foundAppUser.setLastName(appUser.getLastName());
        foundAppUser.setPassword(appUser.getPassword());

        return appUserRepository.save(foundAppUser);
    }

    public void deleteAppUser(String email) {
        if(appUserRepository.existsByEmail(email)){
            appUserRepository.deleteByEmail(email);
        }
        else{
            throw new AppUserNotFound(this.getClass().getSimpleName(), email);
        }
    }
}
