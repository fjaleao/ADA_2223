
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



 /*   // private Map<Integer, List<Region>> regionsConnections;
    private static final int ORIGIN = 1;
    private Region[] regions;
    private List<Region>[] graph;

    public Rescue(int nRegions) {
        // this.regionsConnections = new HashMap<>();
        this.regions = new Region[nRegions + ORIGIN];
        this.graph = new List[nRegions + ORIGIN];

    }

    public void createDirectLinks(int id1, int id2) {
        // Region region1 = regions[id1];
        // Region region2 = regions[id2];
        // regionsConnections.get(region1.getId()).add(region2);
        // regionsConnections.get(region2.getId()).add(region1);
        graph[id1].add(regions[id2]);
        graph[id2].add(regions[id1]);

    }

    public void createRegion(int id, int population, int departure) {
        
        Region region = new Region(population, departure, id);

        regions[id] = region;
        graph[id] = new LinkedList<>();
        graph[id].add(regions[0]);
        graph[0].add(region);


    }

    public int edmondsKarp(int saveRegionID) {
        List<Region>[] network = buildNetwork();
        int numRegions = network.length;
        Region[][] flow = new Region[numRegions][numRegions];
        return 0;
    }

    private List<Region>[] buildNetwork() {
        List<Region>[] network = graph.clone();
        return network;
    }

    public int resolve(int saveRegionID) {
        Region saveRegion = regions[saveRegionID];
        List<Region> flow = graph[saveRegionID];
        Deque<Region> queue = new LinkedList<>();
        for(Region region : flow){
            queue.add(region);
        }
        while(!queue.isEmpty()){
            Region region = queue.poll();

        }
        return 0;
    }*/

}

