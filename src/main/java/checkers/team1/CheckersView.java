package main.java.checkers.team1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.gameIO.IView;
import main.java.model.modelToView.ICommand;
import main.java.model.modelToView.IRejectCommand;
import main.java.model.modelToView.IViewManager;
import main.java.model.utility.Dimension;
import main.java.model.viewToModel.IModelManager;
import main.java.model.viewToModel.IViewRequestor;

/**
* The CheckersView will implement all the methods corresponding Checkers GUI 
*/
public class CheckersView extends JPanel implements IView,IViewManager,ICommand,MouseListener{
	
	private IModelManager iModelManager;
	private IViewRequestor iViewRequestor;
	private List<Object> players;
	private int cells[][];
	private Dimension size;
	JLabel notification;
	boolean playerRed = true;
	static Scanner input = new Scanner(System.in);
	int currRow,currCol;
	boolean gameIsHappening;
	Move[] possibleMoves; // possible moves for the given player is stored in this

	/** 
	  * Class constructor which sets parameters for creating Checkers GUI
	  */
	public CheckersView() {
		JFrame jframe = new JFrame("Checkers");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		jframe.setTitle("Checkers");
		jframe.setSize(400, 400);
		
		this.setBackground(Color.decode("#FFFFFF"));
		this.setBounds(20, 20, 244, 244);

		mainPanel.add(this);
		notification = new JLabel("It is RED player's turn");
		mainPanel.add(notification);
		
		jframe.add(mainPanel);

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		currRow = -1;
		gameIsHappening = true;
	}
	
	/**
	 * Setter for possible moves
	 * @param possibleMoves possible Moves list
	 */
	public void setPossibleMove(Move[] possibleMoves){
		this.possibleMoves = possibleMoves;
	}

	@Override
	public void setTokenAt(int row, int col, int player) {
		cells[row][col] = player;
	}

	@Override
	public void clearTokenAt(int row, int col) {
		cells[row][col] = 0;
	}

	@Override
	public void setMessage(String s) {
		System.out.println("Message"+s);
	}

	@Override
	public void draw() {
		getCommand().setMessage("It's a Draw!");
	}

	@Override
	public void win(int player) {
		getCommand().setMessage("Player won");
		this.iModelManager.reset();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICommand getCommand() {
		return this;
	}

	/**
	 * Implementation of setiViewRequestor method
	 * @param iViewRequestor instance of IViewRequestor
	 */
	@Override
	public void setiViewRequestor(IViewRequestor iViewRequestor) {
		if(playerRed){
			notification.setText("It is RED player's turn");
			playerRed = false;
		}else{
			notification.setText("It is BLACK player's turn");
			playerRed = true;
		}
		this.iViewRequestor = iViewRequestor;
		System.out.println("please enter a valid possible move number : ");
		int moveNumber = Integer.parseInt(input.next());
		int col = 0;
		this.iViewRequestor.setTokenAt(moveNumber, col, new IRejectCommand(){
			@Override
			public void execute(){
				setMessage("there is already a checker here invalid position");
			}
		});
	}
	
	public void setiModelManager(IModelManager iModelManager) {
		this.iModelManager = iModelManager;
	}

	/**
	 * Implementation of setPlayers method
	 * @param players List of Players
	 */
	@Override
	public void setPlayers(List<Object> players) {
		this.players = players;
		List<Object> l = this.iModelManager.getPlayers();
		this.iModelManager.setPlayers(l.get(0), l.get(0));
	}

	@Override
	public void setDimension(Dimension size) {
		this.size = size;
	}
	
	public void setBoard(int[][] cells) {
		this.cells = cells;
	}
	
	/**
	 * printBoard prints board
	 * 
	 */
	public void printBoard(){
		int rL = this.cells.length;
		int cL = this.cells[0].length;
		for(int i=0;i<rL;i++){
			for(int j=0;j<cL;j++){
			  System.out.print(this.cells[i][j]+" ");	
			}
			System.out.println();
		}
	}
	
	/**
	 * Draws the checker board in the panel
	 * @param g Graphics object
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.setColor(Color.black);
        g.drawRect(0,0,getSize().width-1,getSize().height-1);
        g.drawRect(1,1,getSize().width-3,getSize().height-3);
 	   
        for (int row = 0; row < this.size.width; row++) {
           for (int col = 0; col < this.size.height; col++) {
              if ( row % 2 == col % 2 )
                 g.setColor(Color.LIGHT_GRAY);
              else
                 g.setColor(Color.GRAY);
              g.fillRect(2 + col*30, 2 + row*30, 30, 30);
              switch (this.cells[row][col]) {
	              case 1:
	            	  //BLACK
	                 g.setColor(Color.BLACK);
	                 g.fillOval(4 + col*30, 4 + row*30, 25, 25);
	                 break;
	              case 2:
	                 g.setColor(Color.BLACK);
	                 g.fillOval(4 + col*30, 4 + row*30, 25, 25);
	                 g.setColor(Color.WHITE);
	                 g.drawString("K", 7 + col*30, 16 + row*30);
	                 break;
	              case 3:
	            	 //RED
	                 g.setColor(Color.RED);
	                 g.fillOval(4 + col*30, 4 + row*30, 25, 25);
	                 break;
	              case 4:
	            	  //RED KING
	                 g.setColor(Color.RED);
	                 g.fillOval(4 + col*30, 4 + row*30, 25, 25);
	                 g.setColor(Color.WHITE);
	                 g.drawString("K", 7 + col*30, 16 + row*30);
	                 break;
              }
           }
        }
        
        if(gameIsHappening) {
        	g.setColor(Color.CYAN);
        	for(int i = 0; i < possibleMoves.length; i++) {
        		g.drawRect(2 + possibleMoves[i].from.y*30, 2 + possibleMoves[i].from.x*30, 29, 29);
        		g.drawRect(3 + possibleMoves[i].from.y*30,	3 + possibleMoves[i].from.x*30, 27, 27);
        	}
        } 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int col = (e.getX() - 2) / 30;
        int row = (e.getY() - 2) / 30;
        System.out.println(row + ", " + col);
        
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
