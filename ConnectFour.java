import java.util.Scanner;

public class ConnectFour{
    static final int ROWS = 6;
    static final int COLS = 7;
    static char[][] board = new char[ROWS][COLS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initBoard();

        boolean gameOver = false;
        char currentPlayer = 'X';

        while (!gameOver) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter column (0-6): ");
            int col;

            // Input validation
            while (true) {
                if (scanner.hasNextInt()) {
                    col = scanner.nextInt();
                    if (col >= 0 && col < COLS && board[0][col] == ' ') {
                        break;
                    } else {
                        System.out.println("Invalid column. Try again.");
                    }
                } else {
                    scanner.next(); // discard invalid input
                    System.out.println("Please enter a valid number.");
                }
            }

            int row = dropDisc(currentPlayer, col);

            if (checkWin(currentPlayer, row, col)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (isDraw()) {
                printBoard();
                System.out.println("The game is a draw.");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }

    static void initBoard() {
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                board[r][c] = ' ';
    }

    static void printBoard() {
        System.out.println("\nCurrent Board:");
        for (int r = 0; r < ROWS; r++) {
            System.out.print("|");
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c] + "|");
            }
            System.out.println();
        }
        System.out.println(" 0 1 2 3 4 5 6 ");
    }

    static int dropDisc(char player, int col) {
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == ' ') {
                board[r][col] = player;
                return r;
            }
        }
        return -1; 
    }

    static boolean checkWin(char player, int row, int col) {
        return checkDirection(player, row, col, 1, 0)  
            || checkDirection(player, row, col, 0, 1)  
            || checkDirection(player, row, col, 1, 1) 
            || checkDirection(player, row, col, 1, -1); 
    }

    static boolean checkDirection(char player, int row, int col, int deltaRow, int deltaCol) {
        int count = 1;
        count += countDiscs(player, row, col, deltaRow, deltaCol);
        count += countDiscs(player, row, col, -deltaRow, -deltaCol);
        return count >= 4;
    }

    static int countDiscs(char player, int row, int col, int deltaRow, int deltaCol) {
        int count = 0;
        int r = row + deltaRow;
        int c = col + deltaCol;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == player) {
            count++;
            r += deltaRow;
            c += deltaCol;
        }
        return count;
    }

    static boolean isDraw() {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c] == ' ')
                return false;
        }
        return true;
    }
}