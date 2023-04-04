import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int minProm = getNumber(rd);
        int maxProm = getNumber(rd);
        int employees = getNumber(rd);
        Promotions pro = new Promotions(minProm, maxProm, employees);
        int nProRules = getEndLineNumber(rd);
        getRules(rd, pro, nProRules);
        //TODO: print dos resultados
        }

    private static void getRules(BufferedReader rd, Promotions pro, int nProRules) throws IOException {
        for(int i = 0; i< nProRules; i++){
            int employeeX = getNumber(rd);
            int employeeY = getEndLineNumber(rd);
            pro.addRule(employeeX, employeeX);
        }
    }

    private static int getEndLineNumber(BufferedReader rd) throws IOException {
        String columns = "";
        char b = (char)rd.read();
        while (b != '\n') {
            columns += b;
            b = (char) rd.read();
        }
        return Integer.parseInt(columns);
    }

    private static int getNumber(BufferedReader rd) throws IOException {
        String rows = "";
        char b = (char) rd.read();
        while (b != ' ') {
            rows += b;
            b = (char) rd.read();
        }
        return Integer.parseInt(rows);
    }



}