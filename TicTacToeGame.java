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
	}
}

