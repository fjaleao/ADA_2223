import java.util.LinkedList;
import java.util.Queue;

public class Tester {
    
    public static final char VALID_POS = '.';
    public static final char GOAL = 'H';
    public static final char OBSTACLE = 'O';
    public static final char BORDER = 'x';

    private final char[][] board;

    private enum Direction {

        NORTH(-1, 0, "NORTH"),
        EAST(0, 1, "EAST"),
        SOUTH(1, 0, "SOUTH"),
        WEST(0, -1, "WEST");

        public final int deltaRow;
        public final int deltaColumn;
        public final String value; // added for debug

        private Direction(int deltaX, int deltaY, String value) {
            this.deltaRow = deltaX;
            this.deltaColumn = deltaY;
            this.value = value;
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
        // System.out.println();
        // for(char[] row : this.board) {
        // 	for (char c : row)
        // 		System.out.printf("%c", c);
        // 	System.out.println();
        // }
        
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
            
            nMoves = current.getLevel();

            if (current.getType() == GOAL)
                return nMoves;

            for (Direction d : Direction.values()) {
            		// System.out.println("Exploring " + d.value);
            		Coordinate nextNode = exploreDirection(d, current);
            		if (nextNode != null) {
		                waiting.add(nextNode);
		                // System.out.printf("From %s added (%d, %d) to waiting\n\n",d.value, nextNode.getRow(), nextNode.getColumn());
            		}
            }

        } while (!waiting.isEmpty());

        return -1;
    }

    private Coordinate exploreDirection(Direction d, Coordinate current) {
        
        int row = current.getRow();
        int col = current.getColumn();

        for (;;) {
//        	System.out.printf("Currently at: (%d, %d)\nNext step: (%d, %d)\tvalue:%c \t%s found\n\n", 
//        			row, col, row + d.deltaRow, col + d.deltaColumn, board[row + d.deltaRow][col + d.deltaColumn],
//        			found[row + d.deltaRow][col + d.deltaColumn] ? "" : "not");
            
            if (board[row + d.deltaRow][col + d.deltaColumn] == BORDER) {
            	// System.out.printf("Not this way! %s\n\n", d.value);
                return null; // reached the end of the board
            }

            if (board[row + d.deltaRow][col + d.deltaColumn] != OBSTACLE) {
            
            	found[row][col] = true;

                // go in direction
            	// System.out.printf("Moving %s\n", d.value);
                row += d.deltaRow;
                col += d.deltaColumn;

                if (board[row][col] == GOAL) {
                	// System.out.println("Objective found!");
                    break; // found objective
                }

            } else {
            	// System.out.printf("Stopped at ");
            	if (found[row][col]) {
            		// System.out.println("known path.\n");
            		return null; // stopped at a known pathway
            	}
            	// System.out.println("new node, adding to waiting...");
            	found[row][col] = true;
            	break;
            }

        }

        return new Coordinate(row, col, board[row][col], nMoves+1);

    }

}
