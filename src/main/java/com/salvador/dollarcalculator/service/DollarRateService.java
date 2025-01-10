/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salvador.dollarcalculator.service;

import com.salvador.dollarcalculator.model.DollarExchange;
import com.salvador.dollarcalculator.model.ExchangeSource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

/**
 *
 * @author salvador
 */
@Service
public class DollarRateService {

    public final Map getDollarRates() throws IOException {
        try {
            // Use Web Scraping to retrieve dollar rate from the internet
            var webUrl = "https://www.dolarvenezuela.com/";
            var doc = Jsoup.connect(webUrl).get();
            var element = doc.getElementsByTag("tbody").get(0).children();

            var bcvText = element.get(0).children().get(1).text();
            var parallelText = element.get(element.size() - 1).children().get(1).text();

            var bcvPrice = Double.parseDouble(bcvText.split(" ")[1]);
            var parallelPrice = Double.parseDouble(parallelText.split(" ")[1]);

            var map = new HashMap<ExchangeSource, DollarExchange>();
            map.put(ExchangeSource.Bcv, new DollarExchange(bcvPrice, LocalDate.now(), LocalTime.now(), ExchangeSource.Bcv));
            map.put(ExchangeSource.Parallel, new DollarExchange(parallelPrice, LocalDate.now(), LocalTime.now(), ExchangeSource.Parallel));

            return map;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(java.util.Arrays.toString(e.getStackTrace()));
        }
        return new EnumMap(ExchangeSource.class);
    }
}
