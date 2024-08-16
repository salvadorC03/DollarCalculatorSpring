/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salvador.dollarcalculator.controller;

import com.salvador.dollarcalculator.service.ConvertService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author salvador
 */
@Controller
@RequiredArgsConstructor
public class ConvertController {

    private final ConvertService convertService;

    @GetMapping("/ConvertBsToUsd")
    public ModelAndView convertBsToUsd(@RequestParam String rate, @RequestParam String amount) throws IOException, InterruptedException {
        Float rateF, amountF;

        try {
            rateF = Float.valueOf(rate);
            amountF = Float.valueOf(amount);
            
            if (rateF <= 0 || amountF <= 0) throw new NumberFormatException("Invalid params");
        } catch (NumberFormatException e) {
            throw e;
        }

        var result = convertService.convertBsToUsd(Float.valueOf(rate), Float.valueOf(amount));
        
        var inputText = amountF + (amountF > 1.0 ? " Bolívares" : " Bolívar");
        var resultText = result + (result > 1.0 ? " Dólares" : " Dólar");

        return new ModelAndView("convert")
                .addObject("input", inputText)
                .addObject("result", resultText);
    }

    @GetMapping("/ConvertUsdToBs")
    public ModelAndView convertUsdToBs(@RequestParam String rate, @RequestParam String amount) throws IOException, InterruptedException {
        Float rateF, amountF;

        try {
            rateF = Float.valueOf(rate);
            amountF = Float.valueOf(amount);
            
            if (rateF <= 0 || amountF <= 0) throw new NumberFormatException("Invalid params");
        } catch (NumberFormatException e) {
            throw e;
        }

        var result = convertService.convertUsdToBs(Float.valueOf(rate), Float.valueOf(amount));
        
        var inputText = amountF + (amountF > 1.0 ? " Dólares" : " Dólar");
        var resultText = result + (result > 1.0 ? " Bolívares" : " Bolívar");

        return new ModelAndView("convert")
                .addObject("input", inputText)
                .addObject("result", resultText);
    }
}
