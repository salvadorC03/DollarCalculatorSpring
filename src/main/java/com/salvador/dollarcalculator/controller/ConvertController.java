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

    private ModelAndView convertView(Float amount, Float result) {
        var inputText = amount + (amount > 1.0 ? " Bolívares" : " Bolívar");
        var resultText = result + (result > 1.0 ? " Dólares" : " Dólar");

        return new ModelAndView("convert")
                .addObject("input", inputText)
                .addObject("result", resultText);
    }

    private Float[] parseRateAndAmount(String rate, String amount) throws NumberFormatException {
        Float rateF, amountF;

        try {
            rateF = Float.valueOf(rate);
            amountF = Float.valueOf(amount);

            if (rateF <= 0 || amountF <= 0) {
                throw new NumberFormatException("Rate and amount must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            throw e;
        }

        return new Float[]{rateF, amountF};
    }

    @GetMapping("/ConvertBsToUsd")
    public ModelAndView convertBsToUsd(@RequestParam String rate, @RequestParam String amount) throws IOException, InterruptedException {
        var parsedParams = parseRateAndAmount(rate, amount);

        var rateF = parsedParams[0];
        var amountF = parsedParams[1];

        var result = convertService.convertBsToUsd(rateF, amountF);

        return convertView(amountF, result);
    }

    @GetMapping("/ConvertUsdToBs")
    public ModelAndView convertUsdToBs(@RequestParam String rate, @RequestParam String amount) throws IOException, InterruptedException {
        var parsedParams = parseRateAndAmount(rate, amount);

        var rateF = parsedParams[0];
        var amountF = parsedParams[1];

        var result = convertService.convertUsdToBs(rateF, amountF);

        return convertView(amountF, result);
    }
}
