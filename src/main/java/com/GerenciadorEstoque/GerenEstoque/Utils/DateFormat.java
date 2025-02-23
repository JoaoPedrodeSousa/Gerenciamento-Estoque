package com.GerenciadorEstoque.GerenEstoque.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

    public static Date formatter(String date) throws ParseException {
        return sdf.parse(date);
    }

    public static Date getInstance(){
        return new Date();
    }
}
