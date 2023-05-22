import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Node;

public class Rescue {

    private static final int ORIGIN = 1;
    private static final int INFINITY = Integer.MAX_VALUE;


    private int nRegions;
    private Edge[][] graph;


    public Rescue(int nRegions) {

        this.nRegions = nRegions;
        
        int nPos = 2*nRegions + ORIGIN;
        
        //Initialize graph
        this.graph = new Edge[nPos][nPos];
        // {0, 1_e, 1_s, 2_e, 2_s, ..., nRegions-1_e, nRegions-1_s}

    }


    public void createNode(int id, int population, int departure) {

        //Origin node to node and vice versa
        connect(0, id, population, 0);

        //Entry node to exit node and vice versa
        connect(id, id, 0, departure);

    }


    public void addEdge(int id1, int id2) {

        connect(id1, id2, INFINITY, 0);
        connect(id2, id1, INFINITY, 0);
        
    }


    private void connect (int id1, int id2, int cap, int revCap) {

        int exitId1 = id1*2;
        int exitId2 = id2*2;
        int entryId2 = exitId2-1;

        Edge edge = new Edge(entryId2, cap);
        Edge reverse = new Edge (exitId1, revCap);

        graph[exitId1][entryId2] = edge;
        graph[entryId2][exitId1] = reverse;
        
    }


    public int edmondsKarp(int sink) {

        int sinkEntry = sink*2;
        int sinkExit = sinkEntry-1;
        int result = 0;
        int increment;

        while ( ( increment = findPath(sink) )!= 0 ) {

            result += increment;

            // Update flow.
            int node = sinkEntry;
            while ( node != 0 ) {

                int origin = via[node];
                graph[origin][node].incrementFlow(increment);
                graph[node][origin].incrementFlow(-increment);;
                node = origin;

            }

        }

        return result;

    }
    

    int findPath(int sink) {

        Queue<Integer> waiting = new LinkedList<>();
        boolean[] found = new boolean[nRegions];
        int[] pathIncr = new int[nRegions];

        waiting.add(0);

        found[0] = true;
        // via[0] = 0;
        pathIncr[0] = INFINITY;

        do {

            int origin = waiting.remove();

            for (Edge e : graph[origin]) {

                if (e != null) {

                    int destination = e.getDestination();
                    int residue = e.getFlow() - graph[origin][destination].getFlow();
                    
                    if ( !found[destination] && residue > 0 ) {

                        // via[destination] = origin;
                        pathIncr[destination] = Math.min(pathIncr[origin], residue);

                        if ( destination == sink )
                            return pathIncr[destination];

                        waiting.add(destination);
                        found[destination] = true;

                    }

                }

            }

        } while ( !waiting.isEmpty() );
            return 0;
    }


}

