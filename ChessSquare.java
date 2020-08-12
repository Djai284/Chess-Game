
public class ChessSquare {
    private int row;
    private int column;
    private char col;
    private boolean hasPiece;
    private String color;
    private ChessPiece piece;

    public ChessSquare(int row, int column){
        this.row = row;
        this.column = column;
        convertColumn(column);
        hasPiece = false;
    }

    public void convertColumn(int c){
        switch(c){
            case 0:
                col = 'A';
                break;
            case 1: 
                col = 'B';
                break;
            case 2:
                col = 'C';
                break;
            case 3:
                col = 'D';
                break;
            case 4:
                col = 'E';
                break;
            case 5: 
                col = 'F';
                break;
            case 6: 
                col = 'G';
                break;
            case 7:
                col = 'H';
                break;
        }
    }
    public ChessSquare(int row, int column, ChessPiece piece){
        this.row = row;
        this.column = column;
        this.piece = piece;
        hasPiece = false;
    }

    public void addPiece(ChessPiece c){
        this.piece = c;
        this.hasPiece = true;
    }

    public void removePiece(){
        this.piece = null;
        this.hasPiece = false;
    }

    public void Show(){
        //include code for highlighting the square in the graphics
        System.out.print("---");
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public char getCol(){
        return col;
    }

    public boolean hasPiece(){
        return hasPiece;
    }

    public ChessPiece getPiece(){
        return piece;
    }

    public String printPiece(){
        return piece.getName() + " " + Character.toString(col) + Integer.toString(row);
    }

    public boolean equals(ChessSquare c){
        if (c.getRow() == this.getRow() && c.getColumn() == this.getColumn()){
            return true;
        }else{
            return false;
        }
    }
        
}