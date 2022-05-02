package pl.tkaras.security.jwt;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthenticationRequest {

    private String email;

    private String password;

}