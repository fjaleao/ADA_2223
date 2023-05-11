import java.util.LinkedList;
import java.util.Queue;

public class Tester {
    
    public static final char VALID_POS = '.';
    public static final char GOAL = 'H';
    public static final char OBSTACLE = 'O';
    public static final char BORDER = 'x';

    private final char[][] board;

    private enum Direction {

        NORTH(-1, 0),
        EAST(0, 1),
        SOUTH(1, 0),
        WEST(0, -1);

        public final int deltaRow;
        public final int deltaColumn;

        private Direction(int deltaX, int deltaY) {
            this.deltaRow = deltaX;
            this.deltaColumn = deltaY;
        }

    }

    private boolean[][] found;
    private int nMoves;
    private Queue<Coordinate> waiting;
    private Coordinate current;


    public Tester(char[][] board) {
    	
        this.board = new char[board.length+2][board[0].length+2];
        
        for (int i = 0; i < this.board.length; i++) {
        	
        	for (int j = 0; j < this.board[0].length; j++) {
        		
        		boolean isBorder = i == 0 || i == this.board.length-1 || j == 0 || j == this.board[0].length-1;
        		
        		this.board[i][j] = isBorder ? BORDER : board[i-1][j-1];
        		
        	}
        	
        }
        
        //debug
        System.out.println();
        for(char[] row : this.board) {
        	for (char c : row)
        		System.out.printf("%c", c);
        	System.out.println();
        }
        
    }
    

    public int resolve(int row, int col) {

        nMoves = -1;
        found = new boolean[board.length][board[0].length];
        found[row][col] = true;
        waiting = new LinkedList<>();
        current = new Coordinate(row, col, board[row][col], 0);
        
        waiting.add(current);

        do {

            current = waiting.remove();
            
            System.out.printf("Current node: (%d, %d)\n", current.getRow(), current.getColumn());
            
            nMoves = current.getLevel();

            if (current.getType() == GOAL)
                return nMoves;

            for (Direction d : Direction.values()) {
            		Coordinate nextNode = exploreDirection(d, current);
            		if (nextNode != null) {
		                waiting.add(nextNode);
		                System.out.printf("Added (%d, %d) to waiting\n", nextNode.getRow(), nextNode.getColumn());
            		}
            }

        } while (!waiting.isEmpty());

        return -1;
    }

    private Coordinate exploreDirection(Direction d, Coordinate current) {
        
        int row = current.getRow();
        int col = current.getColumn();

        for (;;) {
//        	System.out.printf("Currently at: (%d, %d)\nNext step: (%d, %d)\tvalue:%c\n", row, col, row + d.deltaRow, col + d.deltaColumn, board[row + d.deltaRow][col + d.deltaColumn]);
            
            if (board[row + d.deltaRow][col + d.deltaColumn] == BORDER) {
            	System.out.printf("Not this way! (%d, %d)\n", row + d.deltaRow, col + d.deltaColumn);
                return null; // reached the end of the board
            }

            if (board[row + d.deltaRow][col + d.deltaColumn] != OBSTACLE) {

                // go in direction
                row += d.deltaRow;
                col += d.deltaColumn;
                found[row][col] = true;

                if (board[row][col] == GOAL)
                    break; // found objective

            } else {
            	if (found[row + d.deltaRow][col + d.deltaColumn])
            		return null; // stopped at a known pathway
            	break;
            }

        }

        return new Coordinate(row, col, board[row][col], nMoves+1);

    }

}
