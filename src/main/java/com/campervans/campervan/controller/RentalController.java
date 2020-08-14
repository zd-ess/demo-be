package com.campervans.campervan.controller;


import com.campervans.campervan.model.RentalEntity;
import com.campervans.campervan.repository.RentalRepository;
import com.campervans.campervan.service.RentalServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import datadog.trace.api.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/campervans",  produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalController {
    private static Logger logger = LoggerFactory.getLogger(RentalController.class);
    @Autowired
    private RentalServiceImp rentalServiceImp;
    private final RentalRepository rentalRepository;

    RentalController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @GetMapping(value = "")
    List<RentalEntity> all() {
        return (List<RentalEntity>) rentalRepository.findAll();
    }

    //TODO
//    - `campervans`
//    - `campervans?price[min]=9000&price[max]=75000`
//    - `campervans?page[limit]=3&page[offset]=6`
//    - `campervans?ids=2000,51155,54318`
//    - `campervans?near=33.64,-117.93` // within 100 miles
//    - `campervans?sort=price`
//    - `campervans/<CAMPER_VAN_ID>`

//    @GetMapping("/all")
//    @Trace
//    public List<RentalEntity> getAllRental(Pageable pageable) {
//        return rentalServiceImp.getAllCampervan();
//    }

    @GetMapping("/all_price")
    @Trace
    public List<RentalEntity> getAllPriceDecs() {
        return rentalServiceImp.getAllCampervanOrderByPricePerDayDESC();
    }

    @GetMapping("/price_between")
    @Trace
    public List<RentalEntity> getPriceBetween(@RequestParam double min, double max) {
        return rentalServiceImp.getPricePerDayBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
    }

    @GetMapping("/get_id")
    @Trace
    public List<RentalEntity> getRentalsId(@RequestParam long id) {

        return rentalServiceImp.getByCampervanID(id);
    }

    @GetMapping("/get_near")
    @Trace
    public List<RentalEntity> getLocated(@RequestParam double x, double y) {
        return rentalServiceImp.getLocation(x, y);
    }

    @GetMapping("/page")
    @Trace
    public List<RentalEntity> getPage(@RequestParam int limit, int offset) {
        return rentalServiceImp.getPage(limit, offset);
    }

    @GetMapping("/ids")
    @Trace
    public List<RentalEntity> getIds(@RequestParam long[] ids) {
        return rentalServiceImp.getByCampervansIds(ids);
    }

}
