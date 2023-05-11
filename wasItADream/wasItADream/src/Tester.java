import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

public class Tester {
    private final char[][] board;
    private int result;

    public Tester(char[][] board) {
        this.board = board;
    }


    public int resolve(int x, int y) {
        boolean[][] found = new boolean[board.length][board[0].length];
        Queue<Coordinate> waiting = new LinkedList<>();
        Coordinate current = new Coordinate(x, y, 0);
        waiting.add(current);
        do{
           Coordinate c = waiting.remove();
           int cX = c.getAxis();
           int cY = c.getOrdinate();
           found[cX][cY] = true;
           if(board[cX][cY] == 'H')
               return c.getCount();
           boolean newPath = false;
           //go up
           int up = cX;
           while(up >= 0 && board[up][cY] != 'O' && board[up][cY] != 'H'){
               if(!found[up][cY]) {
                   found[up][cY] = true;
                   newPath = true;
               }
               up--;
           }
           if(up >= 0) {
               char upChar = board[up][cY];
               if (upChar == 'H')
                   return c.getCount() + 1;
               else if (board[up][cY] == 'O' && newPath) {
                   waiting.add(new Coordinate(up+1, cY, c.getCount() + 1));
               }
           }

           //go down
           newPath = false;
           int down = cX;
           while(down < board.length && board[down][cY] != 'O' && board[down][cY] != 'H'){
               if(!found[down][cY]) {
                   found[down][cY] = true;
                   newPath = true;
               }
               down++;
           }
           if(down+1 < board.length) {
               char downChar = board[down][cY];
               if (downChar == 'H')
                   return c.getCount() + 1;
               else if (board[down][cY] == 'O' && newPath) {
                   waiting.add(new Coordinate(down-1, cY, c.getCount() + 1));
               }
           }

           //go left
           newPath = false;
           int left = cY;
           while(left >= 0 && board[cX][left] != 'O' && board[cX][left] != 'H'){
               if(!found[cX][left]) {
                   found[cX][left] = true;
                   newPath = true;
               }
               left--;
           }
           if(left >= 0) {
               char leftChar = board[cX][left];
               if (leftChar == 'H')
                   return c.getCount() + 1;
               else if (board[cX][left] == 'O' && newPath) {
                   waiting.add(new Coordinate(cX, left+1, c.getCount() + 1));
               }
           }

           //go right
           newPath = false;
           int right = cY;
           while(right < board[cX].length && board[cX][right] != 'O' && board[cX][right] != 'H') {
               if (!found[cX][right]) {
                   found[cX][right] = true;
                   newPath = true;
               }
               right++;
           }
           if(right < board[cX].length) {
               char rightChar = board[cX][right];
               if (rightChar == 'H')
                   return c.getCount() + 1;
               else if (board[cX][right] == 'O' && newPath) {
                   waiting.add(new Coordinate(cX, right-1, c.getCount() + 1));
               }
           }
        }while(!waiting.isEmpty());

        return -1;
    }

}
