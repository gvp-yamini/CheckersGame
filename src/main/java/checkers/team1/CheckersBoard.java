package main.java.checkers.team1;

import java.util.ArrayList;

import main.java.model.board.ABoardModel;
import main.java.model.board.NonTerminalState;
import main.java.model.modelToView.ICommand;
import main.java.model.move.IBoardStatusVisitor;
import main.java.model.move.ICheckMoveVisitor;
import main.java.model.move.IUndoMove;
import main.java.model.move.IUndoVisitor;
import main.java.model.utility.Point;

/**
 *  CheckerType that can be used
 */
enum CheckerType
{
	 /**
	   * Empty cell
	   */
	   EMPTY,
	   /**
	     * Black Checker
	     */
	   BLACK,
	   /**
	     * Black King Checker
	     */
	   BLACK_KING,
	   /**
	     * Red Checker
	     */
	   RED,
	   /**
	     * Red King Checker
	     */
	   RED_KING,
	   /**
	     * Blocked cell
	     */
	   BLOCKED
}

/**
* The CheckersBoard will implement all the methods corresponding Checkers board 
*/
public class CheckersBoard extends ABoardModel{
	
	private int nRows;
	private int nCols;
	private int nextPlayer = 3;
	Move[] possibleMoves;
	/** 
	  * Class constructor specifying number of rows and columns to create for a Board
	  */
	public CheckersBoard(int nRows, int nCols){
		super(nRows,nCols);
		this.nRows = nRows;
		this.nCols = nCols;
		createBoard();
	}
	/**
	 * Setup the board with initial positions
	 */
	public void createBoard() {

		for(int row = 0; row < this.nRows; row++) {
			for(int col = 0; col < this.nCols; col++) {
				if(row % 2 == col % 2) {
					if (row < 3)
						this.cells[row][col] = CheckerType.BLACK.ordinal();
					else if(row >  4)
						this.cells[row][col] = CheckerType.RED.ordinal();
					else
						this.cells[row][col] = CheckerType.EMPTY.ordinal();

				} else {
					this.cells[row][col] = CheckerType.EMPTY.ordinal();
				}
			}
		}
	}
	
	
	/**
	   * This method is used to check for win of a particular player
	   * @param player number
	   * @return int This returns 1 for player 0 , -1 for player 2 and 0 for others.
	   */
	public int checkWin(int player){
		if(player==0){
			return 1;
		}
		else if(player==1){ 
			return -1;
		}
		else
			return 0;
	}
	
	
	/**
	 * State of board is updated with the move requested
	 * @param requested Move eg: Move : (Point[x=2,y=2]) -> (Point[x=3,y=3])
	 */
	public void updateBoardWith(Move requestedMove) 
	{
		System.out.println("board is getting updated");
		this.cells[requestedMove.to.x][requestedMove.to.y] = this.cells[requestedMove.from.x][requestedMove.from.y];
		this.cells[requestedMove.from.x][requestedMove.from.y] = EMPTY;
		System.out.println("Math.abs value of the move is: "+ Math.abs(requestedMove.to.x - requestedMove.from.y));
		
		// Remove opponent pieces that are jumped over
		if(Math.abs(requestedMove.to.x - requestedMove.from.x) == 2) 
		{
			System.out.println("2 Row Cleanup in process");
			int removePieceRow = (requestedMove.from.x + requestedMove.to.x) / 2;
			int removePieceCol = (requestedMove.from.y + requestedMove.to.y) / 2;
			this.cells[removePieceRow][removePieceCol] = EMPTY;
		}
		if(Math.abs(requestedMove.to.x - requestedMove.from.x) == 4) 
		{
			System.out.println("4 row cleanup in process");
			if(requestedMove.to.x > requestedMove.from.x && requestedMove.to.y > requestedMove.from.y) {
				this.cells[requestedMove.from.x + 3][requestedMove.from.y + 3] = EMPTY;
				this.cells[requestedMove.from.x + 1][requestedMove.from.y + 1] = EMPTY;
			}

			if(requestedMove.to.x < requestedMove.from.x && requestedMove.to.y > requestedMove.from.x) {
				this.cells[requestedMove.from.x - 3][requestedMove.from.y + 3] = EMPTY;
				this.cells[requestedMove.from.x - 1][requestedMove.from.y + 1] = EMPTY;
			}

			if(requestedMove.to.x > requestedMove.from.x && requestedMove.to.y < requestedMove.from.y) {
				this.cells[requestedMove.from.x + 3][requestedMove.from.y - 3] = EMPTY;
				this.cells[requestedMove.from.x + 1][requestedMove.from.y - 1] = EMPTY;
			}

			if(requestedMove.to.x < requestedMove.from.x && requestedMove.to.y < requestedMove.from.y) {
				this.cells[requestedMove.from.x - 3][requestedMove.from.y - 3] = EMPTY;
				this.cells[requestedMove.from.x - 1][requestedMove.from.y - 1] = EMPTY;
			}
		}
		if(Math.abs(requestedMove.to.x - requestedMove.from.x) == 6) 
		{
			System.out.println("6 row cleanup in process");

			if(requestedMove.to.x > requestedMove.from.x && requestedMove.to.y > requestedMove.from.y) {
				this.cells[requestedMove.from.x + 5][requestedMove.from.y + 5] = EMPTY;
				this.cells[requestedMove.from.x + 1][requestedMove.from.y + 1] = EMPTY;
			}

			if(requestedMove.to.x < requestedMove.from.x && requestedMove.to.y > requestedMove.from.x) {
				this.cells[requestedMove.from.x - 5][requestedMove.from.y + 5] = EMPTY;
				this.cells[requestedMove.from.x - 1][requestedMove.from.y + 1] = EMPTY;
			}

			if(requestedMove.to.x > requestedMove.from.x && requestedMove.to.y < requestedMove.from.y) {
				this.cells[requestedMove.from.x + 5][requestedMove.from.y - 5] = EMPTY;
				this.cells[requestedMove.from.x + 1][requestedMove.from.y - 1] = EMPTY;
			}

			if(requestedMove.to.x < requestedMove.from.x && requestedMove.to.y < requestedMove.from.y) {
				this.cells[requestedMove.from.x - 5][requestedMove.from.y - 5] = EMPTY;
				this.cells[requestedMove.from.x - 1][requestedMove.from.y - 1] = EMPTY;
			}
		}
		if(requestedMove.to.x == 7 && cells[requestedMove.to.x][requestedMove.to.y] == CheckerType.BLACK.ordinal())
			this.cells[requestedMove.to.x][requestedMove.to.y] = CheckerType.BLACK_KING.ordinal();
		if(requestedMove.to.x == 0 && cells[requestedMove.to.x][requestedMove.to.y] == CheckerType.RED.ordinal())
			this.cells[requestedMove.to.x][requestedMove.to.y] = CheckerType.RED_KING.ordinal();
	}
	
