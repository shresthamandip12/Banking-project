/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.controller;

import com.igurash.igurashwallet.entity.User;
import com.igurash.igurashwallet.repository.UserRepository;
import com.igurash.igurashwallet.responseandregister.LoginRequest;
import com.igurash.igurashwallet.responseandregister.Response;
import com.igurash.igurashwallet.service.JwtService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/iguras/authenticate")
public class JwtController {
     @Autowired
    private JwtService jwtservice ; 
    @Autowired
     private  AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userepo;
    
    @PostMapping(path = "/login", consumes = {"application/json"})
    public ResponseEntity<Response> loginuser(@RequestBody LoginRequest request){
        System.out.print("entered login");
        Optional<User> theuser = userepo.findByUsername(request.getUsername());
        Response response = new Response() ;
         
       if(theuser.isPresent() ){
           System.out.println(theuser.get().getUsername());
           if(theuser.get().isEmailVerified() == true && theuser.get().getRole() != null){
         Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        
      
             if (authentication.isAuthenticated()){
                    System.out.println("inside if authentication");
           response.setToken(jwtservice.generateToken(theuser.get().getUsername()));
        response.setMessage("Login Successfull");
        }
        }
           else{
             
               response.setMessage("User is not Verified ");
              
           }
       }else
       {
           response.setMessage("User is not present");
       }
        
        
        return new ResponseEntity<>(response ,  HttpStatus.OK);
    }

}
