import java.util.List;

public class Rescue {
    private final int saveRegion;

    public Rescue(List regions, List directLinks, int saveRegion) {
        this.saveRegion = saveRegion;
        createRegions(regions);
        createDirectLinks(directLinks);
    }

    private void createDirectLinks(List<String> directLinks) {
        int nDirectLinks = directLinks.size();
        for(int i = 0; i < nDirectLinks; i++){
            String numbers = directLinks.get(i);
            String[] num = numbers.split(" ");
            int region1 = Integer.parseInt(num[0]);
            int region2 = Integer.parseInt(num[1]);

        }
    }

    private void createRegions(List<String> regions) {
        int nRegions = regions.size();
        for(int i = 0; i < nRegions; i++){
            String numbers = regions.get(i);
            String[] num = numbers.split(" ");
            int population = Integer.parseInt(num[0]);
            int departure = Integer.parseInt(num[1]);
            Region region = new Region(population, departure);
        }
    }


    public int resolve() {
        return 0;
    }
}
