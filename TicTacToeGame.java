package TicTacToe;
import java.util.*;


public class TicTacToeGame {
	static char[] board;
	static char player,computer;

	/**
	 * UseCase 1
	 * Function createBoard creates a board 
	 * 
	 */
	public static void createBoard(){
		board = new char[10] ;
		for(int index = 1; index <= 9; index++) {
			board[index] = ' ' ;
		}
	}
	/**
	 * Usecase 2
	 * Function assigns X or O to user
	 * @param input
	 * @return
	 */
	public static char inputXorO(Scanner input) {
		char player;
		System.out.println("Enter the input (X/O)");
		player = input.next().charAt(0);
		input.nextLine();
		if(!(player == 'X' || player == 'O')) {
			System.out.println("Invalid Choice.Please enter either (X/O)");
			inputXorO(input);
		}
		return player;
	}
	/**
	 * Usecase 3
	 * Function displays the board
	 * 
	 */
	public static void showBoard() {
		System.out.println(" | " + board[1] + " | " + " | " + board[2] + " | " + " | " + board[3] + " | ");
		System.out.println("--------------------");
		System.out.println(" | " + board[4] + " | " + " | " + board[5] + " | " + " | " + board[6] + " | ");
		System.out.println("--------------------");
		System.out.println(" | " + board[7] + " | " + " | " + board[8] + " | " + " | " + board[9] + " | ");	
	}
	/**
	 * Usecase 4
	 * Function helps the user to move to a particular position on the board
	 * if the position is valid and free
	 * @param input
	 * @param participant
	 */
	public static void moveToBoard(Scanner input,char participant) {
		int index;
		System.out.println("Enter the index you wish to move to:");
		index = input.nextInt();
		input.nextLine();
		if((index >=1 && index <= 9)) {
			if(board[index] != ' ') {
				board[index] = participant;
				showBoard();
			}
		}
		else {
			System.out.println("Invalid Index or Index not Free.Please enter another index");
			moveToBoard(input,participant);
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		createBoard() ;
		player = inputXorO(input);
		if(player == 'X') {
			computer = 'O';
		}
		else {
			computer = 'X';
		}
		showBoard();
	}
}

