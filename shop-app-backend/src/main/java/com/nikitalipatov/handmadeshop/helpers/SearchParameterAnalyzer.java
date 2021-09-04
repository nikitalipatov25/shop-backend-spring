package com.nikitalipatov.handmadeshop.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchParameterAnalyzer {

    private String productName;
    private Double startPrice;
    private Double endPrice;
    private String regExOld = "([а-яА-Я]+)?(\\s+)?(\\d+)?(\\s+)?-?(\\s+)?(\\d+)?(\\s+)?([а-яА-Я]+)?";
    private String regEx = "([а-яА-Я]+)?([0-9]+)-([0-9]+)";

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    public String getRegEx() {
        return regEx;
    }

    public void analyzingSearchParameter(String search) {
        Pattern pattern = Pattern.compile(getRegEx());
        Matcher matcher = pattern.matcher(search);
        matcher.find();
        setProductName(matcher.group(1));
        try {
            setStartPrice(Double.parseDouble(matcher.group(2)));
        } catch (Exception exception) {
            setStartPrice(0.0);
        }
        try {
            setEndPrice(Double.parseDouble(matcher.group(3)));
        } catch (Exception exception) {
            setEndPrice(999999.0);
        }
    }
}
