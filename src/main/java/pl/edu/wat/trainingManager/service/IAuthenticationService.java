package pl.edu.wat.trainingManager.service;

import pl.edu.wat.trainingManager.security.JwtAuthenticationRequest;
import pl.edu.wat.trainingManager.security.JwtAuthenticationResponse;

public interface IAuthenticationService {

    void authenticate(JwtAuthenticationRequest authenticationRequest);
    JwtAuthenticationResponse generateToken(JwtAuthenticationRequest authenticationRequest);
}
