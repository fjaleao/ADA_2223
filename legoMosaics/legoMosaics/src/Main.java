import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

<<<<<<< HEAD:legoMosaics/src/Main.java
    /**
     *
     */
    private static final char EMPTY_STUB = '.';
    private static final char NONE = ' ';

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int rows = getNRows(rd);
        int columns = getNcolumns(rd);

        int[]nOccurrences = getMosaic(rd, rows, columns);
        System.out.println(nOccurrences.length);
        System.out.println(nOccurrences[0]);
        System.out.println(nOccurrences[1]);
        System.out.println(nOccurrences[2]);
        System.out.println(nOccurrences[3]);

      /*  String line = rd.readLine();

        String[] constraints = line.split(" ");
        int row = Integer.parseInt(constraints[0]);
        int column = Integer.parseInt(constraints[1]);

        int[] nOccourences = new int[column + 1];
        int maxSeq = 0;*/

    }

    private static int getNRows(BufferedReader rd) throws IOException{
=======
    private static int getNRows(BufferedReader rd) throws IOException {
>>>>>>> a8cc9ab215248880fc8d4f9869699f78bb620af2:legoMosaics/legoMosaics/src/Main.java
        String rows = "";
        char b = (char) rd.read();
        while (b != ' ') {
            rows += b;
            b = (char) rd.read();
        }
        return Integer.parseInt(rows);
    }

    private static int getNcolumns(BufferedReader rd) throws IOException {
        String columns = "";
        char b = (char) rd.read();
        while (b != '\n') {
            columns += b;
            b = (char) rd.read();
        }
        return Integer.parseInt(columns);
    }

<<<<<<< HEAD:legoMosaics/src/Main.java
    /*
     * This method simplifies situations where there are different sequences with the same
     * length by ommiting similar operations
     * 
     * Example:
     * 
     * | B B B G W W    -> w(3) . w(1) . w(2)
     * | B B B          -> w(3)
     * | G W W          -> w(1) . w(2)
     * 
     * R = w(3).w(1).w(2).w(3).w(1).w(2) = w(3)^2 . w(2)^2 . w(1)^2
     */
    private static int[] getMosaic(BufferedReader rd, int rows, int columns) throws IOException{
        int[] seqMap = new int[columns + 1];
        int seqLen = 0;
        char current = NONE;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++) {
                char stub = (char)rd.read();
                if(stub != EMPTY_STUB){
                    if(seqLen == 0){
                        current = stub;
                        seqLen++;
                    }
                    else{
                        if(current == stub)
                            seqLen++;
                        else{
                            seqMap[seqLen]++;
                            current = stub;
                            seqLen = 1;
                        }
                    }
                }else if (seqLen != 0) {
                    seqMap[seqLen]++;
                    current = NONE;
                    seqLen = 0;

                }
            }
            if(seqLen != 0){
                seqMap[seqLen]++;
                seqLen = 0;
=======
    private static int[] getMosaic(BufferedReader rd, int colums, int rows) throws IOException {
        int[] nOccurrences = new int[colums];
        int occurrence = 0;
        int numberOccurrences = -1;
        char curLetter = ' ';

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                char b = (char) rd.read();
                if (b != '.') {
                    if (numberOccurrences == -1) {
                        curLetter = b;
                        numberOccurrences++;
                    } else {
                        if (curLetter == b)
                            numberOccurrences++;
                        else {
                            nOccurrences[numberOccurrences]++;
                            curLetter = b;
                            numberOccurrences = 0;
                        }
                    }
                } else if (numberOccurrences != -1) {
                    nOccurrences[numberOccurrences]++;
                    curLetter = ' ';
                    numberOccurrences = -1;

                }
            }
            if (numberOccurrences != -1) {
                nOccurrences[numberOccurrences]++;
                numberOccurrences = -1;
>>>>>>> a8cc9ab215248880fc8d4f9869699f78bb620af2:legoMosaics/legoMosaics/src/Main.java
            }
            // reads a \n
            rd.read();

        }
<<<<<<< HEAD:legoMosaics/src/Main.java
        return  seqMap;
=======
        return nOccurrences;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int rows = getNRows(rd);
        int columns = getNcolumns(rd);
        int[] nOccurrences = getMosaic(rd, columns, rows);
        LegoMosaics lego = new LegoMosaics(nOccurrences, columns);
        System.out.print(lego.getResult());
>>>>>>> a8cc9ab215248880fc8d4f9869699f78bb620af2:legoMosaics/legoMosaics/src/Main.java
    }
}