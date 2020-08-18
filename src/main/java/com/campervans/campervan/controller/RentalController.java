package com.campervans.campervan.controller;

import com.campervans.campervan.exception.RecordNotFoundException;
import com.campervans.campervan.model.RentalEntity;
import com.campervans.campervan.repository.RentalRepository;
import com.campervans.campervan.service.RentalServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/campervans",  produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalController {

    @Autowired
    private RentalServiceImp service;

    @Autowired
    private final RentalRepository rentalRepository;

        public RentalController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @RequestMapping("/van_paged")
    public ResponseEntity<List<RentalEntity>> getPagedCapmpervans(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        List<RentalEntity> list = service.getPagedCampervans(pageNo, pageSize);//, sortBy);

        return new ResponseEntity<List<RentalEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/")
    public ResponseEntity<List<RentalEntity>> geSortedCapmpervans(
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<RentalEntity> list = service.getSortBy( sortBy);

        return new ResponseEntity<List<RentalEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/list")
    List<RentalEntity> findAll() {
        return (List<RentalEntity>) rentalRepository.findAll();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<RentalEntity> getCampervanById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        RentalEntity entity = service.getCampervanById(id);

        return new ResponseEntity<RentalEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/near")
    public List<RentalEntity> getLocation(@RequestParam("lat")
                                          @Min(-100L) @Max(100L)
                                                  double x,
                                          @RequestParam("lng")
                                          @Min(-100L) @Max(100L)
                                                  double y) {
        return service.getNearLocation(x, y);
    }

    @RequestMapping("/priceMinMax")
    public List<RentalEntity> getPricePerDayMinMax(@RequestParam double min, double max) {
        return service.getPriceMinMax(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
    }

    @RequestMapping("/ids")
    public List<RentalEntity> getCampvanByIds(@RequestParam long[] ids) throws RecordNotFoundException {
        return service.getCampervanByIds(ids);
    }
    }
