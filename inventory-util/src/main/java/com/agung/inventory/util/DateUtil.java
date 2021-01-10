/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author agung
 */
public class DateUtil {
    
    public static LocalDateTime toLocalDateTime(Date date){
       return Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }
    
    public static Date toDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static DateTimeFormatter formatDate(LocalDateTime date){
        return DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    }

    public static Date getDate(String date){
        return Date.from(LocalDate.parse(date).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date initStartDate(){
        return toDate(LocalDateTime.now());
    }

    public static Date initEndDate(){
        return toDate(LocalDateTime.now().plusDays(6));
    }
}
