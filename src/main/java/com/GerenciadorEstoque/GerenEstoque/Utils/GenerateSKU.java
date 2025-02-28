package com.GerenciadorEstoque.GerenEstoque.Utils;

import java.util.Random;

public class GenerateSKU {
    private static final Random random = new Random();


    private static String checkNumber(Integer number){
        String numberText = String.valueOf(number);
        StringBuilder stringBuilder = new StringBuilder();

        if(number < 10){
            stringBuilder.append("00").append(numberText);
        } else if (number < 100) {
            stringBuilder.append(0).append(numberText);
        } else {
            stringBuilder.append(numberText);
        }

        return stringBuilder.toString();
    }

    public static String setSKU(String name){
        Integer number = random.nextInt(10000);

        StringBuilder stringBuilder = new StringBuilder();
        String nameCode = name.substring(0,3).toUpperCase();

        stringBuilder.append(nameCode)
                    .append("-")
                    .append(checkNumber(number));

        return stringBuilder.toString();

    }

}
