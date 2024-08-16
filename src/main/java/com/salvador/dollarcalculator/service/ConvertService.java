/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salvador.dollarcalculator.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author salvador
 */
@Service
public class ConvertService {
    
    public Float convertBsToUsd(Float rate, Float amount) {
        return amount / rate;
    }
    
    public Float convertUsdToBs(Float rate, Float amount) {
        return amount * rate;
    }
}
