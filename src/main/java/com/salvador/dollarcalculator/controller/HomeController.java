/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salvador.dollarcalculator.controller;

import com.salvador.dollarcalculator.model.ExchangeSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.salvador.dollarcalculator.service.DollarRateService;
import java.io.IOException;

/**
 *
 * @author salvador
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DollarRateService dollarRateService;

    @GetMapping("/")
    public ModelAndView index() throws IOException, InterruptedException {
        var dollarRates = dollarRateService.getDollarRates();

        return new ModelAndView("index")
                .addObject(ExchangeSource.Bcv.name(), dollarRates.get(ExchangeSource.Bcv))
                .addObject(ExchangeSource.Parallel.name(), dollarRates.get(ExchangeSource.Parallel));
    }
}
