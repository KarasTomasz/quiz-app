package pl.tkaras.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tkaras.api.documents.AppUser;
import pl.tkaras.api.dto.AppUserDTO;
import pl.tkaras.api.mappers.impl.AppUserMapper;
import pl.tkaras.api.services.impl.AppUserService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/appUser")
@RestController
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

    @GetMapping("/all")
    public ResponseEntity<List<AppUserDTO>> fetchAllAppUsers(){
        List<AppUser> appUsers = appUserService.getAllAppUsers();
        return new ResponseEntity<>(appUserMapper.mapToDtos(appUsers), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<AppUserDTO> fetchAppUser(@RequestParam("email") String email){
        AppUser appUser = appUserService.getAppUser(email);
        return new ResponseEntity<>(appUserMapper.mapToDto(appUser), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppUserDTO> registerAppUser(@RequestBody AppUserDTO appUserDto){
        AppUser appUser = appUserMapper.mapToDocument(appUserDto);
        AppUser returnedAppUser = appUserService.addAppUser(appUser);
        return new ResponseEntity<>(appUserMapper.mapToDto(returnedAppUser), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AppUserDTO> updateAppUser(@RequestParam("email") String email , @RequestBody AppUserDTO appUserDto){
        AppUser appUser = appUserMapper.mapToDocument(appUserDto);
        AppUser returnedAppUser = appUserService.updateAppUser(email, appUser);
        return new ResponseEntity<>(appUserMapper.mapToDto(returnedAppUser), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity updateAppUser(@RequestParam("email") String email){
        appUserService.deleteAppUser(email);
        return ResponseEntity.ok().build();
    }

}
