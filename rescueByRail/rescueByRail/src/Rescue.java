import java.util.*;

public class Rescue {
    private Map<Integer, List<Region>> regionsConnections;
    private Region[] regions;



    public Rescue(int nRegions) {
        this.regionsConnections = new HashMap<>();
        this.regions = new Region[nRegions];
    }

    public void createDirectLinks(int id1, int id2) {
        Region region1 = regions[id1];
        Region region2 = regions[id2];
        regionsConnections.get(region1.getId()).add(region2);
        regionsConnections.get(region2.getId()).add(region1);

    }

    public void createRegions(int population, int departure) {
        int id = regionsConnections.size();
        Region region = new Region(population, departure, id);
        regions[id] = region;
        regionsConnections.put(region.getId(), new ArrayList<>());

    }


    public int resolve(int saveRegion) {

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rescue rescue)) return false;
        return regionsConnections.equals(rescue.regionsConnections);
    }

}

