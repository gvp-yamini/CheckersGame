package main.java.checkers.team1;

import main.java.model.GameModel;
import main.java.model.viewToModel.ITurnManager;
import main.java.model.viewToModel.IViewRequestor;

/**
* The CheckersController will bind both Model and View together
* It is a driver class
* we followed MVC architecture for this entire project.
*/
public class CheckersController {
	
	/**
	   * This method is used for binding Model with View.
	   * takeTurn is also implemented
	   */
	public void startGame(int rows,int cols){
		GameModel gameModel = new GameModel(rows,cols);
		final CheckersView view = new CheckersView();
		view.setDimension(gameModel.getBoardModel().getDimension());
		view.setBoard(gameModel.getBoardModel().getCells());
		view.setPossibleMove(((CheckersBoard)gameModel.getBoardModel()).getPossibleMoves());
		gameModel.setCommand(view.getCommand());
		gameModel.setViewAdmin(view, new ITurnManager(){
			@Override
			public void takeTurn(IViewRequestor requestor) {
				view.setPossibleMove(((CheckersBoard)gameModel.getBoardModel()).getPossibleMoves());
				view.setiViewRequestor(requestor);
				view.repaint();
			}
			
		});
		view.setiModelManager(gameModel);
		view.setPlayers(gameModel.getPlayers());
	}

	/**
	   * This is main method of this application
	   * Here we will call startGame which initialises actual board  
	   */
	public static void main(String[] args) {
		CheckersController checkers = new CheckersController();
		checkers.startGame(8,8);
	}

}
