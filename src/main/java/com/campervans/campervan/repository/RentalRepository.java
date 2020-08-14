package com.campervans.campervan.repository;

import com.campervans.campervan.model.RentalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RentalRepository extends  PagingAndSortingRepository<RentalEntity, Long> {

    @RestResource(rel = "rental", path="rental")
    @Query("SELECT r FROM RentalEntity r WHERE r.name = :name")
    Page<RentalEntity> findAll(@Param("name") String query, Pageable page);

    List<RentalEntity> findByPricePerDayBetween(BigDecimal min, BigDecimal max);

    @Query("select r from RentalEntity as r where sqrt((r.lat-:x)*(r.lat-:x)+(r.lng-:y)*(r.lng-:y))<=100")
    List<RentalEntity> findLocated(double x, double y);

    List<RentalEntity> findAllByOrderByPricePerDayDesc();

    List<RentalEntity> findAllById(long id);
}
