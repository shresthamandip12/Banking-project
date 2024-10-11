/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.serviceimpl;

import com.igurash.igurashwallet.entity.User;
import com.igurash.igurashwallet.entity.Wallet;
import com.igurash.igurashwallet.repository.UserRepository;
import com.igurash.igurashwallet.repository.WalletRepository;
import com.igurash.igurashwallet.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class UserServiceImpl implements UserService {
 @Autowired
    private UserRepository repo;
 @Autowired
     private PasswordEncoder passwordEncoder;
      @Autowired
     private WalletRepository walletrepo;
    @Override
    public String updateUser(String id) {
       Optional<User> theuser = repo.findByUsername(id);
           if (theuser.isPresent()) {
               Wallet newWallet = new Wallet();
               newWallet.setBalance(0);
               newWallet.setUserID(theuser.get().getId());
           
        
            Wallet  u =     walletrepo.save(newWallet);
            if(u !=null){
        User existing = theuser.get();
             existing.setRole("wallet_user");
             existing.setWalletId(u.getWalletId());
             existing.setWalletPin(passwordEncoder.encode(existing.getPinPassword()));
             User newUser = repo.save(existing);
             if(newUser !=null){
                    return "User Register  success";
             }else{
                 return "User Register Fail";
             }
      
          }else{
              return "Update Failed ";}
          
           }else{
                 return "Failed to find User";
           }
    }
    
    @Override
    public String updateRole(String id){
         Optional<User> theuser = repo.findByUsername(id);
         if(theuser.isPresent()){
             if(theuser.get().getWalletId() !=0 && "wallet_user".equals(theuser.get().getRole())){
                  User existing = theuser.get();
                 existing.setRole("wallet_merchant");
                 User newupdated = repo.save(existing);
                  if(newupdated !=null){
                    return "User Role upgrade  success";
             }else{
                 return "User Role upgrade fail Fail";
             }
                 
             }else{
                 return "User is not Wallet User";
             }
         }else{
             return "User is not Present";
         }
    }
    
}
