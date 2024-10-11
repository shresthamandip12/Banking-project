/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igurash.igurashwallet.service;

import com.igurash.igurashwallet.entity.Wallet;
import java.util.List;

/**
 *
 * @author User
 */
public interface WalletService {
    String add(Wallet wallet);
    Wallet getWalletbyuser(String username);
    
}
