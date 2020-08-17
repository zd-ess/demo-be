package com.campervans.campervan.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface IRentalService {

    List getPriceMinMax(BigDecimal min, BigDecimal max);

    List getNearLocation(double lad, double lng);
}
