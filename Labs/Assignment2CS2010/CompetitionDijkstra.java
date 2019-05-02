/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */
 
//import java.util.Random;
import java.io.*;
import java.lang.*;

class Street {
    double length;
    int from;
    int to;
    
    Street(double length, int from, int to) {
        this.length = length;
        this.from = from;
        this.to = to;
    }
}

class Intersection {
    int id;
    Intersection(int id) {
        this.id = id;
    }
    Street[] streets;
    
}

class Network {
    int numStreet;
    int numInter;
    Network(int numStreet, int numInter) {
        this.numStreet = numStreet;
        this.numInter = numInter;
    }
    Street[] streets = new Street[numStreet];
    Intersection[] inters = new Intersection[numInter];
}

class CompetitionDijkstra {
    static int sA;
    static int sB;
    static int sC;
    static Network network;
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC) throws FileNotFoundException, IOException{
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        String everything;
        //READ FROM FILE
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }
        
        String[] everyArray = everything.split("\n"); //split into individual lines
        int numInter = Integer.parseInt(everyArray[0]); 
        int numStreets = Integer.parseInt(everyArray[1]);
        network = new Network(numStreets, numInter); //initialize network structure
        String[] streeter;
        for (int i = 0; i<numStreets; i++) { //convert lines from file into Streets
            streeter = everyArray[i+2].split(" ");
            network.streets[i] = new Street(Double.parseDouble(streeter[2]), Integer.parseInt(streeter[0]), Integer.parseInt(streeter[1]));
        }
        for (int i = 0; i<numInter; i++) {
            int counter = 0;
            for (int j = 0; j<numStreets; j++) {
                if (network.streets[j].from == i) {
                    counter++;
                }
            }
            network.inters[i] = new Intersection(i);
            network.inters[i].streets = new Street[counter];
        }
        for (int i = 0; i<numInter; i++) {
            int counter = 0;
            for (int j = 0; j<numStreets; j++) {
                if (network.streets[j].from == i) {
                    network.inters[i].streets[counter] = network.streets[j];
                    counter++;
                }
            }
        }
    }
    
    static private double[] sPD(Intersection src) {
        double[] distance = new double[network.numInter];
        
        for (int i = 0; i<network.numInter; i++) {
            distance[i] = 99999;
        }
        distance[src.id] = 0;
        return shortestPathDijkstra(src, 0, distance);
    }
    
    static double[] shortestPathDijkstra(Intersection src, double preDist, double[] distance) {
        for (int i = 0; i<src.streets.length; i++) {
            if (distance[src.streets[i].to] == 99999) {                   //if the intersection is unhandled
                double distTo = src.streets[i].length;                      //get length of street we're going to use
                distance[src.streets[i].to] = preDist + distTo;          //add total distance to src to distance[]
                distance = shortestPathDijkstra(network.inters[src.streets[i].to], preDist + distTo, distance);  //shortest path on all (non-visited) child nodes
            }
        }
        return distance;
    }
    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    static int timeRequiredforCompetition(int iA, int iB, int iC, int iM){
        /*
        Random r = new Random()
        int iA = r.nextInt(network.numInter+1);
        int iB = r.nextInt(network.numInter+1);
        int iC = r.nextInt(network.numInter+1);
        int iM = r.nextInt(network.numInter+1);
        */
        double[] dist = sPD(network.inters[iM]);
        for (int i = 0; i<dist.length; i++) {
            if (dist[i] == 99999) {
                return -1;
            }
        }
        double[] times= {(dist[iA]*1000)/sA, (dist[iB]*1000)/sB, (dist[iC]*1000)/sC};
        return (int) Math.ceil(Math.max(Math.max(times[0], times[1]), times[2]));
    }

}
