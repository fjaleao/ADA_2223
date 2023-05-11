import java.util.LinkedList;
import java.util.Queue;

public class Tester {
    
    private static final char VALID_POS = '.';
    private static final char GOAL = 'H';
    private static final char OBSTACLE = '0';

    private final char[][] board;

    private enum Direction {

        NORTH(0, -1),
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0);

        public final int deltaX;
        public final int deltaY;

        private Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

    }

    private boolean[][] found;
    private int nMoves;


    public Tester(char[][] board) {
        this.board = board;
    }

    public int resolve(int x, int y) {

        nMoves = 0;
        found = new boolean[board.length][board[0].length];
        found[x][y] = true;
        Queue<Coordinate> waiting = new LinkedList<>();
        Queue<Coordinate> nextLevel = new LinkedList<>();
        Coordinate current = new Coordinate(x, y, board[x][y]);
        
        nextLevel.add(current);

        do {

            if (waiting.isEmpty()) {
                waiting = nextLevel;
                nMoves++;
            }

            current = waiting.remove();

            if (current.getType() == GOAL)
                return nMoves;

            for (Coordinate coordinate : getNextNodes(current)) {
                nextLevel.add(coordinate);
            }

        } while (!nextLevel.isEmpty());

        return -1;
    }

    private Coordinate[] getNextNodes(Coordinate current) {
        Coordinate[] res = new Coordinate[4];
        int count = 0;
        Coordinate nextNode;

        for (Direction d : Direction.values())
            if ((nextNode = exploreDirection(d, current)) != null)
                res[count++] = nextNode;
        
        return res;
    }

    private Coordinate exploreDirection(Direction d, Coordinate current) {
        
        int x = current.getX();
        int y = current.getY();

        for (;;) {
            
            if (    x + d.deltaX == 0 || x + d.deltaX == board.length-1 ||
                    y + d.deltaY == 0 || y + d.deltaY == board[0].length-1  )
                return null; // reached the end of the board

            if (    !found[x + d.deltaX][y + d.deltaY] ||
                    (found[x + d.deltaX][y + d.deltaY] && board[x + 2*d.deltaX][y + 2*d.deltaY] != OBSTACLE)    ) {

                if (board[x + d.deltaX][y + d.deltaY] != OBSTACLE) {

                    // go in direction
                    x += d.deltaX;
                    y += d.deltaY;
                    found[x][y] = true;

                    if (board[x][y] == GOAL)
                        break; // found objective

                } else break; // reached obstacle

            }

        }

        return new Coordinate(x, y, board[x][y]);

    }

}
