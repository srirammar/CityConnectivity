package com.mc.challenge.cityconnectivity.model;

import java.util.HashMap;
import java.util.HashSet;

/** A singleton Java class bound to the JVM representing the Routes Adjacency matrix
The data structure would be hashmap of String to ArrayList of Strings representing the
City(Vertex) to adjacency Cities (Vertices). The adjacent vertices mapped if there is route to the city
 */

 public class CityRoutesAdjacencyMatrix implements Cloneable{

    private HashMap<String, HashSet<String>> adjacencyMatrix = new HashMap<>();
    private CityRoutesAdjacencyMatrix(){
        //Constructor blocked from invocation
    }

    static class CityRoutesAdjacencyMatrixHolder{
        static final CityRoutesAdjacencyMatrix instance = new CityRoutesAdjacencyMatrix();
    }

    public static CityRoutesAdjacencyMatrix getInstance(){
        return CityRoutesAdjacencyMatrixHolder.instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private void invalidateAdjacencyMatrix() {
        this.adjacencyMatrix.clear();
    }


    /**
     *
     * @param inMap
     */
    public void populateAdjacencyMatrix(final HashMap<String, HashSet<String>> inMap){
        invalidateAdjacencyMatrix();
        inMap.forEach((k,v)->{
            this.adjacencyMatrix.put(k,v);
        });
    }

    /**
     *
     * @return
     */
    public HashMap<String,HashSet<String>> getAdjacencyMatrix(){
        return adjacencyMatrix;
    }
}
