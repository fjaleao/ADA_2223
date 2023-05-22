
public class Rescue {

    private static final int ORIGIN = 1;
    private static final int INFINITY = Integer.MAX_VALUE;

    private Edge[][] graph;

    public Rescue(int nRegions) {
        
        //Initialize graph
        int nPos = 2*nRegions + ORIGIN;
        this.graph = new Edge[nPos][nPos];
        // {0, 1_e, 1_s, 2_e, 2_s, ..., nRegions_e, nRegions_s}

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

    public void connect (int id1, int id2, int cap, int revCap) {

        int exitId1 = id1*2;
        int exitId2 = id2*2;
        int entryId2 = exitId2-1;

        Edge edge = new Edge(entryId2, cap);
        Edge reverse = new Edge (exitId1, revCap);

        graph[exitId1][entryId2] = edge;
        graph[entryId2][exitId1] = reverse;
        
    }

}

