package com.kata.bank.kataBank.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormatter {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

    public static final String format(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }
}
