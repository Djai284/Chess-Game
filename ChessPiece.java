
public class ChessPiece {
    protected int value;
    protected String name;
    protected boolean isAlive;
    protected boolean isWhite;
    protected boolean hasMoved;


    public ChessPiece(int val, String piece, boolean isWhite){
        this.value = val;
        this.name = piece;
        this.isAlive = true;
        this.isWhite = isWhite;
        this.hasMoved = false;
    }

    public void hasMoved(){
        this.hasMoved = true;
    }

    public boolean getMoved(){
        return hasMoved;
    }

    public void Remove(){
        this.isAlive = false;
    }

    public int getValue(){
        return value;
    }

    public boolean getColor(){
        return isWhite;
    }

    public String getName(){
        return name;
    }

    public int compareColor(ChessPiece c){
       return Boolean.compare(this.getColor(), c.getColor());
    }
}