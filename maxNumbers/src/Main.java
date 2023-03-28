import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException{
        // System.out.println("Hello world!");

        int maxValue;

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int nChildren = Integer.parseInt(rd.readLine());

        maxValue = processFindings(rd, nChildren);

        System.out.println(maxValue);

        rd.close();


    }

    private static int processFindings(BufferedReader rd, int nChildren) throws IOException {

        int result = Integer.MIN_VALUE;

        for (int i = 0; i < nChildren; i++) {

            String[] values = rd.readLine().split(" ");

            int nSticks = Integer.parseInt(values[0]);

            for (int j = 0; j < nSticks; j++) {
                int current = Integer.parseInt(values[j+1]);
                result = current >= result ? current : result;
            }

        }
        return result;
    }
}