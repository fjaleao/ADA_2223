import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int getNRows(BufferedReader rd) throws IOException{
        String rows = "";
        char b = (char) rd.read();
        while (b != ' ') {
            rows += b;
            b = (char) rd.read();
        }
        return Integer.parseInt(rows);
    }

    private static int getNcolumns(BufferedReader rd) throws IOException{
        String columns = "";
        char b = (char)rd.read();
        while (b != '\n') {
            columns += b;
            b = (char) rd.read();
        }
        return Integer.parseInt(columns);
    }

    private static int[] getMosaic(BufferedReader rd, int colums, int rows) throws IOException{
        int[] nOccurrences = new int[colums + 1];
        int numberOccurrences = 0;
        char curLetter = ' ';

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < colums; j++) {
                char b = (char)rd.read();
                if(b != '.'){
                    if(numberOccurrences == 0){
                        curLetter = b;
                        numberOccurrences++;
                    }
                    else{
                        if(curLetter == b)
                            numberOccurrences++;
                        else{
                            nOccurrences[numberOccurrences]++;
                            curLetter = b;
                            numberOccurrences = 1;
                        }
                    }
                }else if (numberOccurrences != 0) {
                    nOccurrences[numberOccurrences]++;
                    curLetter = ' ';
                    numberOccurrences = 0;

                }
            }
            if(numberOccurrences != 0){
                nOccurrences[numberOccurrences]++;
                numberOccurrences = 0;
            }
           // reads a \n
           rd.read();

        }
        return  nOccurrences;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int rows = getNRows(rd);
        int columns = getNcolumns(rd);
        int[]nOccurrences = getMosaic(rd, columns, rows);
        System.out.println(nOccurrences.length);
        System.out.println(nOccurrences[0]);
        System.out.println(nOccurrences[1]);
        System.out.println(nOccurrences[2]);
        System.out.println(nOccurrences[3]);






    }
}