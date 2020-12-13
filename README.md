# Project Name
City Routes Finder
## Project Description
This Spring boot micro-service project reads an input csv file with route map between two cities located at PROJECT_ROOT/data directory.
It then builds a graph using an adjacency matrix while loading the app itself and serves the HTTP GET requests from this adjacency matrix.

## Installation
Get the project code from GITHUB
$PROJECT_HOME/mvn clean install
$PROJECT_HOME/java -jar ./target/cityconnectivity-0.0.1-SNAPSHOT.jar
PROJECT_HOME is the directory where the source code is checked out i.e cityconnectivity
## Usage
The app installs on Spring boot Tomcat port at 8080 and when invoked as shown in below examples
     http://localhost:8080/connected?origin=frankfurt&destination=newyork -->NO
      http://localhost:8080/connected?origin=boston&destination=newyork -->YES
 will return YES if route exists between else returns NO
