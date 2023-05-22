import java.util.*;

public class Rescue {
    private static final int ORIGIN = 1;
    private int nRegions;
    private List<Edge>[] graph;

    public Rescue(int nRegions) {
        this.nRegions = nRegions;
        //Initialize graph
        this.graph = new List[2*nRegions + ORIGIN];
        for(int i = 0; i < 2*nRegions + ORIGIN; i++){
            graph[i] = new LinkedList<>();
        }
    }

    public void createNode(int id, int population, int departure) {
        int exitId = id*2;
        int entryId = exitId-1;
        //Origin node to node and vice versa
        Edge outOrigin = new Edge(0, entryId, population);
        Edge inOrigin = new Edge(entryId, 0, 0);
        graph[0].add(outOrigin);
        graph[0].add(inOrigin);
        graph[entryId].add(outOrigin);
        graph[entryId].add(inOrigin);
        //Entry node to exit node and vice versa
        Edge inExit = new Edge(entryId, exitId , departure);
        Edge outExit = new Edge(exitId, entryId, 0);
        graph[entryId].add(inExit);
        graph[entryId].add(outExit);
        graph[exitId].add(inExit);
        graph[exitId].add(outExit);
    }

    public void connect(int id1, int id2) {
        int exitId1 = id1*2;
        int entryId1 = exitId1-1;
        int exitId2 = id2*2;
        int entryId2 = exitId2-1;
        Edge out1 = new Edge(exitId1, entryId2, Integer.MAX_VALUE);
        Edge in1 =  new Edge(entryId2, exitId1, 0);
        Edge out2 = new Edge(exitId2, entryId1, Integer.MAX_VALUE);
        Edge in2 =  new Edge(entryId1, exitId2, 0);
        
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

