package com.campervans.campervan.repository;

import com.campervans.campervan.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAll();

    List<Rental> findByPricePerDayBetween(BigDecimal min, BigDecimal max);

    @Query("select r from Rental as r where sqrt((r.lat-:x)*(r.lat-:x)+(r.lng-:y)*(r.lng-:y))<=100")
    List<Rental> findLocated(double x, double y);

    List<Rental> findAllByOrderByPricePerDayDesc();

    List<Rental> findAllById(long id);
}
