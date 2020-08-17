package com.campervans.campervan.controller;

import com.campervans.campervan.model.RentalEntity;
import com.campervans.campervan.service.RentalServiceImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RentalController.class)
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    RentalController employeeController;

    @MockBean
    private RentalController rentalController;

    @MockBean
    private RentalServiceImp service;

    @MockBean
    RentalEntity rentalEntity;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void geSortedCapmpervans() {

        List<RentalEntity> rentalEntityList = new ArrayList<>();

        rentalEntityList.add(rentalEntity);

        when(service.getSortBy(any(String.class))).thenReturn(rentalEntityList);
        assertThat(rentalEntityList).isNotNull();
    }

    @Test
    void getCampervanById() throws Exception {

        when(service.getCampervanById(any(Long.class))).thenReturn(rentalEntity);
        assertThat(rentalController).isNotNull();
    }

}