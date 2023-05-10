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
           //go up
           int up = cX;
           while(up-1 >= 0 && board[up-1][y] != 'O' && board[up-1][y] != 'H'){
               found[up][y] = true;
               up--;
            }
           if(up-1 >= 0) {
               char upChar = board[up - 1][y];
               if (upChar == 'H')
                   return c.getCount() + 1;
               else if (board[up - 1][y] == 'O' && !found[up][y]) {
                   waiting.add(new Coordinate(up, y, c.getCount() + 1));
               }
           }

           //go down
           int down = cX;
           while(down+1 < board.length && board[down+1][y] != 'O' && board[down+1][y] != 'H'){
               found[down][y] = true;
               down++;
           }
           if(down+1 < board.length) {
               char downChar = board[down+1][y];
               if (downChar == 'H')
                   return c.getCount() + 1;
               else if (board[down+1][y] == 'O' && !found[down][y]) {
                   waiting.add(new Coordinate(down, y, c.getCount() + 1));
               }
           }

           //go left
           int left = cY;
           while(left-1 >= 0 && board[x][left-1] != 'O' && board[x][left-1] != 'H'){
               found[x][left] = true;
               left--;
           }
           if(left-1 >= 0) {
               char leftChar = board[x][left-1];
               if (leftChar == 'H')
                   return c.getCount() + 1;
               else if (board[x][left-1] == 'O' && !found[x][left]) {
                   waiting.add(new Coordinate(x, left, c.getCount() + 1));
               }
           }

           //go right
           int right = cY;
           while(right+1 < board[x].length && board[x][right+1] != 'O' && board[x][right+1] != 'H'){
               found[x][right] = true;
               right++;
           }
           if(right+1 < board[x].length) {
               char rightChar = board[x][right+1];
               if (rightChar == 'H')
                   return c.getCount() + 1;
               else if (board[x][right+1] == 'O' && !found[x][right]) {
                   waiting.add(new Coordinate(x, right, c.getCount() + 1));
               }
           }
        }while(!waiting.isEmpty());

        return -1;
    }

}
