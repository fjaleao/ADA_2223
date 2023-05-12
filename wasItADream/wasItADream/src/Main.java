import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final char SPACE = ' ';
    private static final char CARRIAGE_RETURN = '\r';
    private static final char LINE_FEED = '\n';

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        
        try {
        	
            int rows = getNextInt(rd, SPACE);
            int columns = getNextInt(rd, SPACE);
            int nTests = getNextInt(rd, CARRIAGE_RETURN);
            rd.skip(2); // skip line feed
            
            char[][] board = getBoard(rd, columns, rows);
            
            Tester t = new Tester(board);
            
            for (int i = 0; i < nTests; i++) {
            	
                int x = getNextInt(rd, SPACE);
                int y = getNextInt(rd, CARRIAGE_RETURN);
                
                // System.out.printf("\nResult for test %d:\n", i+1);
                
                int res = t.resolve(x, y);
                System.out.println(res < 0 ? "stuck" : res);
                
            }
            
        } catch (Exception e) {
                System.err.println(e.getMessage());
        }

    }
    

    private static int getNextInt(BufferedReader rd, char delimiter) throws IOException {
    	
        String rows = "";
        char b;
        
        do {
        	
            b = (char) rd.read();
            rows += b;
            
        } while (b != delimiter);
        
        return Integer.parseInt(rows.trim());
        
    }

    private static char[][] getBoard(BufferedReader rd, int columns, int rows) throws IOException {
    	
        char[][] board = new char[rows][columns];
        
        for (int i = 0; i < rows; i++) {
        	
            board[i] = rd.readLine().toCharArray();
            
        }

        return board;
    }
}