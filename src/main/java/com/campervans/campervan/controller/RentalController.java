package com.campervans.campervan.controller;

import com.campervans.campervan.exception.RecordNotFoundException;
import com.campervans.campervan.model.RentalEntity;
import com.campervans.campervan.repository.RentalRepository;
import com.campervans.campervan.service.RentalServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/campervans",  produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalController {

    private static Logger logger = LoggerFactory.getLogger(RentalController.class);

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

    @RequestMapping("/campervans")
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

    @RequestMapping("/priceMinMax")
    public List<RentalEntity> getPricePerDayMinMax(@RequestParam double min, double max) {
        return service.getPriceMinMax(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
    }

    @RequestMapping("/ids")
    public List<RentalEntity> getCampvanByIds(@RequestParam long[] ids) throws RecordNotFoundException {
        return service.getCampervanByIds(ids);
    }

//    @RequestMapping("near/{lat},{lng}")
//    public List<RentalEntity> getNearbyUsers(@PathVariable double lat, @PathVariable double lng)  {
//        List<RentalEntity> listNear=  this.rentalRepository.findAllNearBy(lat,lng);
//
//        return listNear;
//    }

//    @RequestMapping("/near/{lat}/{lng}")
//    public List<RentalEntity> getLocated(@RequestParam("lat") double x,
//                                         @RequestParam("lng") double y) {
//        return service.getLocation(x, y);
//    }
@GetMapping("/near")
public List<RentalEntity> getLocated(@RequestParam("x") double x,
                                     @RequestParam("y") double y) {
    return service.getLocation(x, y);
}

    //TODO
//    - `campervans`                                     ok list
//    - `campervans?price[min]=9000&price[max]=75000`    ok
//    - `campervans?page[limit]=3&page[offset]=6`        ok
//    - `campervans?ids=2000,51155,54318`                ok
//    - `campervans?near=33.64,-117.93` // within 100 miles
//    - `campervans?sort=price`                          ok
//    - `campervans/<CAMPER_VAN_ID>`                     ok

//
//    http://127.0.0.1:8089/campervans/list

//    http://127.0.0.1:8089/campervans/priceMinMax?min=16900&max=17001

//    http://127.0.0.1:8089/campervans/van_paged?pageSize=20&pageNo=1

//    http://127.0.0.1:8089/campervans/ids?ids=11368,21399,4447

//    http://127.0.0.1:8089/campervans/near?lat=21.89&lng=-47.95

//    http://127.0.0.1:8089/campervans/campervans?sortBy=pricePerDay

//    http://127.0.0.1:8089/campervans/119960


}
