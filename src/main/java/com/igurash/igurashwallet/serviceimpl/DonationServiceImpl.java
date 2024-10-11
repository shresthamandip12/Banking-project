/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.serviceimpl;

import com.igurash.igurashwallet.entity.Donation;
import com.igurash.igurashwallet.entity.Wallet;
import com.igurash.igurashwallet.repository.DonationRepositoy;
import com.igurash.igurashwallet.repository.WalletRepository;
import com.igurash.igurashwallet.service.DonationService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class DonationServiceImpl implements DonationService{
    @Autowired
    private DonationRepositoy drepo;
    @Autowired
     private WalletRepository walletrepo;
    @Override
    public String updateDonation(String id) {
        Optional<Donation> existdonation = drepo.findById(id);
        if(existdonation.isPresent()){
                Wallet newWallet = new Wallet();
               newWallet.setBalance(0);
               newWallet.setUserID(existdonation.get().getId());
                Wallet  u =     walletrepo.save(newWallet);
                if( u != null){
                    Donation existing = existdonation.get();
                    existing.setWallet_id(newWallet.getWalletId());
                    Donation newDonation = drepo.save(existing);
                    if(newDonation != null){
                        return "Successfull Donation Box ";
                    }else{
                        return "Donation Box Creation Fail";
                    }
                }else{
                    return "Donation Box Creation Fail";
                }
            
        }else{
           return "Donation doesn't exist"; 
        }
        
        }
    
}
