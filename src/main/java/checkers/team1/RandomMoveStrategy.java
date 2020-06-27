package main.java.checkers.team1;

import java.util.Random;

import main.java.gameIO.IModel;
import main.java.model.move.INextMoveStrategy;
import main.java.model.utility.Point;

/**
* The RandomMoveStrategy is a concrete class of INextMoveStrategy interface
*/

public class RandomMoveStrategy implements INextMoveStrategy{

	/**
	 * implementation of getNextMove
	 * @param context instance of the BoardModel
	 * @param player is the current player number
	 */
	@Override
	public Point getNextMove(IModel context, int player) {
		
		Random rand = new Random();
		int rows = context.getBoardModel().getDimension().getWidth();
		int cols = context.getBoardModel().getDimension().getHeight();
		Point p = new Point(rand.nextInt(rows), rand.nextInt(cols));
		return p;

		
	}

}
