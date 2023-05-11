import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final char BOUNDARIES = 'B';
    private static final int BONDARY = 2;

    private static final char SPACE = ' ';
    private static final char CARRIAGE_RETURN = '\r';

    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Apanahr linhas");
            int rows = getNextInt(rd, SPACE);
        System.out.println("Apanahr colunas");
            int columns = getNextInt(rd, SPACE);
                    System.out.println("Apanahr testes");
            int nTests = getNextInt(rd, CARRIAGE_RETURN);
        System.out.println("Apanahr quadro");
            char[][] board = getBoard(rd, columns, rows);
        System.out.println("Apanahr tester");
            Tester t = new Tester(board);
            for (int i = 0; i < nTests; i++) {
                int res = t.resolve(getNextInt(rd, SPACE) -1, getNextInt(rd, CARRIAGE_RETURN) -1);
                System.out.println(res < 0 ? "Stuck" : res);
            }

    }

    private static int getNextInt(BufferedReader rd, char delimiter) throws IOException {
        String rows = "";
        char b = (char) rd.read();
        while (b != delimiter) {
            rows += b;
            b = (char) rd.read();
        }
        System.out.println("String " + rows);
        int i = Integer.parseInt(rows);
        System.out.println("number " + i);
        return i;
    }

    private static char[][] getBoard(BufferedReader rd, int colums, int rows) throws IOException {
        char[][] board = new char[rows][colums];
        for (int i = 0; i < rows; i++) {
            board[i] = rd.readLine().toCharArray();
        }

        return board;
    }
}