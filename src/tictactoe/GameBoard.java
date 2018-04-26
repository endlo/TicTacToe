package tictactoe;

/**
 *
 * @author jeremy
 */
public class GameBoard {

    public static final int BOARD_SIZE = 3;

    private BoardToken[][] boardState;
    private boolean isPlayerOnesTurn;
    private BoardToken winner = BoardToken.createEmptyToken();

    public GameBoard() {
        boardState = new BoardToken[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                boardState[i][j] = BoardToken.createEmptyToken();
            }
        }

        isPlayerOnesTurn = true;
    }

    private boolean isWinningLine(
            BoardToken one, BoardToken two, BoardToken three) {
        if (one.equals(BoardToken.createEmptyToken())) {
            return false;
        }
        return one.equals(two) && one.equals(three);
    }

    public boolean isBoardCompletelyFull() {
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (boardState[i][j].equals(BoardToken.createEmptyToken())) {
                    return false;
                }
            }
        }
        return true;
    }

    // TODO
    public boolean isGameOver() {

        // Check if the board is completely full
        if (isBoardCompletelyFull()) {
            return true;
        }

        // Check horizontal wins
        for (int i = 0; i < boardState.length; i++) {
            if (isWinningLine(boardState[i][0],
                    boardState[i][1], boardState[i][2])) {
                winner = boardState[i][0];
                return true;
            }
        }

        // Check vertical wins
        for (int j = 0; j < boardState.length; j++) {
            if (isWinningLine(boardState[0][j],
                    boardState[1][j], boardState[2][j])) {
                winner = boardState[0][j];
                return true;
            }
        }

        // Check diagonal wins
        if (isWinningLine(boardState[0][0],
                boardState[1][1], boardState[2][2])) {
            winner = boardState[0][0];
            return true;
        }
        if (isWinningLine(boardState[2][0],
                boardState[1][1], boardState[0][2])) {
            winner = boardState[2][0];
            return true;
        }

        return false;
    }

    public boolean getIsPlayerOnesTurn() {
        return isPlayerOnesTurn;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < boardState.length; i++) {
            if (i != 0) {
                System.out.println("-----------");
            }
            for (int j = 0; j < boardState[i].length; j++) {
                if (j != 0) {
                    System.out.print("|");
                }
                System.out.print(" " + boardState[i][j].toString() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeToken(BoardToken token, int row, int col) {
        boardState[row][col] = token;
        isPlayerOnesTurn = !isPlayerOnesTurn;
    }

    public boolean canPlaceToken(int row, int col) {
        return boardState[row][col].equals(BoardToken.createEmptyToken());
    }

    public BoardToken getGameWinner() {
        return winner;
    }

}
