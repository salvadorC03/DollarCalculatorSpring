/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salvador.dollarcalculator.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author salvador
 */
@AllArgsConstructor
@Data
public class DollarRate {
    private final Double price;
    private final LocalDate date;
    private final LocalTime time;
    private final String source;
}
