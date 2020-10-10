package TicTacToe;
import java.util.*;


public class TicTacToeGame {
	static char[] board;
	static char player,computer;
	static int count;
	enum Chance{
		HumanPlayer,ComputerPlayer;
	}

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
	public static void moveToBoard(Scanner input,char participant,Chance chance) {
		if(chance == Chance.HumanPlayer) {
			int index;
			System.out.println("Enter the index you wish to move to:");
			index = input.nextInt();
			input.nextLine();
			if((index >=1 && index <= 9)) {
				if(isBoardIndexFree(index)) {
					makeMove(index,participant,board);
					showBoard();
					checkGameStatus(input,participant, chance);
				}
				else {
					System.out.println("Invalid Index or Index not Free.Please enter another index");
					moveToBoard(input,participant,chance);
				}
			}
		}
		else {
			int index = getWin(computer);
			if(index != 0) {
				makeMove(index,computer,board);
			}
			count++;
			checkGameStatus(input,computer,Chance.ComputerPlayer);
		}
	}
	/**Usecase 5
	 * Function checks whether the given index on board is free or not
	 * @param index
	 * @return
	 */
	public static boolean isBoardIndexFree(int index) {
		boolean free = false;
		if(board[index] == ' ') {
			free = true;
		}
		return free;
	}
	/**
	 * UseCase 5
	 * Function makes the move on the board
	 * @param index
	 * @param participant
	 * @param board
	 */
	public static void makeMove(int index,char participant,char[] board) {
		board[index] = participant;
		count++;                       //count of number of plays
	}
	/**
	 * Usecase 6
	 * Function does a toss to pick randomly who initiates the game
	 * @param input
	 */
	public static void toss(Scanner input) {
		int random = (int) Math.floor((Math.random() * 2)) + 1;
		if(random == 1) {
			System.out.println("You Start");
			moveToBoard(input,player, Chance.HumanPlayer);	
		}
		else {
			System.out.println("Computer Starts");
			moveToBoard(input, computer, Chance.ComputerPlayer);
		}
	}
	/**
	 * Usecase 7
	 * Function checks for winning condition
	 * @param board
	 * @param participant
	 * @return
	 */
	public static boolean checkWin(char[] board, char participant) {
		boolean checkWin = false;
		if(board[1] == board[2] && board[2] == board[3] && board[2] == participant ||
				board[1] == board[4] && board[4] == board[7] && board[4] == participant ||
				board[1] == board[5] && board[5] == board[9] && board[5] == participant ||
				board[2] == board[5] && board[5] == board[8] && board[5] == participant ||
				board[3] == board[6] && board[6] == board[9] && board[6] == participant ||
				board[3] == board[5] && board[5] == board[7] && board[5] == participant ||
				board[4] == board[5] && board[5] == board[6] && board[5] == participant ||
				board[7] == board[8] && board[8] == board[9] && board[8] == participant ) {
			checkWin = true;
		}
		return checkWin;
	}
	/**
	 * Usecase 7
	 * Function checks for game tie condition
	 * @return
	 */
	public static boolean checkTie() {
		boolean checkTie = false;
		if (count == 9) {
			checkTie = true;
		}
		return checkTie;
	}
	/**
	 * Usecase 7
	 * function checks for the game status win/tie/continue
	 * @param input
	 * @param participant
	 * @param chance
	 */
	public static void checkGameStatus(Scanner input, char participant, Chance chance) {
		if (checkWin(board, participant)) {
			if (participant == player) {
				System.out.println(" You Win");
			} else {
				System.out.println(" Computer Wins");
			}
		} else if (checkTie()) {
			System.out.println("Game Tied");
		} else {
			if(chance == Chance.HumanPlayer){
				moveToBoard(input,computer,Chance.ComputerPlayer);
			}
			else {
				moveToBoard(input,player,Chance.HumanPlayer);
			}
		}
	}
	/**
	 * Usecase 8
	 * Function checks whether the computer can make a winning move or not
	 * @param participant
	 * @return
	 */
	public static int getWin(char participant) {
		int index = 0;
		char[] copyBoard = board;
		for(int iteration = 1; iteration <= 9 ; iteration++) {
			if(copyBoard[index] == ' ') {
				makeMove(iteration, participant,copyBoard);
				if(checkWin(copyBoard,participant)) {
					index = iteration;
					break;
				}
			}
		}
		return index;
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
		toss(input);
	}
}

