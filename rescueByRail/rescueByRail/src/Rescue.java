import java.util.*;

public class Rescue {

    private static final int ORIGIN = 1;
    private static final int INFINITY = Integer.MAX_VALUE;


    private int nRegions;
    private Map<Integer, List<Edge>> graph;


    public Rescue(int nRegions) {

        this.nRegions = 2*nRegions + ORIGIN;
        
        //Initialize graph
        this.graph = new HashMap<>();
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

        List<Edge> outIncidentEdges = graph.get(exitId1);
        if ( outIncidentEdges == null ) {
            outIncidentEdges = new ArrayList<>();
            graph.put(exitId1, outIncidentEdges);
        }
        outIncidentEdges.add(edge);

        List<Edge> inIncidentEdges = graph.get(entryId2);
        if ( inIncidentEdges == null ) {
            inIncidentEdges = new ArrayList<>();
            graph.put(entryId2, inIncidentEdges);
        }
        inIncidentEdges.add(reverse);
        
    }


    public int edmondsKarp(int sink) {

        int sinkExit = sink*2-1;
        int[][]flow = new int[nRegions][nRegions];
        int[] via = new int[nRegions];
        int flowValue = 0;
        int increment;

        while ( ( increment = findPath(sinkExit, via, flow) ) != 0 ) {

            flowValue += increment;

            // Update flow.
            int node = sinkExit;
            while ( node != 0 ) {

                int origin = via[node];
                flow[origin][node] += increment;
                flow[node][origin] += -increment;
                node = origin;

            }

        }

        return flowValue;

    }
    

    int findPath(int sink, int[] via, int[][]flow) {

        Queue<Integer> waiting = new LinkedList<>();
        boolean[] found = new boolean[nRegions];
        int[] pathIncr = new int[nRegions];

        waiting.add(0);

        found[0] = true;
        via[0] = 0;
        pathIncr[0] = INFINITY;

        do {

            int origin = waiting.remove();
            List<Edge> outIncidentEdges = graph.get(origin);

            for (Edge e : outIncidentEdges) {
                int destination = e.getDestination();
                int residue = e.getCapacity() - flow[origin][destination];
                    
                if ( !found[destination] && residue > 0 ) {

                    via[destination] = origin;
                    pathIncr[destination] = Math.min(pathIncr[origin], residue);

                    if ( destination == sink )
                        return pathIncr[destination];

                    waiting.add(destination);
                    found[destination] = true;

                    }

            }

        } while ( !waiting.isEmpty() );
            return 0;
    }


}

