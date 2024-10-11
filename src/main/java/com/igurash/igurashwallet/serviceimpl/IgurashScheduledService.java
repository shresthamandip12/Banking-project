/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.serviceimpl;

import com.igurash.igurashwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class IgurashScheduledService {
    @Autowired 
    TransactionServiceImpl ts;
    @Scheduled(fixedRate =24*60*60 * 1000) // 24 hours in milliseconds
    public void runJpaFunction() {
      String s =   ts.getAutomatedTransactionService();
        System.out.println("hello");// Your JPA
        System.out.println(s);
    }
    
        @Scheduled(fixedRate =24*60*60 * 1000) // 24 hours in milliseconds
    public void runDonationAutomatedFunction() {
      String s =   ts.getAutomatedDonationTransactionService();
        System.out.println("hello");// Your JPA
        System.out.println(s);
    }
}
