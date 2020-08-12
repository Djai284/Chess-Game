import java.util.*;
public class Main {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        ChessBoard board = new ChessBoard();
        
        boolean gameEnd = false;
        //turn = true --> white move
        //turn = false --> black move
        boolean turn = false;
        while (!gameEnd){
            board.printBoard();
            
            if (board.checkForCheck()){
                System.out.println("You are currently in check. You have to either move your king or another piece to get out of check");
            }

            System.out.println("Choose the square of the piece you want to move");
            String c = sc.nextLine();
            char k = c.charAt(0);
            int r = Character.getNumericValue(c.charAt(1));
            boolean canMove = false;
            if (Character.compare('H', k) < 0 || r > 8){
                System.out.println("This is not a valid square");
                canMove = true;
            }
            ChessSquare og = board.getSquare(k, r);

            if (!og.hasPiece()){
                System.out.println("This is not a valid square because it has no piece. Try again\n");
                canMove = true;
            }

            while (!canMove){
                if (Boolean.compare(og.getPiece().getColor(), turn) == 0){
                    if (!turn){
                        System.out.println("It is white move right now. Choose a valid piece\n");
                    }else{
                        System.out.println("It is black move right now. Choose a valid piece\n");
                    }
                    canMove = true;
                    break;
                }

                ArrayList<ChessSquare> pmoves = board.getPossibleMoves(og);
                if (pmoves.size() == 0){
                    System.out.println("There are no possible moves\n");
                    canMove = true;
                    break;
                }

                board.showPossibleMoves(og);
                System.out.println("Choose the square you want the piece to move to");
                c = sc.nextLine();
                char k2 = c.charAt(0);
                int r2 = Character.getNumericValue(c.charAt(1));

                if (board.checkValidMove(og, board.getSquare(k2, r2))){
                    board.Move(og, board.getSquare(k2, r2));
                    turn = !turn;
                    board.printBoard();
                    canMove = true;
                    break;
                }
                System.out.println("The square you have entered is not a possible move\n");
                
                //In this loop, there needs to be a move tracker that records all the moves made
            }

            System.out.println("If you want to end the game, enter 'r'");

            // if (sc.nextLine() == "r"){
            //     gameEnd = true;
            //     break;
            // }F1
        }

        sc.close();
    }
}