import java.util.ArrayList;

/* The ChessBoard class will be used to  mediate all the various actions that each piece takes. 
This makes it possible so that when the board is changed when pieces move,
all the pieces are part of the new board rather than having past instances of the board*/

public class ChessBoard {
    private ChessSquare[][] board;
    private boolean canCastleLong;
    private boolean canCastleShort;

    public ChessBoard(){
        board = new ChessSquare[8][8];
        createBoard(board);
        fillBoard(board);
        canCastleLong = false;
        canCastleShort = false;
    }

    public static void createBoard(ChessSquare[][] board){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = new ChessSquare(i, j);
            }
        }
    }

    public static void fillBoard(ChessSquare[][] board){

        //Pawns
        for (int i = 0; i < 8; i++){
            board[1][i].addPiece(new Pawn(true)); //White
            board[6][i].addPiece(new Pawn(false)); //Black
        }

        //Rooks
            //White
        board[0][0].addPiece(new Rook(true));
        board[0][7].addPiece(new Rook(true));
            //Black
        board[7][0].addPiece(new Rook(false));
        board[7][7].addPiece(new Rook(false));

        //Bishops
            //White
        board[0][5].addPiece(new Bishop(true));
        board[0][2].addPiece(new Bishop(true));
            //Black
        board[7][2].addPiece(new Bishop(false));
        board[7][5].addPiece(new Bishop(false));

        //Knights
            //White
        board[0][6].addPiece(new Knight(true));
        board[0][1].addPiece(new Knight(true));
            //Black
        board[7][1].addPiece(new Knight(false));
        board[7][6].addPiece(new Knight(false));

        //Queens
        board[0][3].addPiece(new Queen(true));
        board[7][3].addPiece(new Queen(false));

        //Kings
        board[0][4].addPiece(new King(true));
        board[7][4].addPiece(new King(false));
    }

    public ChessSquare getSquare(char column, int row){
        int col = 0;
        switch (column) {
            case 'A':
                col = 0;
                break;
            case 'B':
                col = 1;
                break;
            case 'C':
                col = 2;
                break;
            case 'D':
                col = 3;
                break;
            case 'E':
                col = 4;
                break;
            case 'F':
                col = 5;
                break;
            case 'G':
                col = 6;
                break;
            case 'H':
                col = 7;
                break;
        }

        return board[row-1][col];
    }

    public void printBoard(){
        char[] cols = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        for (int i = 7; i >= 0; i--){
            System.out.print( i+1 + "  ");
            for (int j = 0; j < 8; j++){
                if (board[i][j].getPiece() == null){
                    System.out.print("    \t");
                }else {
                    System.out.print(board[i][j].getPiece().getName() + "\t");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 8; i++){
            System.out.print("   " + cols[i] + "\t");
        }
        System.out.println("\n\n");
    }

    public ArrayList<ChessSquare> getPossibleMoves(ChessSquare c){
        ArrayList<ChessSquare> ret = new ArrayList<ChessSquare>();
        switch(c.getPiece().getName()){
            case "King":
                //Diagonals
                if (c.getRow() <= 6 && c.getColumn() <= 6){
                    if (!board[c.getRow()+1][c.getColumn()+1].hasPiece())
                        ret.add(board[c.getRow()+1][c.getColumn()+1]);
                    else{
                        if (c.getPiece().compareColor(board[c.getRow()+1][c.getColumn()+1].getPiece()) != 0){
                            ret.add(board[c.getRow()+1][c.getColumn()+1]);
                        }
                    }
                }
                if (c.getRow() >= 1 && c.getColumn() >= 1){
                    if (!board[c.getRow()-1][c.getColumn()-1].hasPiece())
                        ret.add(board[c.getRow()-1][c.getColumn()-1]);
                    else{
                        if (c.getPiece().compareColor(board[c.getRow()-1][c.getColumn()-1].getPiece()) != 0){
                            ret.add(board[c.getRow()-1][c.getColumn()-1]);
                        }
                    }
                }
                if (c.getRow() <= 6 && c.getColumn() >= 1){
                    if (!board[c.getRow()+1][c.getColumn()-1].hasPiece())
                        ret.add(board[c.getRow()+1][c.getColumn()-1]);
                    else{
                        if (c.getPiece().compareColor(board[c.getRow()+1][c.getColumn()-1].getPiece()) != 0){
                            ret.add(board[c.getRow()+1][c.getColumn()-1]);
                        }
                    }
                } 
                if (c.getRow() >= 1 && c.getColumn() <= 6){
                    if (!board[c.getRow()-1][c.getColumn()+1].hasPiece())
                        ret.add(board[c.getRow()-1][c.getColumn()+1]);
                    else{
                        if (c.getPiece().compareColor(board[c.getRow()-1][c.getColumn()+1].getPiece()) != 0){
                            ret.add(board[c.getRow()-1][c.getColumn()+1]);
                        }
                    }
                }
                //Straights
                if (c.getRow() <= 6){
                    if (!board[c.getRow()+1][c.getColumn()].hasPiece()){
                        ret.add(board[c.getRow()+1][c.getColumn()]);
                    }else
                        if (c.getPiece().compareColor(board[c.getRow()+1][c.getColumn()].getPiece()) != 0)
                            ret.add(board[c.getRow()+1][c.getColumn()]);
                }
                if (c.getRow() >= 1){
                    if (!board[c.getRow()-1][c.getColumn()].hasPiece()){
                        ret.add(board[c.getRow()-1][c.getColumn()]);
                    }else
                        if (c.getPiece().compareColor(board[c.getRow() -1][c.getColumn()].getPiece()) != 0)
                            ret.add(board[c.getRow() -1][c.getColumn()]);
                }
                if (c.getColumn() <= 6){
                    if (!board[c.getRow()][c.getColumn()+1].hasPiece()){
                        ret.add(board[c.getRow()][c.getColumn()+1]);
                    }else
                        if (c.getPiece().compareColor(board[c.getRow()][c.getColumn()+1].getPiece()) != 0)
                            ret.add(board[c.getRow()][c.getColumn()+1]);
                }
                if (c.getColumn() >= 1){
                    if (!board[c.getRow()][c.getColumn()-1].hasPiece()){
                        ret.add(board[c.getRow()][c.getColumn()-1]);
                    }else
                        if (c.getPiece().compareColor(board[c.getRow()][c.getColumn()-1].getPiece()) != 0)
                            ret.add(board[c.getRow()][c.getColumn()-1]);
                }

                //Castle 
                if (!c.getPiece().getMoved()){
                    if (board[c.getRow()][0].hasPiece()){
                        if (!(board[c.getRow()][0].getPiece().getMoved())){
                            for (int j = 1; j <= 3; j++){
                                if (!board[c.getRow()][j].hasPiece()){
                                    canCastleLong = true;
                                }else {
                                    canCastleLong = false;
                                }
                            }
                        }
                        if (canCastleLong){
                            ret.add(board[c.getRow()][2]);
                        }
                    }

                    if(board[c.getRow()][7].hasPiece()){
                        if (!(board[c.getRow()][7].getPiece().getMoved())){
                            for (int l = 5; l <= 6; l++){
                                if(!board[c.getRow()][l].hasPiece()){
                                    canCastleShort = true;
                                }else {
                                    canCastleShort = false;
                                }
                            }
                        }
                        if (canCastleShort){
                            ret.add(board[c.getRow()][6]);
                        }
                    }
                }

                break;
            case "Queen":
                //Diagonals

                //Gets squares UpRight
                for (int i = 1; i < 8; i++){
                    if (c.getRow()+i >= 8 || c.getColumn()+i >= 8){
                        break;
                    }else{
                        if (!board[c.getRow()+i][c.getColumn()+i].hasPiece())
                            ret.add(board[c.getRow()+i][c.getColumn()+i]);
                        else{
                            if (c.getPiece().compareColor(board[c.getRow()+i][c.getColumn()+i].getPiece()) != 0){
                                ret.add(board[c.getRow()+i][c.getColumn()+i]);
                                break;
                            }else
                                break;
                        }      
                    }
                }
                //Gets squares DownLeft
                for (int i = 1; i < 8; i++){
                    if (c.getRow()-i < 0 || c.getColumn()-i < 0){
                        break;
                    }else{
                        if (!board[c.getRow()-i][c.getColumn()-i].hasPiece())
                            ret.add(board[c.getRow()-i][c.getColumn()-i]);
                        else{
                            if (c.getPiece().compareColor(board[c.getRow()-i][c.getColumn()-i].getPiece()) != 0){
                                ret.add(board[c.getRow()-i][c.getColumn()-i]);
                                break;
                            }else
                                break;
                        }
                    }
                }
                //Gets Square UpLeft
                for (int i = 1; i < 8; i++){
                    if (c.getRow()+i >= 8 || c.getColumn()-i < 0){
                        break;
                    }else{
                        if (!board[c.getRow()+i][c.getColumn()-i].hasPiece())
                            ret.add(board[c.getRow()+i][c.getColumn()-i]);
                        else{
                            if (c.getPiece().compareColor(board[c.getRow()+i][c.getColumn()-i].getPiece()) != 0){
                                ret.add(board[c.getRow()+i][c.getColumn()-i]);
                                break;
                            }else
                                break;
                        }
                    }
                }
                //Gets squares DownRight
                for (int i = 1; i < 8; i++){
                    if (c.getRow()-i < 0 || c.getColumn()+i >= 8){
                        break;
                    }else{
                        if (!board[c.getRow()-i][c.getColumn()+i].hasPiece())
                            ret.add(board[c.getRow()-i][c.getColumn()+i]);
                        else{
                            if (c.getPiece().compareColor(board[c.getRow()-i][c.getColumn()+i].getPiece()) != 0){
                                ret.add(board[c.getRow()-i][c.getColumn()+i]);
                                break;
                            }else
                                break;
                        }
                    }
                }

                //Straights
                //Gets the rows in front
                if (c.getRow() < 7){
                    for (int i = c.getRow()+1; i < 8; i++){
                        if (!board[i][c.getColumn()].hasPiece()){
                            ret.add(board[i][c.getColumn()]);
                        }else{
                            if (c.getPiece().compareColor(board[i][c.getColumn()].getPiece()) != 0){
                                ret.add(board[i][c.getColumn()]);
                                break;
                            }else
                                break;
                        }
                    } 
                }
                
                //Gets the rows below
                if (c.getRow() > 0){
                    for (int i = c.getRow()-1; i >= 0; i--){
                        if (!board[i][c.getColumn()].hasPiece()){
                            ret.add(board[i][c.getColumn()]);
                        }else{
                            if (c.getPiece().compareColor(board[i][c.getColumn()].getPiece()) != 0){
                                ret.add(board[i][c.getColumn()]);
                                break;
                            }else
                                break;
                        }
                    }  
                }
                
                //gets the columns to the right
                if (c.getColumn() < 7){
                    for (int i = c.getColumn()+1; i < 8; i++){
                        if (!board[c.getRow()][i].hasPiece()){
                            ret.add(board[c.getRow()][i]);
                        }else{
                            if (c.getPiece().compareColor(board[c.getRow()][i].getPiece()) != 0){
                                ret.add(board[c.getRow()][i]);
                                break;
                            }else
                                break;
                        }
                    }
                }
                //Gets columns to the left
                if (c.getColumn() > 0){
                    for (int i = c.getColumn()-1; i >= 0; i--){
                        if (!board[c.getRow()][i].hasPiece()){
                            ret.add(board[c.getRow()][i]);
                        }else{
                            if (c.getPiece().compareColor(board[c.getRow()][i].getPiece()) != 0){
                                ret.add(board[c.getRow()][i]);
                                break;
                            }else
                                break;
                        }
                    }
                }                
                break;
            case "Bishop":
                //Gets squares UpRight
                for (int i = 1; i < 8; i++){
                    if (c.getRow()+i >= 8 || c.getColumn()+i >= 8){
                        break;
                    }else{
                        if (!board[c.getRow()+i][c.getColumn()+i].hasPiece())
                            ret.add(board[c.getRow()+i][c.getColumn()+i]);
                        else{
                            if (c.getPiece().compareColor(board[c.getRow()+i][c.getColumn()+i].getPiece()) != 0){
                                ret.add(board[c.getRow()+i][c.getColumn()+i]);
                                break;
                            }else
                                break;
                        }      
                    }
                }
                //Gets squares DownLeft
                for (int i = 1; i < 8; i++){
                    if (c.getRow()-i < 0 || c.getColumn()-i < 0){
                        break;
                    }else{
                        if (!board[c.getRow()-i][c.getColumn()-i].hasPiece())
                            ret.add(board[c.getRow()-i][c.getColumn()-i]);
                        else{
                            if (c.getPiece().compareColor(board[c.getRow()-i][c.getColumn()-i].getPiece()) != 0){
                                ret.add(board[c.getRow()-i][c.getColumn()-i]);
                                break;
                            }else
                                break;
                        }
                    }
                }
                //Gets Square UpLeft
                for (int i = 1; i < 8; i++){
                    if (c.getRow()+i >= 8 || c.getColumn()-i < 0){
                        break;
                    }else{
                        if (!board[c.getRow()+i][c.getColumn()-i].hasPiece())
                            ret.add(board[c.getRow()+i][c.getColumn()-i]);
                        else{
                            if (c.getPiece().compareColor(board[c.getRow()+i][c.getColumn()-i].getPiece()) != 0){
                                ret.add(board[c.getRow()+i][c.getColumn()-i]);
                                break;
                            }else
                                break;
                        }
                    }
                }
                //Gets squares DownRight
                for (int i = 1; i < 8; i++){
                    if (c.getRow()-i < 0 || c.getColumn()+i >= 8){
                        break;
                    }else{
                        if (!board[c.getRow()-i][c.getColumn()+i].hasPiece())
                            ret.add(board[c.getRow()-i][c.getColumn()+i]);
                        else{
                            if (c.getPiece().compareColor(board[c.getRow()-i][c.getColumn()+i].getPiece()) != 0){
                                ret.add(board[c.getRow()-i][c.getColumn()+i]);
                                break;
                            }else
                                break;
                        }
                    }
                }
                break;
            case "Knight":
                if(c.getColumn() < 6){
                    if (c.getRow() < 7){
                        if (!board[c.getRow()+1][c.getColumn()+2].hasPiece()){
                            ret.add(board[c.getRow()+1][c.getColumn() + 2]);
                        }else 
                            if(board[c.getRow()+1][c.getColumn()+2].getPiece().compareColor(c.getPiece()) != 0)
                                ret.add(board[c.getRow()+1][c.getColumn() + 2]);
                    }
                    if (c.getRow() > 0){
                        if (!board[c.getRow()-1][c.getColumn()+2].hasPiece()){
                            ret.add(board[c.getRow()-1][c.getColumn() + 2]);
                        }else 
                            if(board[c.getRow()-1][c.getColumn()+2].getPiece().compareColor(c.getPiece()) != 0)
                                ret.add(board[c.getRow()-1][c.getColumn() + 2]);
                    }
                }
                if (c.getColumn() > 1){
                    if (c.getRow() < 7){
                        if (!board[c.getRow()+1][c.getColumn()-2].hasPiece()){
                            ret.add(board[c.getRow()+1][c.getColumn()-2]);
                        }else 
                            if(board[c.getRow()+1][c.getColumn()-2].getPiece().compareColor(c.getPiece()) != 0)
                                ret.add(board[c.getRow()+1][c.getColumn()-2]);
                    }
                    if (c.getRow() > 0){
                        if (!board[c.getRow()-1][c.getColumn()-2].hasPiece()){
                            ret.add(board[c.getRow()-1][c.getColumn()-2]);
                        }else 
                            if(board[c.getRow()-1][c.getColumn()-2].getPiece().compareColor(c.getPiece()) != 0)
                                ret.add(board[c.getRow()-1][c.getColumn()-2]);
                    }
                }
                if (c.getRow() < 6){
                    if (c.getColumn() < 7){
                        if (!board[c.getRow()+2][c.getColumn()+1].hasPiece()){
                            ret.add(board[c.getRow()+2][c.getColumn()+1]);
                        }else 
                            if(board[c.getRow()+2][c.getColumn()+1].getPiece().compareColor(c.getPiece()) != 0)
                                ret.add(board[c.getRow()+2][c.getColumn()+1]);
                    }
                    if (c.getColumn() > 0){
                        if (!board[c.getRow()+2][c.getColumn()-1].hasPiece()){
                            ret.add(board[c.getRow()+2][c.getColumn()-1]);
                        }else 
                            if(board[c.getRow()+2][c.getColumn()-1].getPiece().compareColor(c.getPiece()) != 0)
                                ret.add(board[c.getRow()+2][c.getColumn()-1]);
                    }
                }
                if (c.getRow() > 1){
                    if (c.getColumn() < 7){
                        if (!board[c.getRow()-2][c.getColumn()+1].hasPiece()){
                            ret.add(board[c.getRow()-2][c.getColumn()+1]);
                        }else 
                            if(board[c.getRow()-2][c.getColumn()+1].getPiece().compareColor(c.getPiece()) != 0)
                                ret.add(board[c.getRow()-2][c.getColumn()+1]);
                    }
                    if (c.getColumn() > 0){
                        if (!board[c.getRow()-2][c.getColumn()-1].hasPiece()){
                            ret.add(board[c.getRow()-2][c.getColumn()-1]);
                        }else 
                            if(board[c.getRow()-2][c.getColumn()-1].getPiece().compareColor(c.getPiece()) != 0)
                                ret.add(board[c.getRow()-2][c.getColumn()-1]);
                    }
                }
                break;
            case "Pawn":
                //Have to implement En Passant
                if (c.getPiece().getColor()){
                    if (c.getRow() < 7){
                        if (!c.getPiece().getMoved()){
                            if (!(board[c.getRow()+2][c.getColumn()].hasPiece()&&board[c.getRow()+1][c.getColumn()].hasPiece())){
                                ret.add(board[c.getRow()+2][c.getColumn()]);
                                ret.add(board[c.getRow()+1][c.getColumn()]);
                            }
                        }else if (!(board[c.getRow()+1][c.getColumn()].hasPiece())){
                            ret.add(board[c.getRow()+1][c.getColumn()]);
                        }

                        if ( c.getColumn() < 7 && board[c.getRow()+1][c.getColumn()+1].hasPiece()){
                            if (c.getPiece().compareColor(board[c.getRow()+1][c.getColumn()+1].getPiece()) != 0){
                                ret.add(board[c.getRow()+1][c.getColumn()+1]);
                            }
                        }
                        if( c.getColumn() > 0 && board[c.getRow()+1][c.getColumn()-1].hasPiece()){
                            if (c.getPiece().compareColor(board[c.getRow()+1][c.getColumn()-1].getPiece()) != 0){
                                ret.add(board[c.getRow()+1][c.getColumn()-1]);
                            }
                        }
                    }
                }else {
                    if (c.getRow() > 0){
                        if (!c.getPiece().getMoved()){
                            if (!(board[c.getRow()-2][c.getColumn()].hasPiece()&&board[c.getRow()-1][c.getColumn()].hasPiece())){
                                ret.add(board[c.getRow()-2][c.getColumn()]);
                                ret.add(board[c.getRow()-1][c.getColumn()]);
                            }
                        }else if (!board[c.getRow()-1][c.getColumn()].hasPiece()){
                            ret.add(board[c.getRow()-1][c.getColumn()]);
                        }
        
                        if ( c.getColumn() < 7 && board[c.getRow()-1][c.getColumn()+1].hasPiece()){
                            if (c.getPiece().compareColor(board[c.getRow()-1][c.getColumn()+1].getPiece()) != 0){
                                ret.add(board[c.getRow()-1][c.getColumn()+1]);
                            }
                        }
                        if(c.getColumn() > 0 && board[c.getRow()-1][c.getColumn()-1].hasPiece()){
                            if (c.getPiece().compareColor(board[c.getRow()-1][c.getColumn()-1].getPiece()) != 0){
                                ret.add(board[c.getRow()-1][c.getColumn()-1]);
                            }
                        }
                    }
                }
                break;
            case "Rook":
                //Gets the rows in front
                if (c.getRow() < 7){
                    for (int i = c.getRow()+1; i < 8; i++){
                        if (!board[i][c.getColumn()].hasPiece()){
                            ret.add(board[i][c.getColumn()]);
                        }else{
                            if (c.getPiece().compareColor(board[i][c.getColumn()].getPiece()) != 0){
                                ret.add(board[i][c.getColumn()]);
                                break;
                            }else
                                break;
                        }
                    } 
                }
                
                //Gets the rows below
                if (c.getRow() > 0){
                    for (int i = c.getRow()-1; i >= 0; i--){
                        if (!board[i][c.getColumn()].hasPiece()){
                            ret.add(board[i][c.getColumn()]);
                        }else{
                            if (c.getPiece().compareColor(board[i][c.getColumn()].getPiece()) != 0){
                                ret.add(board[i][c.getColumn()]);
                                break;
                            }else
                                break;
                        }
                    }  
                }
                
                //gets the columns to the right
                if (c.getColumn() < 7){
                    for (int i = c.getColumn()+1; i < 8; i++){
                        if (!board[c.getRow()][i].hasPiece()){
                            ret.add(board[c.getRow()][i]);
                        }else{
                            if (c.getPiece().compareColor(board[c.getRow()][i].getPiece()) != 0){
                                ret.add(board[c.getRow()][i]);
                                break;
                            }else
                                break;
                        }
                    }
                }
                //Gets columns to the left
                if (c.getColumn() > 0){
                    for (int i = c.getColumn()-1; i >= 0; i--){
                        if (!board[c.getRow()][i].hasPiece()){
                            ret.add(board[c.getRow()][i]);
                        }else{
                            if (c.getPiece().compareColor(board[c.getRow()][i].getPiece()) != 0){
                                ret.add(board[c.getRow()][i]);
                                break;
                            }else
                                break;
                        }
                    }
                }
        }
        return ret;
    }

    public void showPossibleMoves(ChessSquare c){
        System.out.println("These are your possible moves:");
        ArrayList<ChessSquare> pmoves = this.getPossibleMoves(c);
        for (int i = 0; i < pmoves.size(); i++){
            System.out.println(c.getPiece().getName() + " " + pmoves.get(i).getCol() + (pmoves.get(i).getRow()+1));
        }
        if (canCastleLong){
            System.out.println("CastleLong");
        }
        if (canCastleShort){
            System.out.println("CastleShort");
        }
    }

    public boolean checkValidMove(ChessSquare c, ChessSquare c2){
        boolean ret = false;
        ArrayList<ChessSquare> pmoves = this.getPossibleMoves(c);
        for (int i = 0; i < pmoves.size(); i++) {
            if (c2.equals(pmoves.get(i))){
                ret = true;
                break;
            }
        }
        return ret;
    }

    public void Castle(ChessSquare king, boolean l){
        if (canCastleLong && l){
            this.Move(king, board[king.getRow()][2]);
            this.Move(board[king.getRow()][0], board[king.getRow()][3]);
        }else if (canCastleShort && !l){
            this.Move(king, board[king.getRow()][6]);
            this.Move(board[king.getRow()][7], board[king.getRow()][5]);
        }
    }

    public boolean checkForCheck(){
        //Make this return boolean
        int row = 0;
        int column = 0;
        boolean check = false;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board[i][j].hasPiece()){
                    if (board[i][j].getPiece().getName() == "King" ){
                        // if(Boolean.compare(color, board[i][j].getPiece().getColor()) == 0){
                            row  = i;
                            column = j;
                        // }
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].hasPiece()){
                    ArrayList<ChessSquare> pmoves = this.getPossibleMoves(board[i][j]);
                    for (int k = 0; k < pmoves.size(); k++){
                        if (pmoves.get(k).equals(board[row][column]) && pmoves.get(k).getPiece().compareColor(board[row][column].getPiece()) != 0){
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        return check;
    }

    // public ArrayList<ChessSquare> checkMoves(){
    //     //returns moves the king can make after being in check and to not be in another check
    // }

    public void checkForMate(){
        //Make this return boolean
    }

    public void Move(ChessSquare a, ChessSquare b){
        if (b.hasPiece()){
            b.removePiece();
        }
        a.getPiece().hasMoved();
        b.addPiece(a.getPiece());
        a.removePiece();
    }

    
}