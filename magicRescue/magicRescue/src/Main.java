import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(rd.readLine());
        resolve(lines, rd);

    }

    /**
     * This method will read an input line describing a route and calculates the minimum number of times Harry and
     * Ron need to traverse the route defined in that route.
     */
    private static void resolve(int lines, BufferedReader rd) throws IOException {
        for(int i = 0; i < lines; i++){
            String line = rd.readLine();
            MagicRescue mr = new MagicRescue(line);
        }

    }
}