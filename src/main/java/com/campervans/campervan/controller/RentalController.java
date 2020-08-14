package com.campervans.campervan.controller;


import com.campervans.campervan.model.Rental;
import com.campervans.campervan.service.RentalServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import datadog.trace.api.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/rental",  produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalController {
    private static Logger logger = LoggerFactory.getLogger(RentalController.class);
    @Autowired
    private RentalServiceImp rentalServiceImplement;

    @GetMapping("/all")
    @Trace
    public List<Rental> getAllRental(Pageable pageable) {
        return rentalServiceImplement.getAllCampervan();
    }

    @GetMapping("/all_price")
    @Trace
    public List<Rental> getAllPriceDecs() {
        return rentalServiceImplement.getAllCampervanOrderByPricePerDayDESC();
    }

    @GetMapping("/price_between")
    @Trace
    public List<Rental> getPriceBetween(@RequestParam double min, double max) {
        return rentalServiceImplement.getPricePerDayBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
    }

    @GetMapping("/get_id")
    @Trace
    public List<Rental> getRentalsId(@RequestParam long id) {

        return rentalServiceImplement.getByCampervanID(id);
    }

    @GetMapping("/get_near")
    @Trace
    public List<Rental> getLocated(@RequestParam double x, double y) {
        return rentalServiceImplement.getLocation(x, y);
    }

    @GetMapping("/page")
    @Trace
    public List<Rental> getPage(@RequestParam int limit, int offset) {
        return rentalServiceImplement.getPage(limit, offset);
    }

    @GetMapping("/ids")
    @Trace
    public List<Rental> getIds(@RequestParam long[] ids) {
        return rentalServiceImplement.getByCampervansIds(ids);
    }

}
