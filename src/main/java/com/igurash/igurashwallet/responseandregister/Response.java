/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.responseandregister;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author User
 */
public class Response {
     @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token ; 
    private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response() {
    }

    public Response(String token, String message) {
        this.token = token;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" + "token=" + token + ", message=" + message + '}';
    }
}
