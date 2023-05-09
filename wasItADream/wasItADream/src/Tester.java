import java.util.LinkedList;
import java.util.Queue;

public class Tester {
    private final char[][] board;
    private final boolean[][] found;

    public Tester(char[][] board) {
        this.board = board;
        this.found = new boolean[board.length][board[0].length];
    }


    public int resolve(int x, int y) {
        Queue<Coordinate> waiting = new LinkedList<>();
        Coordinate current = new Coordinate(x, y);
        waiting.add(current);
        found[x][y] = true;
        int count = auxresolve(waiting, 0);
        return count;
    }

    private int auxresolve(Queue<Coordinate> waiting, int count){
        Coordinate c = waiting.poll();


        return 0;
    }
}
