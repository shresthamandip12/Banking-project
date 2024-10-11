/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.controller;

import com.igurash.igurashwallet.responseandregister.Response;
import com.igurash.igurashwallet.responseandregister.TransactionRequest;
import com.igurash.igurashwallet.service.JwtService;
import com.igurash.igurashwallet.serviceimpl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/iguras/walletuser")
public class TransactionController {
    @Autowired
    private  TransactionServiceImpl transacimpl;
    
     @Autowired
   JwtService jwtrepo;
     @PutMapping(path = "/transaction", consumes = {"application/json"})
     public ResponseEntity<Response> dotransaction(@RequestBody TransactionRequest request,
             @RequestParam("deviceId") String deviceId ,
             @RequestParam("pinPassword") String pwd,
             @RequestHeader(value = "Authorization") String authorization){
         Response response = new Response();
       
          String token = authorization.replaceAll("Bearer", " ").trim();
          if(jwtrepo.isTokenExpired(token)== false){
              
          String message = transacimpl.getTransaction(request, token, pwd,deviceId);
          response.setMessage(message);
          }else{
            response.setMessage("Login Session Expired");
          }
      return new ResponseEntity<>(response ,  HttpStatus.OK);
     }
          @PutMapping(path = "/donationtransaction", consumes = {"application/json"})
     public ResponseEntity<Response> dodonationtransaction(@RequestBody TransactionRequest request,
             @RequestParam("deviceId") String deviceId ,
             @RequestParam("pinPassword") String pwd,
             @RequestHeader(value = "Authorization") String authorization){
         Response response = new Response();
       
          String token = authorization.replaceAll("Bearer", " ").trim();
          if(jwtrepo.isTokenExpired(token)== false){
              
          String message = transacimpl.getDonationTransaction(request, token, pwd,deviceId);
          response.setMessage(message);
          }else{
            response.setMessage("Login Session Expired");
          }
      return new ResponseEntity<>(response ,  HttpStatus.OK);
     }
     
      @PutMapping(path = "/servicetransaction", consumes = {"application/json"})
     public ResponseEntity<Response> doServicetransaction(@RequestBody TransactionRequest request,
             @RequestParam("deviceId") String deviceId ,
             @RequestParam("pinPassword") String pwd,
              @RequestParam("productId") String productId,
              @RequestParam("serviceType") String serviceType,
             @RequestHeader(value = "Authorization") String authorization){
         Response response = new Response();
       
          String token = authorization.replaceAll("Bearer", " ").trim();
          if(jwtrepo.isTokenExpired(token)== false){
              
          String message = transacimpl.getServiceTransaction(request, token, pwd,deviceId,productId,serviceType);
          response.setMessage(message);
          }else{
            response.setMessage("Login Session Expired");
          }
      return new ResponseEntity<>(response ,  HttpStatus.OK);
     }
         @GetMapping(value = "/checkusersubscription/{productId}" )
         public ResponseEntity<Response> doServicetransaction( @PathVariable(value = "productId") String productId,
                  @RequestHeader(value = "Authorization") String authorization){
             Response response = new Response();
               String token = authorization.replaceAll("Bearer", " ").trim();
          if(jwtrepo.isTokenExpired(token)== false){
              
          String message = transacimpl.getServiceStatus(token, productId);
                  response.setMessage(message);
          }else{
            response.setMessage("Login Session Expired");
          }
             
             
             return new ResponseEntity<>(response ,  HttpStatus.OK);
         }
}
