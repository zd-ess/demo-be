package com.campervans.campervan.repository;

import com.campervans.campervan.model.RentalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RentalRepository extends  PagingAndSortingRepository<RentalEntity, Long> {

    List<RentalEntity> findByPricePerDayBetween(BigDecimal min, BigDecimal max);

    List<RentalEntity> findAllById(long id);

      @Query("SELECT d FROM RentalEntity " +
              "as d where SQRT" +
              "(POWER((d.lat-:x),2.0)" +
              " + POWER((d.lng-:y),2.0))  < 100")
    List<RentalEntity> findNearLocation(double x,  double y);


}
