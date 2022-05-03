package pl.tkaras.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tkaras.services.impl.AuthService;

@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestParam String email, @RequestParam String password){
        String token =  authService.setIn(email, password);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}