package main.java.checkers.team1;

import main.java.model.utility.Point;

public class Move {
	Point from,to;
	
	/** 
	  * Class constructor specifying from and to Points in a Board
	  */
	public Move(Point from, Point to) {
		this.from = from;
		this.to = to;
	}
	
	public String toString() {
		return "(" + this.from + ") -> (" + this.to + ")";
	}
}
