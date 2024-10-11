/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



/**
 *
 * @author User
 */
@Entity
@Table(name = "\"User\"")
public class User {
    @Id
    @Column(name = "id",columnDefinition = "text")
   private String id ; 
    @Column(name = "email",columnDefinition = "text")
   private String email;
    @Column(name = "username",columnDefinition = "text")
   private String username;
    @Column(name = "password",columnDefinition = "text")
   private String password;
   
     @Column(name = "\"emailVerified\"")
      private boolean emailVerified;
     
     @Column(name = "wallet_role")
   private String role;
     @Column(name = "wallet_pin")
     private String walletPin;
     
     @Column(name = "walletID")
private long walletId;
@Column(name = "\"kycId\"",columnDefinition = "text")
private String kycId;
@Column(name =  "\"pinPassword\"" ,columnDefinition = "text")
private String pinPassword;

    public String getWalletPin() {
        return walletPin;
    }

    public void setWalletPin(String walletPin) {
        this.walletPin = walletPin;
    }

    public String getPinPassword() {
        return pinPassword;
    }

    public void setPinPassword(String pinPassword) {
        this.pinPassword = pinPassword;
    }


    public String getKycId() {
        return kycId;
    }

    public void setKycId(String kycId) {
        this.kycId = kycId;
    }

 

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }
     
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public User() {
    }

    public User(String id, String email, String username, String password, boolean emailVerified, String role, long walletId, String kycId, String pinPassword) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.emailVerified = emailVerified;
        this.role = role;
        this.walletId = walletId;
        this.kycId = kycId;
        this.pinPassword = pinPassword;
    }
   
    
    
}
