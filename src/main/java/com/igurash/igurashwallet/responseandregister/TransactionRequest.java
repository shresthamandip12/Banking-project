/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.responseandregister;

import java.util.List;

/**
 *
 * @author User
 */
public class TransactionRequest {
    
      private String transactionType;
  
    private double amount ; 
       //this is username for P2P/p2s and Donation post id For P2D
    private String  receiver_ID;
    List<MultiTransactionRequest> senderList;

    public List<MultiTransactionRequest> getSenderList() {
        return senderList;
    }

    public void setSenderList(List<MultiTransactionRequest> senderList) {
        this.senderList = senderList;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReceiver_ID() {
        return receiver_ID;
    }

    public void setReceiver_ID(String receiver_ID) {
        this.receiver_ID = receiver_ID;
    }


    
    
}
