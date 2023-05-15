import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        try{
            String numbers = rd.readLine();
            String[] num = numbers.split(" ");
            int nRegions = Integer.parseInt(num[0]);
            int nDirectLinks = Integer.parseInt(num[1]);
            List<String> regions = new ArrayList<>();
            List<String> directLinks = new ArrayList<>();
            for(int i = 0; i < nRegions; i++){
                numbers = rd.readLine();
                regions.add(numbers);
            }
            for(int i = 0; i < nDirectLinks; i++){
                numbers = rd.readLine();
                directLinks.add(numbers);
            }
            int saveRegion = Integer.parseInt(rd.readLine());
            Rescue rescue = new Rescue(regions, directLinks, saveRegion);
            int result = rescue.resolve();
            rd.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}