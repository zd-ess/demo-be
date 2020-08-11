package com.campervans.campervan.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.data.annotation.Id;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

// Base class with Entity mappings
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
