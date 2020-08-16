package com.campervans.campervan.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface IRentalService {

    List getPriceMinMax(BigDecimal min, BigDecimal max);
    List getAllCampervan();

//    List getPricePerDayBetween(BigDecimal min, BigDecimal max);

    List getByCampervansIds(long ids []);

    List getLocation(double lad, double lng);

    List getAllCampervanOrderByPricePerDayDESC();

    List getPage(int limit, int offset);

    List getByCampervanID(long id);
}
