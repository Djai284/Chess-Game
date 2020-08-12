public class King extends ChessPiece {
    private int score;
    public King(boolean isWhite){
        super(10, "King", isWhite);
        score = 10;
    }
}