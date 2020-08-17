package com.campervans.campervan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rental_images",indexes = {@Index(name = "rental_images_rental_id_fkey",columnList = ("rental_id"))})
@NoArgsConstructor
@Getter
@Setter
public class ImageEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private RentalEntity rentalEntity;

    @Column(name = "url", nullable = false)
    private String url;
}
