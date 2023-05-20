import java.util.*;

public class Rescue {
    // private Map<Integer, List<Region>> regionsConnections;

    private Region[] regions;
    private List<Region>[] map;

    public Rescue(int nRegions) {
        // this.regionsConnections = new HashMap<>();
        this.regions = new Region[nRegions];
        this.map = new List<>[nRegions];
    }

    public void createDirectLinks(int id1, int id2) {
        // Region region1 = regions[id1];
        // Region region2 = regions[id2];
        // regionsConnections.get(region1.getId()).add(region2);
        // regionsConnections.get(region2.getId()).add(region1);
        int index1 = id1-1;
        int index2 = id2-1;
        map[index1].add(regions[index2]);
        map[index2].add(regions[index1]);

    }

    public void createRegion(int id, int population, int departure) {
        
        Region region = new Region(population, departure, id);

        int index = id-1;

        regions[index] = region;
        map[index] = new List<>();

    }

    public int resolve(int saveRegion) {

        return 0;
    }

}

