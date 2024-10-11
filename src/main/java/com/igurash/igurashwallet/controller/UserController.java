/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.controller;

import com.igurash.igurashwallet.entity.User;
import com.igurash.igurashwallet.repository.UserRepository;
import com.igurash.igurashwallet.responseandregister.Response;
import com.igurash.igurashwallet.serviceimpl.UserServiceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/iguras/authenticate")
public class UserController {
    @Autowired
    private UserRepository userepo;
      @Autowired
    UserServiceImpl serviceimpl;
    
     @PutMapping("/registerwallet")
      public ResponseEntity<Response> updateUserandcreateWallet(@RequestParam("username") String username,
              @RequestParam("pinPassword") String pwd
    ){
          Response response = new Response();
                  Optional<User> theuser = userepo.findByUsername(username);
          if(theuser.isPresent()){
               if(theuser.get().getPinPassword().equals(pwd) && 
                       theuser.get().isEmailVerified() == true &&
                       theuser.get().getKycId() !=null &&
                       theuser.get().getRole() == null
                     ){
                   
                   String s = serviceimpl.updateUser(username);
                    response.setMessage(s);
        }
           else{
             
               response.setMessage("User is not Verified ");
               if(theuser.get().getRole() != null){
                   
                   response.setMessage("User is already Registered");
                   
               }else{
                   response.setMessage("User is not Verified ");
               }
              
           }
       }else
       {
           response.setMessage("User is not present");
       }
              
          
           return new ResponseEntity<>(response ,  HttpStatus.OK);
      }
      @PutMapping("/registermerchant")
        public ResponseEntity<Response> updateUserWalletRole(@RequestParam("username") String username
    ){
             Response response = new Response();
             String s = serviceimpl.updateRole(username);
             response.setMessage(s);
               return new ResponseEntity<>(response ,  HttpStatus.OK);
            
        }
    
}
