package com.mc.challenge.cityconnectivity.repo;

import com.mc.challenge.cityconnectivity.exception.InternalProcessingException;
import com.mc.challenge.cityconnectivity.exception.RouteNotFoundException;
import com.mc.challenge.cityconnectivity.model.CityRoutesAdjacencyMatrix;
import com.mc.challenge.cityconnectivity.service.CityConnectivityDataLoaderService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import static com.mc.challenge.cityconnectivity.util.CityConnectivityConstants.CITY_CONNECTIVITY_SERVICE_REPOSITORY;

@Repository(CITY_CONNECTIVITY_SERVICE_REPOSITORY)
public class CityConnectivityServiceRepository {

    /**
     *
     * @param sourceCity
     * @param destinationCity
     * @return
     * @throws InternalProcessingException
     * @throws RouteNotFoundException
     */
    public boolean findRouteExists(final String sourceCity,final String destinationCity)
                throws  InternalProcessingException,RouteNotFoundException{
        //If data does not exists throw 500 error
        final CityRoutesAdjacencyMatrix adjMatrix = CityRoutesAdjacencyMatrix.getInstance();

        if(Objects.isNull(adjMatrix) || adjMatrix.getAdjacencyMatrix().isEmpty()){
            CityConnectivityDataLoaderService.buildRouteMap();
        }

        //if data exists and source city does not exists throw 404 error
        final HashSet<String> adjCityVertices = adjMatrix.getAdjacencyMatrix().get(sourceCity.toLowerCase());
        if(Objects.isNull(adjCityVertices)){
            throw new RouteNotFoundException("The route between the cities "+sourceCity+ " and "+destinationCity +" does not exist");
        }
        return adjCityVertices.contains(destinationCity.toLowerCase());
    }

}
