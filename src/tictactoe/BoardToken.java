package tictactoe;

/**
 *
 * @author jeremy
 */
public class BoardToken {
    
    private char token;

    public static BoardToken createXToken() {
        return new BoardToken('X');
    }
    
    public static BoardToken createOToken() {
        return new BoardToken('O');
    }
    
    public static BoardToken createEmptyToken() {
        return new BoardToken(' ');
    }
    
    private BoardToken(char token) {
        this.token = token;
    }
    
    public String toString() {
        return String.valueOf(this.token);
    }
    
    public boolean equals(BoardToken other) {
        return this.token == other.token;
    }
    
}
