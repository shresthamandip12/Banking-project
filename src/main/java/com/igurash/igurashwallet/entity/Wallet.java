/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name="wallet_table")
public class Wallet {
        @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wallet_Id")
    private long walletId ; 
     @Column(name = "account_balance")
    private double balance; 
 @Column(name = "user_id")
    private String userID; 

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Wallet() {
    }

    public Wallet(Wallet wallet) {
        this.walletId = wallet.getWalletId();
        this.balance = wallet.getBalance();
        this.userID = wallet.getUserID();
    }

    public Wallet(long walletId, double balance, String userID) {
        this.walletId = walletId;
        this.balance = balance;
        this.userID = userID;
    }
    
    
    
}