	/**
	   * This method is used to implement makeMove
	   * @param MoveNumber This is the first paramter to makeMove method which takes Move number
	   * @param temp  This is the second parameter which is a placeholer
	   * @param icheckmovevisitor This is the ICheckMoveVisitor instance
	   * @param iboardstatusvisitor This is instance of IBoardStatusVisitor
	   * @return IUndoMove This returns either undo or apply Move.
	   */
	public synchronized IUndoMove makeMove(int MoveNumber, int temp, int player, ICheckMoveVisitor icheckmovevisitor, IBoardStatusVisitor iboardstatusvisitor){
		int row = possibleMoves[MoveNumber].to.x;
		int col = possibleMoves[MoveNumber].to.y;
		if(isValidMove(player,row,col)){
			updateBoardWith(possibleMoves[MoveNumber]);
			super.execute(iboardstatusvisitor, null);
			if(player==1){
			   nextPlayer = 3;
			}else{
			   nextPlayer = 1;	
			}
			return new IUndoMove(){
				public void apply(IUndoVisitor iundovisitor){
					undoMove(row,col,iundovisitor);
				}
			};
		}else{
			icheckmovevisitor.invalidMoveCase();
			return new IUndoMove(){
				public void apply(IUndoVisitor iundovisitor){
					
				}
			};
		}
	}
	
	/**
	   * This method implements undoMove 
	   * @param row This is the first parameter to undoMove method which takes current row of the checker 
	   * @param col This is the second parameter to undoMove method which takes current col of the checker 
	   * @param iundovisitor This is instance of IUndoVisitor
	   */
	public synchronized void undoMove(int row,int col,IUndoVisitor iundovisitor){
		int k = super.cells[row][col];
		if(k==0){
			iundovisitor.noTokenCase();
		}else{
			super.cells[row][col]=0;
			iundovisitor.tokenCase((k+1)/2);
		}
		super.state = NonTerminalState.Singleton;
	}
	
