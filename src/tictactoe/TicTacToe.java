package tictactoe;

import java.util.Scanner;

/**
 * Tic-Tac-Toe
 * 4/24/18
 * @author Jeremy Weidner
 */
public class TicTacToe {

    public static final Scanner stdin = new Scanner(System.in);

    public static int getPlayerRowColInput() {
        do {
            try {
                int rowOrCol = stdin.nextInt();

                if (rowOrCol < 0) {
                    System.out.print(rowOrCol + " is too low, please try again: ");
                } else if (rowOrCol >= GameBoard.BOARD_SIZE) {
                    System.out.print(rowOrCol + " is too high, please try again: ");
                } else {
                    return rowOrCol;
                }
            } catch (Exception e) {
                System.out.print("Invalid input, please try again: ");
                stdin.nextLine();
            }
        } while (true);
    }

    public static int[] getMoveFromPlayer(GameBoard board) {
        int row;
        int col;
        boolean isMoveValid = false;
        int[] results = new int[2];
        
        if (board.getIsPlayerOnesTurn()) {
            System.out.println("Player One's Turn (X)");
        } else {
            System.out.println("Player Two's Turn (O)");
        }

        do {
            System.out.print("Enter row: ");
            row = getPlayerRowColInput();

            System.out.print("Enter column: ");
            col = getPlayerRowColInput();

            isMoveValid = board.canPlaceToken(row, col);
            if (!isMoveValid) {
                System.out.println("That spot is already taken! "
                        + "Please choose another.");
            }

        } while (!isMoveValid);
        
        results[0] = row;
        results[1] = col;
        
        return results;
    }

    public static void playRound() {

        GameBoard board = new GameBoard();
        BoardToken winner;

        do {

            board.printBoard();
            int[] rowAndCol = getMoveFromPlayer(board);
            int row = rowAndCol[0];
            int col = rowAndCol[1];
            
            BoardToken token;
            
            if (board.getIsPlayerOnesTurn()) {
                token = BoardToken.createXToken();
            } else {
                token = BoardToken.createOToken();
            }
            
            board.placeToken(token, row, col);

        } while (!board.isGameOver());

        winner = board.getGameWinner();
        
        if (winner.equals(BoardToken.createXToken())) {
            System.out.println("Player one wins!!!");
        } else if (winner.equals(BoardToken.createOToken())) {
            System.out.println("Player two wins!!!");
        } else {
            System.out.println("EVERYONE LOSES HAHAHAHA");
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean keepPlaying = true;
        char userInput;

        System.out.println("Let's play a game of Tic-Tac-Toe!");

        do {

            playRound();

            System.out.print("Do you want to keep playing (y/n)? ");
            stdin.nextLine();
            userInput = stdin.nextLine().toLowerCase().charAt(0);

            keepPlaying = userInput == 'y';

        } while (keepPlaying);

    }

}
