package main.java.checkers.team1.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import main.java.checkers.team1.RandomMoveStrategy;
import main.java.gameIO.IModel;
import main.java.model.GameModel;


public class TestRandomMoveStrategy {
	@Test
	public void testRandomMoveStrategy() {
		RandomMoveStrategy rms = new RandomMoveStrategy();
		IModel gameModel = new GameModel(8,8);
		assertNotNull(rms.getNextMove(gameModel, 1));
	}
	
	
}
