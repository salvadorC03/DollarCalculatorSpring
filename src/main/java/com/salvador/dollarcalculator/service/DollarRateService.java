/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salvador.dollarcalculator.service;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.salvador.dollarcalculator.model.DollarExchange;
import com.salvador.dollarcalculator.model.ExchangeSource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

/**
 *
 * @author salvador
 */
@Service
public class DollarRateService {

    public Map<ExchangeSource, DollarExchange> getDollarRates() throws IOException, InterruptedException {
        try {
            var webClient = new WebClient(BrowserVersion.CHROME);
            var webUrl = "https://www.bcv.org.ve/";

            var page = webClient.getPage(webUrl);
            page.initialize();

            var htmlPage = new HtmlPage(page.getWebResponse(), page.getEnclosingWindow());
            
            System.out.println(htmlPage.asNormalizedText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(java.util.Arrays.toString(e.getStackTrace()));
        }

        var bcvDollarExchange = new DollarExchange(36.60, LocalDate.now(), LocalTime.now(), ExchangeSource.Bcv);
        var parallelDollarExchange = new DollarExchange(40.10, LocalDate.now(), LocalTime.now(), ExchangeSource.Parallel);

        var map = new HashMap<ExchangeSource, DollarExchange>();
        map.put(ExchangeSource.Bcv, bcvDollarExchange);
        map.put(ExchangeSource.Parallel, parallelDollarExchange);

        return map;
    }
}
