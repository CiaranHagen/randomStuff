import static org.junit.Assert.assertEquals;
import java.io.*;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/*

I chose to create both Streets, Intersections and the network as class objects. I chose this to allow me to store more information per object, especially for the network. 
This way I can, additionally to Streets and Intersections, store the number of streets/ intersections and also access most everything from whatever known factor I have.

The streets and intersections could very well have been stored in simple 2-dimensional arrays, but for the sake of consistency and ease of access of the elements, I decided to use them as classes as well. (Also I really like classes :)

---------

Pertaining to the perks of the Dijkstra method vs the Floyd-Warshall method, it seems to me that the Dijkstra method uses more processing power to execute, due to its recursive nature. It also uses way more temporary arrays, using more storage than really necessary. However this allows for easier checking of nodes (visited or non-visited).

The Floyd-Warshall method on the other hand is done more or less in-place, meaning that it uses far less storage. When finished, it also has the perk f returning a two dimensional array, allowing us to get access to every connection in the graph, as opposed to the Dijkstra version, which only gives us the paths pertaining to one node.

In the end, I would, for small networks, prefer the Floyd-Warshall as it calculates every path more efficiently. For large networks, especially ones consisting of several unconnected sub-networks, Dijkstra's method might be better, as it only calculates the paths to the nodes reachable from the node of interest. 

If this exercise were different and asked for several different endpoints, I am certain that Floyd-Warshall would be the superior method. 
However, as there is always only one endpoint, Dijkstra's algorithm seems like the wiser choice for this exercise.

*/

@RunWith(JUnit4.class)
public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() throws FileNotFoundException, IOException {

        new CompetitionDijkstra("./tinyEWD.txt", 60, 70, 80);
    }
    
    @Test
    public void testDijkstraFilled() {
        assertEquals("DC Street 0 length", CompetitionDijkstra.network.streets[0].length, 0.35);
        assertEquals("DC Street 0 from", CompetitionDijkstra.network.streets[0].from, 4);
        assertEquals("DC Street 0 to", CompetitionDijkstra.network.streets[0].to, 5);
        
        assertEquals("DC Street 1 length", CompetitionDijkstra.network.streets[1].length, 0.35);
        assertEquals("DC Street 1 from", CompetitionDijkstra.network.streets[1].from, 5);
        assertEquals("DC Street 1 from", CompetitionDijkstra.network.streets[1].to, 4);
        
        assertEquals("DC Street 2 length", CompetitionDijkstra.network.streets[2].length, 0.37);
        assertEquals("DC Street 2 from", CompetitionDijkstra.network.streets[2].from, 4);
        assertEquals("DC Street 2 from", CompetitionDijkstra.network.streets[2].to, 7);
        
        assertEquals("DC Street 3 length", CompetitionDijkstra.network.streets[3].length, 0.28);
        assertEquals("DC Street 3 from", CompetitionDijkstra.network.streets[3].from, 5);
        assertEquals("DC Street 3 from", CompetitionDijkstra.network.streets[3].to, 7);
        
        assertEquals("DC Street 4 length", CompetitionDijkstra.network.streets[4].length, 0.28);
        assertEquals("DC Street 4 from", CompetitionDijkstra.network.streets[4].from, 7);
        assertEquals("DC Street 4 from", CompetitionDijkstra.network.streets[4].to, 5);
        
        assertEquals("DC Street 5 length", CompetitionDijkstra.network.streets[5].length, 0.32);
        assertEquals("DC Street 5 from", CompetitionDijkstra.network.streets[5].from, 5);
        assertEquals("DC Street 5 from", CompetitionDijkstra.network.streets[5].to, 1);
        
        assertEquals("DC Street 6 length", CompetitionDijkstra.network.streets[6].length, 0.38);
        assertEquals("DC Street 6 from", CompetitionDijkstra.network.streets[6].from, 0);
        assertEquals("DC Street 6 from", CompetitionDijkstra.network.streets[6].to, 4);
        
        assertEquals("DC Street 7 length", CompetitionDijkstra.network.streets[7].length, 0.26);
        assertEquals("DC Street 7 from", CompetitionDijkstra.network.streets[7].from, 0);
        assertEquals("DC Street 7 from", CompetitionDijkstra.network.streets[7].to, 2);
        
        assertEquals("DC Street 8 length", CompetitionDijkstra.network.streets[8].length, 0.39);
        assertEquals("DC Street 8 from", CompetitionDijkstra.network.streets[8].from, 7);
        assertEquals("DC Street 8 from", CompetitionDijkstra.network.streets[8].to, 3);
        
        assertEquals("DC Street 9 length", CompetitionDijkstra.network.streets[9].length, 0.29);
        assertEquals("DC Street 9 from", CompetitionDijkstra.network.streets[9].from, 1);
        assertEquals("DC Street 9 from", CompetitionDijkstra.network.streets[9].to, 3);
        
        assertEquals("DC Street 10 length", CompetitionDijkstra.network.streets[10].length, 0.34);
        assertEquals("DC Street 10 from", CompetitionDijkstra.network.streets[10].from, 2);
        assertEquals("DC Street 10 from", CompetitionDijkstra.network.streets[10].to, 7);
        
        assertEquals("DC Street 11 length", CompetitionDijkstra.network.streets[11].length, 0.40);
        assertEquals("DC Street 11 from", CompetitionDijkstra.network.streets[11].from, 6);
        assertEquals("DC Street 11 from", CompetitionDijkstra.network.streets[11].to, 2);
        
        assertEquals("DC Street 12 length", CompetitionDijkstra.network.streets[12].length, 0.52);
        assertEquals("DC Street 12 from", CompetitionDijkstra.network.streets[12].from, 3);
        assertEquals("DC Street 12 from", CompetitionDijkstra.network.streets[12].to, 6);
        
        assertEquals("DC Street 13 length", CompetitionDijkstra.network.streets[13].length, 0.58);
        assertEquals("DC Street 13 from", CompetitionDijkstra.network.streets[13].from, 6);
        assertEquals("DC Street 13 from", CompetitionDijkstra.network.streets[13].to, 0);
        
        assertEquals("DC Street 14 length", CompetitionDijkstra.network.streets[14].length, 0.93);
        assertEquals("DC Street 14 from", CompetitionDijkstra.network.streets[14].from, 6);
        assertEquals("DC Street 14 from", CompetitionDijkstra.network.streets[14].to, 4);
    }
    
    @Test
    public void testDijkstraTime() {
    
        //For some reason this test doesn't throw an error no matter what I enter.
        //I can't find the problem.
        int testTime = CompetitionDijkstra.timeRequiredforCompetition(0, 2, 6, 7);
        assertEquals("Time Test ", testTime, 5);
    }
    
    @Test
    public void testFWConstructor() throws FileNotFoundException, IOException {

        new CompetitionDijkstra("./impossible.txt", 60, 70, 80);
    }
    
    @Test
    public void testImpossibleTime(){
        int testTime = CompetitionDijkstra.timeRequiredforCompetition(0, 2, 3, 7);
        assertEquals("Time impossible Test ", testTime, -1);
    }    
}
