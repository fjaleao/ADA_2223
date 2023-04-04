import java.util.LinkedList;
import java.util.List;

public class Promotions {
    private int minProm, maxProm, numEmployees;

    private List<Integer>[] succ, pred;

    public Promotions(int minProm, int maxProm, int numEmployees) {
        this.minProm = minProm;
        this.maxProm = maxProm;
        this.numEmployees = numEmployees;
        succ = new List[numEmployees];
        pred = new List[numEmployees];

        //NAO UTULIZAR GETS NAS LISTAS LIGADAS
        for(int i=0; i <numEmployees; i++){
            succ[i] = new LinkedList<Integer>();
            pred[i] = new LinkedList<Integer>();
        }
    }

    public void addRule(int employeeX, int employeeY){
        succ[employeeX].add(employeeY);
        pred[employeeY].add(employeeX);
    };

    public int dfsExplore(Boolean[] processed){ //one more input


        return 0;
    };
}
