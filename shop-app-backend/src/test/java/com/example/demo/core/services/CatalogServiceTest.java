package com.example.demo.core.services;

import com.example.demo.core.supportingClasses.SearchParameterAnalyzer;
import com.example.demo.core.repos.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CatalogServiceTest {

    @Autowired
    CatalogService catalogService;

    @Autowired
    CatalogRepository catalogRepository;

    Pageable pageable;

    SearchParameterAnalyzer searchParameterAnalyzer = new SearchParameterAnalyzer();

    String[] cases = {
        "товар 123-321",
        "123-321 товар",
        "товар",
        "123-321",
        "123"
    };
    String[][] expected = {
        {"товар", "123.0", "321.0"},
        {"товар", "123.0", "321.0"},
        {"товар", "0.0", "9999.0"},
        {"empty", "123.0", "321.0"},
        {"empty", "123.0", "9999.0"}
    };

    @Test
    public void testingSearchParameterAnalyzer() {
        for (int i=0; i<cases.length; i++) {
            searchParameterAnalyzer.analyzingSearchParameter(cases[i]);
                if (searchParameterAnalyzer.getNameOfSearchedProduct() == null) {
                    searchParameterAnalyzer.setNameOfSearchedProduct("empty");
                }
            assertEquals(expected[i][0], searchParameterAnalyzer.getNameOfSearchedProduct(),
            "Should contain productname");
            assertEquals(expected[i][1], searchParameterAnalyzer.getBottomLineOfPriceInSearchedProduct().toString(),
            "Should contain firstprice");
            assertEquals(expected[i][2], searchParameterAnalyzer.getTopLineOfPriceInSearchedProduct().toString(),
            "Should contain secondprice");
        }
    }
String category = "ddfdfd";
    String[] check;
    @Test
    public void testing() {
        String search = "товар";
        try {
            var rez = catalogService.listAll(search, category,check, pageable);
            assertAll(
            () -> assertEquals("майка", rez.getContent().get(0).getProductName()),
            () -> assertEquals(200, rez.getContent().get(0).getProductPrice())
            );
        } catch (Exception ex) {
            System.out.println("Search parameter is empty, showing all catalog");
            catalogRepository.findAll();
        }
    }
}