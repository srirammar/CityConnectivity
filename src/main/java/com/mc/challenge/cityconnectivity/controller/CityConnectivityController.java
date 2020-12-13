package com.mc.challenge.cityconnectivity.controller;

import com.mc.challenge.cityconnectivity.exception.InternalProcessingException;
import com.mc.challenge.cityconnectivity.exception.RouteNotFoundException;
import com.mc.challenge.cityconnectivity.service.CityConnectivityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static com.mc.challenge.cityconnectivity.util.CityConnectivityConstants.YES;
import static com.mc.challenge.cityconnectivity.util.CityConnectivityConstants.NO;

@RestController
public class CityConnectivityController {
    @Autowired
    private CityConnectivityService cityConnectivityService;
    private static final Logger logger = LoggerFactory.getLogger(CityConnectivityController.class.getName());

    /**
     *
     * @param origin
     * @param destination
     * @return
     * @throws InternalProcessingException
     */
    @GetMapping("/connected")
    public ResponseEntity<String> checkRouteExists(@RequestParam("origin") final String origin
                                , @RequestParam("destination") final String destination ) throws InternalProcessingException {
        boolean connectivityExists = false;
        try{
            connectivityExists = cityConnectivityService.verifyRouteExists(origin,destination);
            logger.debug("Are the cities connected : {}",connectivityExists);
        }
        catch (final RouteNotFoundException rnfe){
            connectivityExists = false;
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(connectivityExists ? YES : NO);
    }
}
