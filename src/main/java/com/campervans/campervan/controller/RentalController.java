package com.campervans.campervan.controller;


import com.campervans.campervan.exception.RecordNotFoundException;
import com.campervans.campervan.model.RentalEntity;
import com.campervans.campervan.repository.RentalRepository;
import com.campervans.campervan.service.RentalServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import datadog.trace.api.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
//@RequestMapping(path = "/rental",  produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/campervans",  produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalController {

    private static Logger logger = LoggerFactory.getLogger(RentalController.class);



    @Autowired
    private RentalServiceImp service;

    @Autowired
    private final RentalRepository rentalRepository;


        RentalController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @GetMapping("/van")
    public ResponseEntity<List<RentalEntity>> getPagedAndSortedCapmpervans(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<RentalEntity> list = service.getPagedAndSortedCampervans(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<RentalEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/van_paged")
    public ResponseEntity<List<RentalEntity>> getPagedCapmpervans(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize)
//            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<RentalEntity> list = service.getPagedCampervans(pageNo, pageSize);//, sortBy);

        return new ResponseEntity<List<RentalEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/campervans")
    public ResponseEntity<List<RentalEntity>> geSortedCapmpervans(
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<RentalEntity> list = service.getSortBy( sortBy);

        return new ResponseEntity<List<RentalEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    List<RentalEntity> all() {
        return (List<RentalEntity>) rentalRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<RentalEntity> getCampervanByIds(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        RentalEntity entity = service.getCampervanById(id);

        return new ResponseEntity<RentalEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    //////


    //TODO
//    - `campervans`
//    - `campervans?price[min]=9000&price[max]=75000`    ok
//    - `campervans?page[limit]=3&page[offset]=6`        ok
//    - `campervans?ids=2000,51155,54318`
//    - `campervans?near=33.64,-117.93` // within 100 miles
//    - `campervans?sort=price`                          ok
//    - `campervans/<CAMPER_VAN_ID>`

//    @GetMapping("/all")
//    @Trace
//    public List<RentalEntity> getAllRental(Pageable pageable) {
//        return rentalServiceImp.getAllCampervan();
//    }

    @GetMapping("/all")
    @Trace
    public List<RentalEntity> getAllRental() {
        return service.getAllCampervan();
    }

    @GetMapping("/all_price")
    @Trace
    public List<RentalEntity> getAllPriceDecs() {
        return service.getAllCampervanOrderByPricePerDayDESC();
    }

    @GetMapping("/price_between")
    @Trace
    public List<RentalEntity> getPriceBetween(@RequestParam double min, double max) {
        return service.getPricePerDayBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
    }

    @GetMapping("/get_id")
    @Trace
    public List<RentalEntity> getRentalsId(@RequestParam long id) {

        return service.getByCampervanID(id);
    }

    @GetMapping("/get_near")
    @Trace
    public List<RentalEntity> getLocated(@RequestParam double x, double y) {
        return service.getLocation(x, y);
    }

    @GetMapping("/page")
    @Trace
    public List<RentalEntity> getPage(@RequestParam int limit, int offset) {
        return service.getPage(limit, offset);
    }

    @GetMapping("/ids")
    @Trace
    public List<RentalEntity> getIds(@RequestParam long[] ids) {
        return service.getByCampervansIds(ids);
    }

}
