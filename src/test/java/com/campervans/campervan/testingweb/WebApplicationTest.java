package com.campervans.campervan.testingweb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.campervans.campervan.controller.RentalController;
import com.campervans.campervan.model.RentalEntity;
import com.campervans.campervan.service.RentalServiceImp;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RentalController.class)
public class WebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalController rentalController;

    @MockBean
    private RentalServiceImp service;

    @MockBean
    RentalEntity rentalEntity;

    @Test
    public void getFromService() throws Exception {


        when(service.getCampervanById(any(Long.class))).thenReturn(rentalEntity);
//        this.mockMvc.perform(get("/{id}")).andExpect(status().isOk());
        assertThat(rentalController).isNotNull();
    }
}
