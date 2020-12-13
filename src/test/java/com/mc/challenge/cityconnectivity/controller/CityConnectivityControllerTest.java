package com.mc.challenge.cityconnectivity.controller;

import com.mc.challenge.cityconnectivity.service.CityConnectivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CityConnectivityController.class)
public class CityConnectivityControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityConnectivityService service;

    @Test
    public void givenCities_whenRouteExists_thenReturnNO()
            throws Exception {
        Mockito.when(service.verifyRouteExists(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenReturn(false);
        final MvcResult requestResult = mvc.perform(get("/connected?origin=frankfurt&destination=newyork")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "NO");
    }

    @Test
    public void givenCities_whenRouteExists_thenReturnYES()
            throws Exception {
        Mockito.when(service.verifyRouteExists(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenReturn(true);
        final MvcResult requestResult = mvc.perform(get("/connected?origin=boston&destination=newyork")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "YES");
    }
}

