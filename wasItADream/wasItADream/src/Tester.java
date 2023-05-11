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
           if(up-1 >= 0 && !found[up-1][cY]){
               while(up-1 >= 0 && board[up-1][cY] != 'O' && board[up-1][cY] != 'H'){
                   found[up][cY] = true;
                   up--;
               }
           }
           if(up-1 >= 0) {
               char upChar = board[up - 1][cY];
               if (upChar == 'H')
                   return c.getCount() + 1;
               else if (board[up - 1][cY] == 'O' && !found[up][cY]) {
                   waiting.add(new Coordinate(up, cY, c.getCount() + 1));
               }
           }

           //go down
           int down = cX;
           if(down+1 < board.length && !found[down+1][cY]){
               while(down+1 < board.length && board[down+1][cY] != 'O' && board[down+1][cY] != 'H'){
                   found[down][cY] = true;
                   down++;
               }
           }
           if(down+1 < board.length) {
               char downChar = board[down+1][cY];
               if (downChar == 'H')
                   return c.getCount() + 1;
               else if (board[down+1][cY] == 'O' && !found[down][cY]) {
                   waiting.add(new Coordinate(down, cY, c.getCount() + 1));
               }
           }

           //go left
           int left = cY;
           if(left-1 >= 0 && !found[cX][left-1]){
                while(left-1 >= 0 && board[cX][left-1] != 'O' && board[cX][left-1] != 'H'){
                     found[cX][left] = true;
                     left--;
                }
           }
           if(left-1 >= 0) {
               char leftChar = board[cX][left-1];
               if (leftChar == 'H')
                   return c.getCount() + 1;
               else if (board[cX][left-1] == 'O' && !found[cX][left]) {
                   waiting.add(new Coordinate(cX, left, c.getCount() + 1));
               }
           }

           //go right
           int right = cY;
           if(right+1 < board[cX].length && !found[cX][right+1]){
                while(right+1 < board[cX].length && board[cX][right+1] != 'O' && board[cX][right+1] != 'H'){
                     found[cX][right] = true;
                     right++;
                }
           }
           if(right+1 < board[cX].length) {
               char rightChar = board[cX][right+1];
               if (rightChar == 'H')
                   return c.getCount() + 1;
               else if (board[cX][right+1] == 'O' && !found[cX][right]) {
                   waiting.add(new Coordinate(cX, right, c.getCount() + 1));
               }
           }
        }while(!waiting.isEmpty());

        return -1;
    }

}
