/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.utils;

/**
 *
 * @author dewa
 */
public class Enkripsi {
    public String sha256(String input){
        String data ="";
        data = org.apache.commons.codec.digest.DigestUtils.sha256Hex(input);
        return data;
    }
}
