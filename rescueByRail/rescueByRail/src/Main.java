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
            Rescue rescue = new Rescue(nRegions);


            for(int i = 1; i <= nRegions; i++){
                numbers = rd.readLine();
                num = numbers.split(" ");
                int population = Integer.parseInt(num[0]);
                int departure = Integer.parseInt(num[1]);
                rescue.createNode(i, population, departure);
                //rescue.createRegion(i, population, departure);
            }

            for(int i = 0; i < nDirectLinks; i++){
                numbers = rd.readLine();
                num = numbers.split(" ");
                int region1 = Integer.parseInt(num[0]);
                int region2 = Integer.parseInt(num[1]);
                rescue.addEdge(region1 ,region2);
            }

            int saveRegion = Integer.parseInt(rd.readLine());
            //int result = rescue.resolve(saveRegion);

            System.out.println(0);

            rd.close();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
}