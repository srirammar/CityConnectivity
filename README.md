# Project Name
City Routes Finder
## Project Description
This Spring boot project reads an input csv file with route map between two cities and builds a graph using an adjacency matrix
The app installs on Tomcat port at 8080 and when invoked as shown in the 
     http://localhost:8080/connected?origin=frankfurt&destination=newyork
 will return YES if route exists between else returns NO
## Installation
Get the project code from GITHUB
$PROJECT_HOME/mvn clean install
$PROJECT_HOME/java -jar ./target/cityconnectivity-0.0.1-SNAPSHOT.jar
PROJECT_HOME is the directory where the source code is checked out i.e cityconnectivity
## Usage
TODO: Write usage instructions