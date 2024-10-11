/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.controller;

import com.igurash.igurashwallet.entity.User;
import com.igurash.igurashwallet.responseandregister.Response;
import com.igurash.igurashwallet.serviceimpl.DonationServiceImpl;
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
@RequestMapping("/iguras")
public class DonationController {
    @Autowired
    DonationServiceImpl dimpl ;
    @PutMapping("/registerDonation")
      public ResponseEntity<Response> updateDonationandcreateWallet(@RequestParam("donationid") String donationId
          
    ){
          Response response = new Response();
           String s = dimpl.updateDonation(donationId);
          response.setMessage(s);
           return new ResponseEntity<>(response ,  HttpStatus.OK);
      }
    
}
