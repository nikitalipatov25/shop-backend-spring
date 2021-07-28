package com.nikitalipatov.handmadeshop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequestMapping(value = "/sale-report")
public class SaleReportController {

}
