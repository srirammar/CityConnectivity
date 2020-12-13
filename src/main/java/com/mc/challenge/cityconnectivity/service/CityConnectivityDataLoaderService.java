package com.mc.challenge.cityconnectivity.service;

import com.mc.challenge.cityconnectivity.exception.InternalProcessingException;
import com.mc.challenge.cityconnectivity.model.CityRoutesAdjacencyMatrix;
import static com.mc.challenge.cityconnectivity.util.CityConnectivityConstants.*;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.*;

public class CityConnectivityDataLoaderService {
    /**
     *
     * @return
     * @throws InternalProcessingException
     */
    public static CityRoutesAdjacencyMatrix buildRouteMap() throws InternalProcessingException {
        final CityRoutesAdjacencyMatrix adjMatrix= CityRoutesAdjacencyMatrix.getInstance();
        final HashMap<String, HashSet<String>> routeMap = new HashMap<>();
        buildAdjacencyMatrix(routeMap);
        adjMatrix.populateAdjacencyMatrix(routeMap);
        updateAdjVertiesList(routeMap);
        return adjMatrix;
    }

    private static void updateAdjVertiesList(final HashMap<String,HashSet<String>> inMap) {
        for(Map.Entry<String,HashSet<String>> entry : inMap.entrySet()) {
            final HashSet<String> adjVerticesList = entry.getValue();
            final HashSet<String> tempList = new HashSet<>();
            for(final String city: adjVerticesList){
                final HashSet<String> routeMapCities = inMap.get(city.toLowerCase());
                if(Objects.nonNull(routeMapCities)) {
                    tempList.addAll(routeMapCities);
                }
            }
            adjVerticesList.addAll(tempList);
            adjVerticesList.remove(entry.getKey());
        }
    }

    /**
     *
     * @param inMap
     * @throws InternalProcessingException
     */

    private static void buildAdjacencyMatrix(final HashMap<String,HashSet<String>> inMap)
                                        throws InternalProcessingException {
        try( BufferedReader csvReader = new BufferedReader(new FileReader(CITY_DATA_FILE))) {
            String row = null;
            while ((row = csvReader.readLine()) != null) {
                final String[] rowData = row.split(",");
                if (rowData.length != 2) {
                    throw new InternalProcessingException("Invalid data or unstructured csv file...");
                }
                //build adjacency matrix for both source and destination
                buildAdjVerticesList(inMap,rowData[0], rowData[1]);
                buildAdjVerticesList(inMap,rowData[1], rowData[0]);
            }
        } catch (final Exception ex) {
            throw new InternalProcessingException("Exception occurred while trying to load data and setup data");
        }
    }

    /**
     *
     * @param inMap
     * @param source
     * @param destination
     */
    private static void buildAdjVerticesList(final HashMap<String,HashSet<String>> inMap, final String source, final String destination) {

        final HashSet<String> sourceAdjVerticesList = inMap.get(source.toLowerCase());
        if(Objects.isNull(sourceAdjVerticesList)) {
            final HashSet<String> newAdjVerticesList = new HashSet<>();
            newAdjVerticesList.add(destination.toLowerCase());
            inMap.put(source.toLowerCase(),newAdjVerticesList);
        } else {
            sourceAdjVerticesList.add(destination.toLowerCase());
            final HashSet<String> destAdjVerticesList =  inMap.get(destination);
            if(Objects.nonNull(destAdjVerticesList)){
                sourceAdjVerticesList.addAll(destAdjVerticesList);
            }
            inMap.put(source.toLowerCase(),sourceAdjVerticesList);
        }
    }
}
