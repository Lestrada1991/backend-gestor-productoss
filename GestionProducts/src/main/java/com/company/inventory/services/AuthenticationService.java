package com.company.inventory.services;

import com.company.inventory.respnose.JwtAuthenticationResponse;
import com.company.inventory.respnose.request.SignUpRequest;
import com.company.inventory.respnose.request.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
    
   
}
