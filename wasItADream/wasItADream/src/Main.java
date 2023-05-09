import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final char BOUNDARIES = 'B';
    private static final int BONDARY = 2;

    private static final char SPACE = ' ';
    private static final char CARRIAGE_RETURN = '\n';

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        try {
            int rows = getNextInt(rd, SPACE);
            int columns = getNextInt(rd, SPACE);
            int nTests = getNextInt(rd, CARRIAGE_RETURN);
            char[][] board = getBoard(rd, columns, rows);
            Tester t = new Tester(board);
            for (int i = 0; i < nTests; i++) {
                int res = t.resolve(getNextInt(rd, SPACE), getNextInt(rd, CARRIAGE_RETURN));
                System.out.println(res < 0 ? "stuck" : res);
            }
        } catch (Exception e) {

        }

    }

    private static int getNextInt(BufferedReader rd, char delimiter) throws IOException {
        String rows = "";
        char b = (char) rd.read();
        while (b != delimiter) {
            rows += b;
            b = (char) rd.read();
        }
        return Integer.parseInt(rows);
    }

    private static char[][] getBoard(BufferedReader rd, int colums, int rows) throws IOException {
        char[][] board = new char[rows][colums];
        for (int i = 0; i < rows; i++) {
            board[i] = rd.readLine().toCharArray();
        }

        return board;
    }
}