	/**
	   * This method implements isValidMove
	   * @param player This is the first parameter to isValidMove method which takes current player number
	   * @param row This is the second parameter to isValidMove method which takes current row of the checker 
	   * @param col This is the third parameter to isValidMove method which takes current col of the checker
	   * @return boolean returns true or false  
	   */
	public boolean isValidMove(int player, int row, int col){
		if(row>this.nRows || col > this.nCols){
			return false;
		}
		return 0==super.cells[row][col]; // check if cell is empty
	}
	
	/**
	   * This method checks for Legal jumps
	   * @param player This is the first parameter to isLegalJump method which takes current player number
	   * @param move This will take a instance of Move
	   * @param inBetweenRow This is the third parameter to isLegalJump method which takes opponent player's row of the checker 
	   * @param inBetweenCol This is the fourth parameter to isLegalJump method which takes opponent player's col of the checker
	   * @return returns true or false based on the logic 
	   */
	public boolean isLegalJump(int player, Move move, int inBetweenRow, int inBetweenCol) {
		if (move.to.y < 0 || move.to.y >= 8 || move.to.x >= 8 || move.to.x < 0) {
			return false;
		}

		if(this.cells[move.to.x][move.to.y] != EMPTY ) {
			return false;
		}

		if(player == CheckerType.RED.ordinal()) {

			if(this.cells[move.from.x][move.from.y] == CheckerType.RED.ordinal() && move.to.x > move.to.y)
				return false;

			if(this.cells[inBetweenRow][inBetweenCol] != CheckerType.BLACK.ordinal() && this.cells[inBetweenRow][inBetweenCol] != CheckerType.BLACK_KING.ordinal())
				return false;

			return true;
		} else {

			if(this.cells[move.from.x][move.from.y] == CheckerType.BLACK.ordinal() && move.to.x < move.from.x)
				return false;

			if(this.cells[inBetweenRow][inBetweenCol] != CheckerType.RED.ordinal() && this.cells[inBetweenRow][inBetweenCol] != CheckerType.RED_KING.ordinal())
				return false;

			return true;
		}
	}
	
