package com.mc.challenge.cityconnectivity.service;

import com.mc.challenge.cityconnectivity.exception.InternalProcessingException;
import com.mc.challenge.cityconnectivity.exception.RouteNotFoundException;
import com.mc.challenge.cityconnectivity.repo.CityConnectivityServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.mc.challenge.cityconnectivity.util.CityConnectivityConstants.BEAN_CITY_CONNECTIVITY_SERVICE;

@Service(BEAN_CITY_CONNECTIVITY_SERVICE)
public class CityConnectivityService {
    @Autowired
    private CityConnectivityServiceRepository cityConnectivityServiceRepo;

    /**
     *
     * @param sourceCity
     * @param destinationCity
     * @return
     * @throws InternalProcessingException
     * @throws RouteNotFoundException
     */
    public boolean verifyRouteExists(final String sourceCity, final String destinationCity)
                        throws InternalProcessingException, RouteNotFoundException {
        return cityConnectivityServiceRepo.findRouteExists(sourceCity,destinationCity);
    }
}
