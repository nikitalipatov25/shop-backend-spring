package com.example.demo.core.services;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;


    class CatalogServiceTest {

        @RepeatedTest(10)
        void testing () {
            String[] cases = {"майка 123-321","123-321 майка","майка","123-321","123"};
            String[] results = {
                    "?search=майка&priceFrom=123&priceTo=321",
                    "?search=майка&priceFrom=123&priceTo=321",
                    "?search=майка&priceFrom=0&priceTo=999999",
                    "?priceFrom=123&priceTo=321",
                    "?priceFrom=123&priceTo=999999"
            };
            int generateRandomNumber = (int) ((Math.random() * (5-0)) + 0);
            System.out.println(cases[generateRandomNumber]);
            String searchParameter = cases[generateRandomNumber];
            String actual = searchAnalysis(searchParameter);
            String expected = results[generateRandomNumber];
            assertEquals(actual, expected);
        }

        String searchAnalysis(String search) {
            String productName;
            String priceFrom;
            String priceTo;
            Pattern pattern = Pattern.compile("([а-яА-Я]+)?(\\s+)?(\\d+)?(\\s+)?-?(\\s+)?(\\d+)?(\\s+)?([а-яА-Я]+)?");
            Matcher matcher = pattern.matcher(search);
            matcher.find();
            productName = matcher.group(1);
            if (productName == null) {
                productName = matcher.group(8);
            }
            priceFrom = matcher.group(3);
            if (priceFrom == null) {
                priceFrom = "0";
            }
            priceTo = matcher.group(6);
            if (priceTo == null) {
                priceTo = "999999";
            }
            String result = "?";
            if (!(productName == null) && (!productName.isEmpty() || !productName.isBlank())) {
                result = result + "search=" + productName;
            }
            if (!(productName == null)) {
                result = result + "&priceFrom=" + priceFrom;
            } else {
                result = result + "priceFrom=" + priceFrom;
            }
            result = result + "&priceTo=" + priceTo;
            return result;
        }
    }