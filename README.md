# GameFramework - Checkers

Design Patterns for Board Games

Development of an OO board game system. The students easily see the power, utility, flexibility and scalability of the OO Design. The lesson is that generic concepts should lead to generic applicability, not single-use, “throw away” code. The students learn “programming-in-the-large” while studying a system that is still small enough to manage.

For simplicity, we will focus on two-dimensional game boards Checkers with only two players.

It uses a 2-person game design as a vehicle to learn BIGGER concepts in computing:

- Abstraction
- Design Process
- Fundamental Principles.

The given game framework abstracts and decouples the different components in a game and specifies them in terms of interfaces with only pure abstract behaviors.
For example, the rules of a game is abstracted and encapsulated in an interface called IBoardModel. ABoardModel is a specific implementation of this interface using the state pattern. Playing a particular board game is a matter of writing a concrete subclass of ABoardModel and plug it into the framework. Nothing in the framework is changed!

Among the source files is GameModel.java which is the class that encapsulates:

- the rules of a game,
- the strategies to compute the next move, and
- the management of players.

- *GameModel* does not contain any code specific to Checkers. It merely moderates the interactions between the board, IBoardModel and the strategy to compute the next move, INextMoveStrategy. 
- *GameModel.getRequestor()* is the factory method to instantiate the IRequestor for the view. GameModel directly implements the IModelManager interface.
- GameModel.java has a method called getPlayers(). In this method, the code to add players playing the strategies.

For more info please review the following paper: https://github.com/UTDClassroom/GameFramework/blob/master/DesignPatternForGames.pdf

# Steps to run the Checkers Board

- CheckersController.java is our driver class
- By default Board will the running with 2 Human Players
- You can change the Players  in setPlayers method in CheckersView.java
- Implemented RandomMoveStrategy, you can add more strategies in GameModel.java by implementing INextMoveStrategy
- You need to select moves from the given possible moves, we are also supporting jump moves.

Initial board configuration:

![](https://github.com/UTDClassroom/cs6359003-project-cs6359003-team1/blob/master/CheckersBoardInitialPositions.PNG)

Board after some moves:

![](https://github.com/UTDClassroom/cs6359003-project-cs6359003-team1/blob/master/CheckersBoardPositions.PNG)

# Pledge of Honor
“As a Comet, I pledge honesty, integrity, and service in all that I do.”
