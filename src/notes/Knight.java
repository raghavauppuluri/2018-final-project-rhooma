package Pieces;

import java.util.ArrayList;
import java.util.Scanner;

public class Knight extends Piece {
	
	
	public boolean isValid(Location loc) {
		
	}
	

}
public void PawnPromotion (Location move, Board b){
	int row = move.getRow();
	int col = move.getCol();
	Scanner keyboard = new Scanner(System.in);
	if(this.getID().charAt(0) == "w"){
		if(n != null && row == 7){
			System.out.println("What Piece ya want ?");
			String piece = keyboard.nextLine();
			String zone = "";
		while(zone.equals("")){
			if(piece.equals("Q")){
				
				b.board[row][col] = new Queen("wQ");
				zone = "2";
			}
			else if(piece.equals("N")){
				b.board[row][col] = new Knight("wN");
				zone = "2";
			}
			else if(piece.equals("B")){
				b.board[row][col] = new Bishop("wB");
				zone = "2";
			}
			else if(piece.equals("R")){
				b.board[row][col] = new Rook("wR");
				zone = "2";
			}
			else{
				System.out.println("What Piece ya want ?");
				 piece = keyboard.nextLine();
			}
	}
}
	}else{	
		if(row == 0){
			System.out.println("What Piece ya want ?");
			String piece = keyboard.nextLine();
			String zone = "";
		while(zone.equals("")){
			if(piece.equals("Q")){
				
				b.board[row][col] = new Queen("bQ");
				zone = "2";
			}
			else if(piece.equals("N")){
				b.board[row][col] = new Knight("bN");
				zone = "2";
			}
			else if(piece.equals("B")){
				b.board[row][col] = new Bishop("bB");
				zone = "2";
			}
			else if(piece.equals("R")){
				b.board[row][col] = new Rook("bR");
				zone = "2";
			}
			else{
				System.out.println("What Piece ya want ?");
				 piece = keyboard.nextLine();
			}
	}
	}
	}
}
public boolean KingSide(Location move, Board b){
	boolean WinitalPosition = this.getID().charAt(0) == 'w' && b.board[0][3].getID().equals("wK");
	boolean WthereIsARook = b.board[0][0] != null && b.board[0][0].getID().equals("wR");
	boolean WnoMovement = !(this.hasMoved && b.board[0][3].getHasMoved());
	boolean noChecks = this.notChecked;
	boolean WnothingBetween = b.board[0][0].isValid(new Location(0,2));
	boolean BinitalPosition = this.getID().charAt(0) == 'w' && b.board[7][3].getID().equals("wK");
	boolean BthereIsARook = b.board[7][0] != null && b.board[7][0].getID().equals("bR")
	boolean BnoMovement = !(this.hasMoved && b.board[7][3].getHasMoved());
	boolean BnothingBetween = b.board[7][0].isValid(new Location(7,2));
	
	if(WinitalPosition){
		if(WthereIsARook){
			if(WnoMovement){
				if(noChecks){
					if(WnothingBetween){
						
					return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
}else{
	return false;
}

	}
	else if(BinitalPosition){
		if(BthereIsARook){
			if(WnoMovement){
				if(noChecks){
					if(BnothingBetween){
					return true;
				}
					else{
						return false;
					}
			
			}
			else{
				return false;
			}
}else{
	return false;
}
	}
	else{
		return false;
	}
}
	else{
		return false;
	}
}
}
public boolean QueenSide(Location move, Board b){
	boolean WinitalPosition = this.getID().charAt(0) == 'w' && b.board[0][3].getID().equals("wK");
	boolean WthereIsARook = b.board[0][7] != null && b.board[0][7].getID().equals("wR");
	boolean WnoMovement = !(this.hasMoved && b.board[0][3].getHasMoved());
	boolean noChecks = this.notChecked;
	boolean WnothingBetween = b.board[0][7].isValid(new Location(0,2));
	boolean BinitalPosition = this.getID().charAt(0) == 'w' && b.board[7][3].getID().equals("wK");
	boolean BthereIsARook = b.board[7][7] != null && b.board[7][7].getID().equals("bR")
	boolean BnoMovement = !(this.hasMoved && b.board[7][3].getHasMoved());
	boolean BnothingBetween = b.board[7][7].isValid(new Location(7,2));
	
	if(WinitalPosition){
		if(WthereIsARook){
			if(WnoMovement){
				if(noChecks){
					if(WnothingBetween){
						
					return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
}else{
	return false;
}

	}
	else if(BinitalPosition){
		if(BthereIsARook){
			if(WnoMovement){
				if(noChecks){
					if(BnothingBetween){
					return true;
				}
					else{
						return false;
					}
			
			}
			else{
				return false;
			}
}else{
	return false;
}
	}
	else{
		return false;
	}
}
	else{
		return false;
	}
}

public boolean isInCheck(Board b, char firstLetter){ //determines if king of the other color is in check
	//board class
	boolean isInCheck = false;
	for(int row = 0; row < b.length; row++){
		for(int col = 0; col < b[0].length; col++ ){
			if(b.board[row][col] != null){
				if(b.board[row][col].getID().charAt(0) == firstLetter){
					if(b.board[row][col].isCheck(b)){		
					return true;
				}
				}
				}
	}	

	}
return false;
}
	public Location isInCheckLocation(Board b, char firstLetter){ //determines where the checker is
		//board class
		Location checker = new Location (0,0);
		boolean isInCheck = false;
		for(int row = 0; row < b.length; row++){
			for(int col = 0; col < b[0].length; col++ ){
				if(b.board[row][col] != null){
					if(b.board[row][col].getID().charAt(0) == firstLetter){
						if(b.board[row][col].isCheck(b)){		
						 checker = new Location (row,col);
					}
					}
					}
		}	
	}
return checker;
}
public Location OtherKingLoc(Board b, char firstLetter){ //determines the location of the king of the other color
	//board class
	Location kingLoc = new Location (0,0);
	int kingRow = 0;
	int kingCol = 0;
		for(int row = 0; row < b.length; row++){
		for(int col = 0; col < b[0].length; col++){
			if(b.board[row][col] != null){
				boolean otherColor = b.board[row][col].getID().charAt(0) != firstLetter;
				boolean isItKing = b.board[row][col].getID().charAt(1) == 'K';
				if(otherColor && isItKing){
					kingRow = row;
					kingCol = col;
					kingLoc = new Location(kingRow, kingCol);
					return kingLoc;
				}
			}
		}
		}
		
				
}
public boolean canOtherKingMove(Board b, char firstLetter){ //determines whether other colored king can move
	//board class
int kingRow = 0;
int kingCol = 0;
	for(int row = 0; row < b.length; row++){
	for(int col = 0; col < b[0].length; col++){
		if(b.board[row][col] != null){
			boolean otherColor = b.board[row][col].getID().charAt(0) != firstLetter;
			boolean isItKing = b.board[row][col].getID().charAt(1) == 'K';
			if(otherColor && isItKing){
				kingRow = row;
				kingCol = col;
				break;
			}
		}
	}
}
boolean HorizontalPositive = b.board[kingRow][kingCol].isValid(b, new Location(kingRow, kingCol + 1)); //moves of other king
boolean VerticalPositive = b.board[kingRow][kingCol].isValid(b, new Location(kingRow + 1, kingCol));
boolean HorizontalNegative = b.board[kingRow][kingCol].isValid(b, new Location(kingRow, kingCol - 1));
boolean VerticalNegative = b.board[kingRow][kingCol].isValid(b, new Location(kingRow - 1, kingCol));
boolean DiagonalPositive = b.board[kingRow][kingCol].isValid(b, new Location(kingRow + 1, kingCol + 1));
boolean DiagonalNegative = b.board[kingRow][kingCol].isValid(b, new Location(kingRow - 1, kingCol - 1));
if(kingCol < 7 && kingRow < 7 || (kingCol > 0 && kingRow > 0) ){
	return HorizontalPositive || VerticalPositive || HorizontalNegative || VerticalNegative 
			||DiagonalPositive || DiagonalNegative;
}
else if(kingCol < 7 && kingRow == 7){
	return HorizontalPositive || HorizontalNegative || VerticalNegative || DiagonalNegative;
	
}
else if(kingRow < 7 && kingCol == 7 ){
	return VerticalPositive || HorizontalNegative || VerticalNegative || DiagonalNegative;
}
else if(kingRow == 7 && kingCol == 7 ){
	return HorizontalNegative || VerticalNegative || DiagonalNegative;
}
else if(kingCol > 0 && kingRow == 0){
	return HorizontalPositive || VerticalPositive || HorizontalNegative || DiagonalPositive;
}
else if(kingCol == 0 && kingRow > 0){
	return HorizontalPositive || VerticalPositive || VerticalNegative || DiagonalPositive;
}
else{
	return HorizontalPositive || VerticalPositive || DiagonalPositive;
}
}
public boolean canOtherCapture(Board b, char firstLetter){ //determines if the other color pieces can capture the checker
	//board class
	Location checker = b.isInCheckLocation(b, firstLetter);
	if(checker == null){
		return true;
	}
	for(int row = 0; row < b.length; row++){
		for(int col = 0; col < b[0].length; col++){
			if(b.board[row][col] != null){
				if(b.board[row][col].getID().charAt(0) != firstLetter){
					if(b.board[row][col].isValid(b, checker)){
						return true;
					}
				}
			}
		}
	}
	return false;
}
public ArrayList<Piece> colorPieces(Board b, char firstLetter){ //makes array of other colored pieces
	//board class
	ArrayList <Piece> coloredPieces = new ArrayList <Piece> ();
	for(int row = 0; row < b.length; row++){
		for(int col = 0; col < b[0].length; col++){
			if(b.board[row][col] != null){
				if(b.board[row][col].getID().charAt(0) != firstLetter){
					coloredPieces.add(b.board[row][col]);
				}
			}
		}
	}
}
public boolean canItBlock(Board b, char firstLetter){ //determines whether the pieces can block the check
	//board class
	ArrayList <Piece> coloredPieces = b.colorPieces(b, firstLetter);
	Location checker = b.isInCheckLocation(b, firstLetter);
	Location kingLoc = b.OtherKingLoc(b, firstLetter);
	if(checker == null || kingLoc == null){
		return true;
	}
	int checkerRow = checker.getRow();
	int checkerCol = checker.getCol();
	int kingRow = kingLoc.getRow();
	int kingCol = kingLoc.getCol();
	if(b.board[checkerRow][checkerCol].checkHorizontal()){ //different checks
		while(checkerCol != kingCol){
			if(checkerCol > kingCol){
				checkerCol --;
			}
			else{
				checkerCol++;
			}
			for(int i = 0; i < coloredPieces.size(); i++){
				boolean canItGetInWay = coloredPieces.get(i).isValid(b, new Location (checkerRow, checkerCol));
				if(canItGetInWay){
					return true;
				}
			}
		}
	return false;
	}
	else if(b.board[checkerRow][checkerCol].checkVertical()){
		while(checkerRow != kingRow){
			if(checkerRow > kingRow){
				checkerRow --;
			}
			else{
				checkerRow++;
			}
			for(int i = 0; i < coloredPieces.size(); i++){
				boolean canItGetInWay = coloredPieces.get(i).isValid(b, new Location (checkerRow, checkerCol));
				if(canItGetInWay){
					return true;
				}
			}
		}
		return false;
	}
	else if(b.board[checkerRow][checkerCol].checkDiagonal()){
		while(checkerRow != kingRow){
			if(checkerRow > kingRow){
				checkerRow --;
				checkerCol--;
			}
			else{
				checkerRow++;
				checkerCol++;
			}
			for(int i = 0; i < coloredPieces.size(); i++){
				boolean canItGetInWay = coloredPieces.get(i).isValid(b, new Location (checkerRow, checkerCol));
				if(canItGetInWay){
					return true;
				}
			}
		}
		return false;
	}
	else{
		return false;
	}
}
public boolean isCheckMate(Board b, char firstLetter){ //actual method
//board class
	Location OtherKingLocation = b.OtherKingLoc(b,firstLetter);
int OtherKingRow = OtherKingLocation.getRow();
int  OtherKingCol = OtherKingLocation.getCol();
	if(b.board[ OtherKingRow][ OtherKingCol].isInCheck(b,firstLetter)){ //Is the king in check
	
		if(!(b.canItCapture(b, firstLetter))){ //can anything move and capture it
		
			if(!(b.canItBlock(b,firstLetter))){ //can anything block the check
				
				if(!(canOtherKingMove(b, firstLetter))){ //can it move out
					
					return true;
					
				}
				else{
					return false;
				}
			
		}
			else{
				return false;
			}
	}
		else{
			return false;
	}
	
	}
	
else{
	return false;
}
}
}