	/**
	 * This method will calculate the possible moves for the current player
	 * There are two possible moves, one is moving by one step and the other is a jump over other players coin
	 * @return Move[] List of all possible moves
	 */
	public Move[] getPossibleMoves() {
		int currPlayer = nextPlayer;
		if (currPlayer != CheckerType.RED.ordinal() && currPlayer != CheckerType.BLACK.ordinal())
			return null;

		int currPlayerKing;
		if(currPlayer == CheckerType.RED.ordinal())
			currPlayerKing = CheckerType.RED_KING.ordinal();
		else
			currPlayerKing = CheckerType.BLACK_KING.ordinal();
		
		    ArrayList<Move> availableMoves = new ArrayList<Move>();
		    
			for(int row = 0; row < 8; row++) {
				for(int col = 0; col < 8; col++) {
					if(this.cells[row][col] == currPlayer || this.cells[row][col] == currPlayerKing) {
						if(isLegalJump(currPlayer, new Move(new Point(row, col), new Point(row + 2, col + 2)), row + 1, col + 1)) {
							if(isLegalJump(currPlayer, new Move(new Point(row + 2, col + 2),new Point(row + 4, col + 4)), row + 3, col + 3)) {
								if(isLegalJump(currPlayer, new Move(new Point(row + 4, col + 4),new Point(row + 6, col + 6)), row + 5, col + 5)) {
									availableMoves.add(new Move(new Point(row, col),new Point(row + 6, col + 6)));
								} else {
									availableMoves.add(new Move(new Point(row, col),new Point(row + 4, col + 4)));
								}
							} else {
								availableMoves.add(new Move(new Point(row, col),new Point(row + 2, col + 2)));
							}
						}
						if(isLegalJump(currPlayer, new Move(new Point(row, col),new Point(row - 2, col + 2)), row - 1, col + 1)) {
							if(isLegalJump(currPlayer, new Move(new Point(row - 2, col + 2),new Point(row - 4, col + 4)), row - 3, col + 3)) {
								if(isLegalJump(currPlayer, new Move(new Point(row - 4, col + 4),new Point(row - 6, col + 6)), row - 5, col + 5)) {
									availableMoves.add(new Move(new Point(row, col),new Point(row - 6, col + 6)));
								} else {
									availableMoves.add(new Move(new Point(row, col),new Point(row - 4, col + 4)));
								}
							} else {
								availableMoves.add(new Move(new Point(row, col),new Point(row - 2, col + 2)));
							}
						}
						if(isLegalJump(currPlayer, new Move(new Point(row, col),new Point(row + 2, col - 2)), row + 1, col - 1)) {
							if(isLegalJump(currPlayer, new Move(new Point(row + 2, col - 2),new Point(row + 4, col - 4)), row + 3, col - 3)) {
								if(isLegalJump(currPlayer, new Move(new Point(row + 4, col - 4), new Point(row + 6, col - 6)), row + 5, col - 5)) {
									availableMoves.add(new Move(new Point(row, col),new Point(row + 6, col - 6)));
								} else {
									availableMoves.add(new Move(new Point(row, col),new Point(row + 4, col - 4)));
								}
							} else {
								availableMoves.add(new Move(new Point(row, col),new Point(row + 2, col - 2)));
							}
						}
						if(isLegalJump(currPlayer, new Move(new Point(row, col),new Point(row - 2, col - 2)), row - 1, col - 1)) {
							if(isLegalJump(currPlayer, new Move(new Point(row - 2, col - 2),new Point(row - 4, col - 4)), row - 3, col - 3)) {
								if(isLegalJump(currPlayer, new Move(new Point(row - 4, col - 4),new Point(row - 6, col - 6)), row - 5, col - 5)) {
									availableMoves.add(new Move(new Point(row, col),new Point(row - 6, col - 6)));
								} else {
									availableMoves.add(new Move(new Point(row, col),new Point(row - 4, col - 4)));
								}
							} else {
								availableMoves.add(new Move(new Point(row, col),new Point(row - 2, col - 2)));
							}
						}
					}
				}
			}
		    
		    if(availableMoves.size() == 0) {
				for(int row = 0; row < 8; row++) {
					for(int col = 0; col < 8; col++) {

						if(this.cells[row][col] == currPlayer || this.cells[row][col] == currPlayerKing) {
							    Point from = new Point(row,col);
							    if(isLegalMove(currPlayer,new Move(from,new Point(row+1, col+1)))){
								availableMoves.add(new Move(from,new Point(row+1, col+1)));
							    }
							    if(isLegalMove(currPlayer,new Move(from,new Point(row-1, col+1)))){
								availableMoves.add(new Move(from,new Point(row-1, col+1)));
							    }
							    if(isLegalMove(currPlayer,new Move(from,new Point(row-1, col-1)))){
								availableMoves.add(new Move(from,new Point(row+1, col-1)));
							    }
							    if(isLegalMove(currPlayer,new Move(from,new Point(row-1, col-1)))){
								availableMoves.add(new Move(from,new Point(row-1, col-1)));
							    }
						}
					}
				}
			}
		    if(availableMoves.size() == 0){
		    	chgState(checkWin(currPlayer));
		    	return null;
		    }else {
			Move[] moves = new Move[availableMoves.size()];
			for(int i = 0; i < availableMoves.size(); i++) {
				moves[i] = availableMoves.get(i);
				System.out.println(i+" Possible Moves: " + moves[i].toString());
			}
			possibleMoves = moves;
			return moves;
			}
	}
	
	/**
	   * This method checks for Legal Moves
	   * @param player This is the first parameter to isLegalMove method which takes current player number
	   * @param requestedMove This will take a instance of Move
	   * @return returns true or false based on the logic 
	   */
	public boolean isLegalMove(int player, Move requestedMove) {

		if (requestedMove.to.y < 0 || requestedMove.to.x >= this.nRows || requestedMove.to.y >= this.nCols || requestedMove.to.x < 0) {
			return false;
		}

		if(this.cells[requestedMove.to.x][requestedMove.to.y] != EMPTY) {
			return false;
		} else {
			if(player == CheckerType.RED.ordinal()) {
				if(this.cells[requestedMove.from.x][requestedMove.from.y] == CheckerType.RED.ordinal() && requestedMove.to.x > requestedMove.from.x) {

					return false;
				}
				return true;
			} else {
				if (this.cells[requestedMove.from.x][requestedMove.from.y] == CheckerType.BLACK.ordinal() && requestedMove.to.x < requestedMove.from.x)
					return false;
				return true;
			}
		}
	}
}
