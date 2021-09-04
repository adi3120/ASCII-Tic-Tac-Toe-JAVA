import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static ArrayList<Integer> playPos = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPos = new ArrayList<Integer>();

	public static void main(String[] args) {
		char[][] gameboard = {
				{ ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' },
				{ ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' },
				{ ' ', '|', ' ', '|', ' ' } 
				};
		boolean gameover = false;
		while (!gameover) {
			showBoard(gameboard);
			takeInput(gameboard, "user");
			takeInput(gameboard, "comp");
			String a=checkWin(gameover);
			System.out.println(a);
			if(!a.isEmpty()) 
			{
				showBoard(gameboard);
				break;
			}
		}

	}

	public static void showBoard(char[][] board) {
		for (char[] row : board) {
			for (char ele : row) {
				System.out.print(ele);
			}
			System.out.println();
		}
	}

	public static void takeInput(char[][] board, String player) {

		int pos = 0;
		char fill = ' ';
		Scanner scn = new Scanner(System.in);
		Random rnd = new Random();
		if (player.equals("user")) {
			fill = 'X';
			System.out.println("Enter your placement");

			pos = scn.nextInt();
		} else if (player.equals("comp")) {
			fill = 'O';

			pos = rnd.nextInt(9) + 1;
		}
		boolean invalid = true;
		while (invalid)
			if (board[((pos - 1) / 3) * 2][2 * ((pos - 1) % 3)] == ' ') {
				invalid = false;
				if (player.equals("user"))
					playPos.add(pos);
				else
					cpuPos.add(pos);
				board[((pos - 1) / 3) * 2][2 * ((pos - 1) % 3)] = fill;
			} else {
				if (player.equals("user")) {
					System.out.println("Invalid Position Enter again");
					pos = scn.nextInt();
				} else if (player.equals("comp")) {
					pos = rnd.nextInt(9) + 1;
				}
			}
	}

	public static String checkWin(boolean gameover) {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List bottRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 6);
		List rightCol = Arrays.asList(7, 8, 9);
		List crossOne = Arrays.asList(1, 5, 9);
		List crossTwo = Arrays.asList(3, 5, 7);

		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(bottRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(crossOne);
		winning.add(crossTwo);

		for (List l : winning) {
			if (playPos.containsAll(l)) {
				gameover = true;
				return "Congrats You won";
			} else if (cpuPos.containsAll(l)) {
				gameover = true;
				return "Cpu Wins";
			} else if (playPos.size() + cpuPos.size() == 9) {
				gameover = true;
				return "TIE";
			}
		}
		return "";
	}

}
