package pl.tkaras.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tkaras.api.documents.AppUser;
import pl.tkaras.api.dto.AppUserDto;
import pl.tkaras.services.impl.AppUserService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/appUser")
@RestController
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/all")
    public ResponseEntity<List<AppUserDto>> fetchAllAppUsers(){
        return new ResponseEntity<>(appUserService.getAllAppUsers(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<AppUserDto> fetchAppUser(@RequestParam String email){
        return new ResponseEntity<>(appUserService.getAppUser(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppUserDto> registerAppUser(@RequestBody AppUserDto appUserDto){
        return new ResponseEntity<>(appUserService.addAppUser(appUserDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AppUserDto> updateAppUser(@RequestParam String email ,@RequestBody AppUserDto appUserDto){
        return new ResponseEntity<>(appUserService.updateAppUser(email, appUserDto), HttpStatus.OK);
    }

    @DeleteMapping
    public void updateAppUser(@RequestParam String email){
        appUserService.deleteAppUser(email);
    }

}
