package pl.tkaras.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.tkaras.models.documents.AppUser;
import pl.tkaras.models.dto.AppUserDTO;
import pl.tkaras.models.mappers.impl.AppUserMapper;
import pl.tkaras.services.impl.AppUserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RequestMapping("api/v1/appUser")
@RestController
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<AppUserDTO>> fetchAllAppUsers(){
        List<AppUser> appUsers = appUserService.getAllAppUsers();
        return new ResponseEntity<>(appUserMapper.mapToDtos(appUsers), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<AppUserDTO> fetchAppUser(@RequestParam("email") String email){
        AppUser appUser = appUserService.getAppUser(email);
        return new ResponseEntity<>(appUserMapper.mapToDto(appUser), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<AppUserDTO> registerAppUser(@RequestBody AppUserDTO appUserDto){
        AppUser appUser = appUserMapper.mapToDocument(appUserDto);
        AppUser returnedAppUser = appUserService.addAppUser(appUser);
        return new ResponseEntity<>(appUserMapper.mapToDto(returnedAppUser), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<AppUserDTO> updateAppUser(@RequestParam("email") String email , @RequestBody AppUserDTO appUserDto){
        AppUser appUser = appUserMapper.mapToDocument(appUserDto);
        AppUser returnedAppUser = appUserService.updateAppUser(email, appUser);
        return new ResponseEntity<>(appUserMapper.mapToDto(returnedAppUser), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<?> deleteAppUser(@RequestParam("email") String email){
        appUserService.deleteAppUser(email);
        return ResponseEntity.ok().build();
    }

}
