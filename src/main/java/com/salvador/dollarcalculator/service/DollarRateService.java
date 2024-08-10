/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salvador.dollarcalculator.service;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.salvador.dollarcalculator.entity.DollarRate;
import com.salvador.dollarcalculator.entity.RateSource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;

/**
 *
 * @author salvador
 */
@NoArgsConstructor
public class DollarRateService {

    public Map<RateSource, DollarRate> getDollarRates() throws IOException, InterruptedException {
        var uri = URI.create("https://dolartoday.com/wp-admin/admin-ajax.php");
        var action = "dt_currency_calculator_handler";
        var amount = "1";

        var body = MultipartBodyPublisher
                .newBuilder()
                .textPart("action", action)
                .textPart("amount", amount)
                .build();

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(uri).POST(body).build();
        
        var response = client.send(request, null);
        var data = response.body();
        
        client.close();

        var bcvDollarRate = new DollarRate(36.60, LocalDate.now(), LocalTime.now(), RateSource.Bcv);
        var parallelDollarRate = new DollarRate(40.10, LocalDate.now(), LocalTime.now(), RateSource.Parallel);

        var map = new HashMap<RateSource, DollarRate>();
        map.put(RateSource.Bcv, bcvDollarRate);
        map.put(RateSource.Parallel, parallelDollarRate);

        return map;
    }
}
