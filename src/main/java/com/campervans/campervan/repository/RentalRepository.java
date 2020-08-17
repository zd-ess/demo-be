package com.campervans.campervan.repository;

import com.campervans.campervan.model.RentalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RentalRepository extends  PagingAndSortingRepository<RentalEntity, Long> {

    @Query("select r from RentalEntity as r where (sqrt((r.lat-:x)*(r.lat-:x)+(r.lng-:y)*(r.lng-:y)))<=100")
    List<RentalEntity> findLocated(double x, double y);

    List<RentalEntity> findByPricePerDayBetween(BigDecimal min, BigDecimal max);

//    @Query("select r from RentalEntity as r where sqrt((r.lat-:x)*(r.lat-:x)+(r.lng-:y)*(r.lng-:y))<=100")
//    @Query( SELECT *, point(-85.3078294, 35.0609500) <@> point(longitude, latitude)::point as distance
//    FROM rental
//    WHERE (point(-85.3078294, 35.0609500) <@> point(longitude, latitude)) < 100
//    ORDER BY distance);

//    @Query("SELECT select r from RentalEntity point(-85.3078294, 35.0609500) <@> point(lng, lat)::point as distance FROM RentalEntity where (point(-85.3078294, 35.0609500) <@> point(lng, lat)) < 100 ORDER BY distance")


//    @Query("SELECT p from RentalEntity as p where  point(-85.3078294, 35.0609500) <@> point(p.lng, p.lat)::point as distance FROM RentalEntity where (point(-85.3078294, 35.0609500) <@> point(lng, lat)) < 100 ")
//    List<RentalEntity> findLocated(double x, double y);

    List<RentalEntity> findAllById(long id);

//    @Query(value ="SELECT *, ( 6371 * acos( cos( radians(?1) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?2) ) + sin( radians(?1) ) * sin( radians( lat ) ) ) ) AS distance FROM user WHERE roles='USER' HAVING distance < 100 ORDER BY distance LIMIT 0 , 20",nativeQuery = true)

//    @Query(value ="SELECT  r from RentalEntity as r where( 6371 * acos( cos( radians(?1) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?2) ) + sin( radians(?1) ) * sin( radians( lat ) ) ) ) AS distance FROM rentals WHERE roles='USER' HAVING distance < 100 ORDER BY distance LIMIT 0 , 20",nativeQuery = true)

//    @Query(value ="SELECT *,( 6371 * acos( cos( radians(?1) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?2) ) + sin( radians(?1) ) * sin( radians( lat ) ) ) ) AS distance FROM rentals  HAVING distance < 100 ORDER BY distance LIMIT 0 , 20",nativeQuery = true)

//    @Query(value ="SELECT d FROM RentalEntity as d" +
//            " WHERE ((SQRT" +
//            "(POW(((d.lat-:x)) , 2 ) +" +
//            " POW(((d.lng-:y))), 2))) " +
////            " AS distance " +
//            "WHERE d < 1")// +
////            " ORDER BY distance ASC ") //,
////            nativeQuery = true)
//    List<RentalEntity> findAllNearBy(double lat,  double lng);


}
