package com.example.demo.core.supportingClasses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchParameterAnalyzer {

    private String nameOfSearchedProduct;
    private Double bottomLineOfPriceInSearchedProduct;
    private Double topLineOfPriceInSearchedProduct;
    private String regEx = "([а-яА-Я]+)?(\\s+)?(\\d+)?(\\s+)?-?(\\s+)?(\\d+)?(\\s+)?([а-яА-Я]+)?";



    public String getNameOfSearchedProduct() {
        return nameOfSearchedProduct;
    }

    public void setNameOfSearchedProduct(String nameOfSearchedProduct) {
        this.nameOfSearchedProduct = nameOfSearchedProduct;
    }

    public Double getBottomLineOfPriceInSearchedProduct() {
        return bottomLineOfPriceInSearchedProduct;
    }

    public void setBottomLineOfPriceInSearchedProduct(Double bottomLineOfPriceInSearchedProduct) {
        this.bottomLineOfPriceInSearchedProduct = bottomLineOfPriceInSearchedProduct;
    }

    public Double getTopLineOfPriceInSearchedProduct() {
        return topLineOfPriceInSearchedProduct;
    }

    public void setTopLineOfPriceInSearchedProduct(Double topLineOfPriceInSearchedProduct) {
        this.topLineOfPriceInSearchedProduct = topLineOfPriceInSearchedProduct;
    }

    public String getRegEx() {
        return regEx;
    }

    public void analyzingSearchParameter(String search) {
        Pattern pattern = Pattern.compile(getRegEx());
        Matcher matcher = pattern.matcher(search);
        matcher.find();
        setNameOfSearchedProduct(matcher.group(1));
        if (getNameOfSearchedProduct() == null) {
            setNameOfSearchedProduct(matcher.group(8));
        }
        try {
            setBottomLineOfPriceInSearchedProduct(Double.parseDouble(matcher.group(3)));
        } catch (Exception exception) {
            setBottomLineOfPriceInSearchedProduct(0.0);
        }
        try {
            setTopLineOfPriceInSearchedProduct(Double.parseDouble(matcher.group(6)));
        } catch (Exception exception) {
            setTopLineOfPriceInSearchedProduct(9999.0);
        }
    }
}
