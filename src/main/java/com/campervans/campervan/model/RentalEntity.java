package com.campervans.campervan.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
@AllArgsConstructor
@Getter
@Setter
public class RentalEntity extends  AbstractEntity{


    public RentalEntity(){}

//    @Column(name = "name", nullable = false)
    @NotNull
    @Column(columnDefinition = "VARCHAR", length = 100)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "sleeps", nullable = false)
    private int sleeps;

    @Column(name = "price_per_day", nullable = false)
    private BigDecimal pricePerDay;

    @Column(name = "home_city", nullable = false)
    private String homeCity;

    @Column(name = "home_state")
    private String homeState;

    @Column(name = "home_zip")
    private String homeZip;

    @Column(name = "home_county")
    private String homeCounty;

    @Column(name = "home_country", nullable = false)
    private String homeCountry;

    @Column(name = "vehicle_make", nullable = false)
    private String vehicleMake;

    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @Column(name = "vehicle_year", nullable = false)
    private int vehicleYear;

    @Column(name = "vehicle_length", nullable = false)
    private double length;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime updated;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @Column(name = "primary_image_url", nullable = false)
    private String primaryImageUrl;

    @Column(name = "owner_name", nullable = false)
    private String OwnerName;

    @Column(name = "owner_avatar_url")
    private String OwnerAvatarUrl;

}
