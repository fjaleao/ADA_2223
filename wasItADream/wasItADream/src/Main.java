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
        String numbers = rd.readLine();
        String[] num = numbers.split(" ");
        int rows = Integer.parseInt(num[0]);
        int columns = Integer.parseInt(num[1]);
        int nTests = Integer.parseInt(num[2]);
        char[][] board = getBoard(rd, columns, rows);
        Tester t = new Tester(board);
        for (int i = 0; i < nTests; i++) {
            numbers = rd.readLine();
            num = numbers.split(" ");
            int x = Integer.parseInt(num[0]) - 1;
            int y = Integer.parseInt(num[1]) - 1;
            int res = t.resolve(x, y);
            System.out.println(res < 0 ? "Stuck" : res);
        }

    }

    private static char[][] getBoard(BufferedReader rd, int colums, int rows) throws IOException {
        char[][] board = new char[rows][colums];
        for (int i = 0; i < rows; i++) {
            board[i] = rd.readLine().toCharArray();
        }

        return board;
    }
}