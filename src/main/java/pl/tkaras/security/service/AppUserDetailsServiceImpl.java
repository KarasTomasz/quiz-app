package pl.tkaras.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.tkaras.exceptions.impl.AppUserNotFound;
import pl.tkaras.models.documents.AppUser;
import pl.tkaras.respositories.AppUserRepository;

@RequiredArgsConstructor
@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new AppUserNotFound(email));

        return new AppUserDetailsImpl(appUser);
    }
}