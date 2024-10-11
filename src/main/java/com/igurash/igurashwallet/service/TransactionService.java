/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igurash.igurashwallet.service;

import com.igurash.igurashwallet.responseandregister.TransactionRequest;

/**
 *
 * @author User
 */
public interface TransactionService {
    public String getTransaction(TransactionRequest request,String token,String pinPassword,String deviceId);
      public String getServiceTransaction(TransactionRequest request,String token,String pinPassword,String deviceId,String prdId,String serviceType);
      public String getServiceStatus(String token,String prdId);
}
