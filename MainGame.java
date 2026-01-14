package noaman.games;

import java.util.Scanner;

public class MainGame {
	
	static class Move {
		public int col; //col of the table
		public int row; //row of the table
		
		@Override
		public String toString() {
			return "Row : " + row + ", Col :" + col;
		}
	}
	
	char mainPlayer = 'x';
	char secondPlayer = 'o';
	
	boolean gameTerminated = false;
	boolean mainPlayerTurn = true;
	
	Scanner scanner = new Scanner(System.in);
	
	public char board[][] = {
		{'_','_','_'},
		{'_','_','_'},
		{'_','_','_'}
	}; //board of the game
	
	Move lastMove = new Move();
	
	public MainGame() {
		boolean Valid;
		int response, winner;
		System.out.println("Welcome to tic-tac-toc:");
		do {
			if(!areMovesLeft()) {
				winner = 0;
				this.gameTerminated = true;
				break;
			}
			displayBoard();
			if(mainPlayerTurn)
			{
				do {
					response = askForMove(this.mainPlayerTurn);
					Valid = valueChecker(response);
				}while(!Valid);
			}
			else {
				Move bestMove = findBestMove();
				lastMove = bestMove;
				bestMove.col++;
				bestMove.row++;
				System.out.println("The best move was :" + bestMove);
			}
			
			
			applyAnswer(mainPlayerTurn);
			this.mainPlayerTurn = !this.mainPlayerTurn;
			winner = winCondition();
			if (winner != 0 || !areMovesLeft()) {
			    this.gameTerminated = true;
			}
		}while(!this.gameTerminated);
		displayBoard();

		switch(winner) {
			case 10:
				System.out.println("Congratulations sir First you won");
				break;
			case -10:
				System.out.println("Congratulations sir Second you won");
				break;
			default:
				System.out.println("The game ended in a draw");
				break;
		}
	}

	public void displayBoard() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				System.out.print(" | " + this.board[i][j]);
			}
			System.out.print(" |");
			System.out.println("");
		}
	}
	
	public int askForMove(boolean mainPlayerTurn) {
		String Player = (mainPlayerTurn) ? "sir First" : "sir Second" ;
		
		System.out.println("it's the turn of " + Player + ".\nPlease choose a square");
		return scanner.nextInt();
	}
	
	public boolean valueChecker(int value) {
		int firstNumber = value / 10;
		int secondNumber = value - (firstNumber * 10);
		
		if ( firstNumber == 0 || secondNumber == 0) return false; 
		
		if(firstNumber < 4 && secondNumber < 4 && this.board[firstNumber-1][secondNumber-1] == '_') {
			this.lastMove.row = firstNumber;
			this.lastMove.col = secondNumber; 
			return true;
		}
		System.out.println("The chosen cell is either out bounds or already full.");
		return false;
	}
	
	public void applyAnswer(boolean turn) {
		this.board[this.lastMove.row-1][this.lastMove.col-1] = (turn) ? 'x' : 'o' ;
	}
	
	public int winCondition() {
		for (int i = 0; i < 3; i++) {
			if(  this.board[i][0] == this.board[i][2] && this.board[i][1] == this.board[i][2] && this.board[i][1] != '_' ){
				return (this.board[i][1] == 'x') ? 10 : -10 ;
			}
		}
		
		if(this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2] && this.board[1][1] != '_' ) {
			return (this.board[1][1] == 'x') ? 10 : -10 ;
		}
		else if(this.board[2][0] == this.board[1][1] && this.board[1][1] == this.board[0][2] && this.board[1][1] != '_') {
			return (this.board[1][1] == 'x') ? 10 : -10 ;
		}
		
		for (int i = 0; i < 3; i++) {
			if(this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board[2][i] && this.board[0][i] != '_') {
				return (this.board[1][i] == 'x') ? 10 : -10 ;
			}
		}
		
		return 0; 
	}
	
	public boolean areMovesLeft() {
		for (int i = 0; i <this.board.length; i++) {
			for (int j = 0; j <this.board.length; j++) {
				if(board[i][j] == '_') return true;
			}
		}
		return false;
	}
	
	public int minimax(int depth, boolean isMax) {
		int score = winCondition();
		
		if(score == 10) return 10-depth;
		else if (score == -10) return -10+depth;
		
		if(areMovesLeft() == false) return 0;
		
		if(isMax) {
			int best = -1000;
			
			for (int i = 0; i <this.board.length; i++) {
				for (int j = 0; j <this.board.length; j++) {
					if ( this.board[i][j] == '_') {
						board[i][j] = 'x';
						
						best = Math.max(best, minimax(depth+1, !isMax));
						
						board[i][j] = '_';
					}
				}
			}
			
			return best;
		}
		else {
			int best = 1000;
			
			for (int i = 0; i <this.board.length; i++) {
				for (int j = 0; j <this.board.length; j++) {
					if(board[i][j] == '_') {
						board[i][j] = 'o';
						
						best = Math.min(best, minimax(depth+1, !isMax));
						
						board[i][j] = '_';
					}
				}
			}
			return best;
		}
	}
	
	Move findBestMove() {
		boolean save = this.gameTerminated;
		int bestVal = 1000;
		Move bestMove = new Move();
		bestMove.row = -1;
		bestMove.col = -1;
		
		for (int i = 0; i <this.board.length; i++) {
			for (int j = 0; j <this.board.length; j++) {
				if(board[i][j] == '_') {
					
					board[i][j] = 'o';
					
					int moveVal = minimax(0 ,true);
					
					board[i][j] = '_';
					
					if(moveVal < bestVal) {
						bestMove.row = i;
						bestMove.col = j;
						bestVal = moveVal;
					}
				}
			}
		}
		
		this.gameTerminated = save;
		return bestMove;
	}
	
	public static void main(String[] args) {
		new MainGame();
	}
}
