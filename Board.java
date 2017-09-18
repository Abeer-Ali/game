package tic;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Board {
	private static int MAXSIZE ;
	private int numOfTurns = 0;
	private boolean endGame = false;
	private boolean firstMove = false;
	public enum levels {
		easy, medium, hard
	}

	levels difficulty = levels.easy;
	
	String[][] board = new String[MAXSIZE][MAXSIZE];

	public void setSize(int x){
		MAXSIZE=x;
		
	}

	public Board() {
		for (int i = 0; i < MAXSIZE; i++)
			for (int j = 0; j < MAXSIZE; j++)
				board[i][j] = " ";
		endGame = false;
	}

	private void humanTurn() throws IOException {
		boolean validMove = false;
		int rowMove = 0;
		int colMove = 0;
		while (!validMove) {
			System.out.println("\nYOUR TURN\nENTER THE ROW NUMBER FOLLOWED BY THE COLUMN NUMBER");
			System.out.println("ROW NUMBER FROM 1 - " + MAXSIZE + " COLUMN NUMBER FROM 1 -  " + MAXSIZE + ".");
			System.out.println("FOR EXAMPLE: 2 3");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String s = br.readLine();
				rowMove = Integer.parseInt(s.valueOf(s.charAt(0)));
				colMove = Integer.parseInt(s.valueOf(s.charAt(s.length() - 1)));
				if ((rowMove > 0 && rowMove <= MAXSIZE) && (colMove > 0 && colMove <= MAXSIZE)
						&& board[rowMove - 1][colMove - 1] == " ") {
					board[rowMove - 1][colMove - 1] = "X";
					validMove = true;
					//keyboard.close();
				} else {
					System.out.println("SORRY, INVALID MOVE, PLEASE TRY AGAIN");
				}
			
			} catch (Exception e) {
				System.out.println("ENTER YOUR MOVE");
				System.out.println(e);
			}
		}
	}

	private boolean checkRowForWin(String checkingSymbol) {
		int countMoves = 0;
		int spaceCounter = 0;
		int leadI = 0;
		int leadJ = 0;
		for (int i = 0; i < MAXSIZE; i++) {
			countMoves = 0;
			spaceCounter = 0;
			for (int j = 0; j < MAXSIZE; j++) {
				if (board[i][j] == checkingSymbol) {
					countMoves++;
				} else if (board[i][j] == " ") {
					spaceCounter++;
					leadI = i;
					leadJ = j;
					if (spaceCounter > 1) 
					{
						break;
					}
				} else {
					break;
				}
			}
			if (countMoves == MAXSIZE - 1 && spaceCounter == 1) {
				board[leadI][leadJ] = "O";
				System.out.println("THIS TIME I CHOOSE ROW " + (leadI+1) + " COLUMN " + (leadJ+1));
				return true;
			}
		}
		return false;
	}

	private boolean colWin(String checkingSymbol) {
		int countMoves = 0;
		int spaceCounter = 0;
		int leadI = 0;
		int leadJ = 0;
		// check columns for winning move
		for (int i = 0; i < MAXSIZE; i++) {
			countMoves = 0;
			spaceCounter = 0;
			for (int j = 0; j < MAXSIZE; j++) {
				if (board[j][i] == checkingSymbol) {
					countMoves++;
				} else if (board[j][i] == " ") {
					spaceCounter++;
					leadI = i;
					leadJ = j;
					if (spaceCounter > 1) 
					{
						break;
					}
				} else
				{
					break;
				}
			}
			if (countMoves == MAXSIZE - 1 && spaceCounter == 1) {
				System.out.println("NOW I WANT TO MOVE ROW " + (leadJ+1) + " COLUMN " + (leadI+1));
				board[leadJ][leadI] = "O";
				return true;
			}
		}
		return false;
	}

	private void simple() {
		System.out.println("***************THAT IS AN EASY MOVE***********");
		boolean computerhadTurn = false;
		Random rand = new Random();
		while (!computerhadTurn) {
			int randRow = rand.nextInt(MAXSIZE);
			int randCol = rand.nextInt(MAXSIZE);
			if (board[randRow][randCol] == " ") {
				board[randRow][randCol] = "O";
				computerhadTurn = true;
				System.out.println("TH NEXT MOVE I MAKE IS ROW " + (randRow + 1) + " COLUMN " + (randCol + 1));
			}

		}
	}

	private boolean downDiagonal(String checkingSymbol)
	{
		int countMoves = 0;
		int spaceCounter = 0;
		int leadI = 0;
		int leadJ = 0;
		for (int i = 0; i < MAXSIZE; i++)
		{
			if (board[i][i] == checkingSymbol)
			{
				countMoves++;
			} else if (board[i][i] == " ")
			{
				spaceCounter++;
				leadI = i;
				leadJ = i;
				if (spaceCounter > 1)
				{
					break;
				}
			}
			else
			{
				break;
			}
		}
		if (countMoves == MAXSIZE - 1 && spaceCounter == 1) {
			board[leadI][leadJ] = "O";
			System.out.println("NOW I WANT TO MOVE ROW " + (leadI+1) + " COLUMN " + (leadJ+1));
			return true;
		}
		return false;
	}

	private boolean topDiagonal(String checkingSymbol)
	{
		int countMoves = 0;
		int spaceCounter = 0;
		int leadI = 0;
		int leadJ = 0;
		int j = 0;
		for (int i = MAXSIZE - 1; i >= 0; i--) {
			if (board[j][i] == checkingSymbol) {
				countMoves++;
			} else if (board[j][i] == " ") {
				spaceCounter++;
				leadI = i;
				leadJ = j;
				if (spaceCounter > 1) {
					break;
				}
			} else {
				break;
			}
			j++;
		}
		if (countMoves == MAXSIZE - 1 && spaceCounter == 1) {
			board[leadJ][leadI] = "O";
			System.out.println("OKAY THIS TIME ROW " + (leadJ+1) + " COLUMN " + (leadI+1));
			return true;
		}
		return false;
	}

	private boolean leading(String checkingSymbol)
	{
		boolean hadTurn = false;
		int[] countRows = new int[MAXSIZE];
		for (int i = 0; i < MAXSIZE; i++)
		{
			countRows[i] = 0;
		}
		int[] countCols = new int[MAXSIZE];
		for (int i = 0; i < MAXSIZE; i++)
		{
			countCols[i] = 0;
		}
		int downDiagonalCount = 0;
		int upDiagonalCount = 0;
		boolean fullLine = true;
		int countMoves = 0;

		for (int i = 0; i < MAXSIZE; i++) {
			countMoves = 0;
			fullLine = true;
			for (int j = 0; j < MAXSIZE; j++) {
				if (board[i][j] == checkingSymbol)
				{
					countMoves++;
				} else if (board[i][j] == "X")
				{
					countRows[i] = 0;
					fullLine = false;
					break;
				}
			}
			if (fullLine)
			{
				countRows[i] = countMoves;
			}
		}
		int maxRow = 0;
		int maxRowValue = countRows[0];
		for (int i = 1; i < countRows.length; i++)
		{
			if (countRows[i] > countRows[maxRow])
			{
				maxRow = i;
				maxRowValue = countRows[i];
			}
		}

		for (int i = 0; i < MAXSIZE; i++) {
			countMoves = 0;
			fullLine = true;
			for (int j = 0; j < MAXSIZE; j++) {
				if (board[j][i] == checkingSymbol) {
					countMoves++;
				} else if (board[j][i] == "X") {
					countCols[i] = 0;
					fullLine = false;
					break;
				}
			}
			if (fullLine)
			{
				countCols[i] = countMoves;
			}
		}
		int maxColumn = 0;
		int maxColumnValue = countCols[0];
		for (int i = 1; i < countCols.length; i++)
		{
			if (countCols[i] > countCols[maxColumn])
			{
				maxColumn = i;
				maxColumnValue = countCols[i];
			}
		}

		fullLine = true;
		for (int i = 0; i < MAXSIZE; i++)
		{
			if (board[i][i] == checkingSymbol)
			{
				countMoves++;
			} else if (board[i][i] == "X")
			{
				fullLine = false;
				downDiagonalCount = 0;
				break;
			}
		}
		if (fullLine)
		{
			downDiagonalCount = countMoves;
		}

		int j = 0;
		fullLine = true;
		countMoves = 0;
		for (int i = MAXSIZE - 1; i >= 0; i--) {

			if (board[j][i] == checkingSymbol) {
				countMoves++;
			}
			else if (board[j][i] == "X")
			{
				fullLine = false;
				upDiagonalCount = 0;
				break;
			}
			j++;
		}
		if (fullLine)
		{
			upDiagonalCount = countMoves;
		}

		if (maxRowValue == 0 && maxColumnValue == 0 && downDiagonalCount == 0 && upDiagonalCount == 0)
		{
			hadTurn = false;
		}
		else if (maxRowValue >= maxColumnValue && maxRowValue >= downDiagonalCount && maxRowValue >= upDiagonalCount)
		{
			System.out.println("SMART MOVE WITH THE ROWS");
			for (j = 0; j < MAXSIZE; j++) {
				if (board[maxRow][j] == " ") {
					board[maxRow][j] = "O";
					System.out.println("i WILL GO ROW " + (maxRow+1) + " COLUMN " + (j+1));
					hadTurn = true;
					break;
				}
			}
		}
		else if (maxColumnValue >= maxRowValue && maxColumnValue >= downDiagonalCount
				&& maxColumnValue >= upDiagonalCount) {
			System.out.println("LET IT BE COLUMNS THIS TIME");
			for (int i = 0; i < MAXSIZE; i++) {
				if (board[i][maxColumn] == " ") {
					board[i][maxColumn] = "O";
					System.out.println("WELL LETS SEE, ROW " + (i+1) + " COLUMN " + (maxColumn+1));
					hadTurn = true;
					break;
				}
			}
		} else if (downDiagonalCount >= maxRowValue && downDiagonalCount >= maxColumnValue
				&& downDiagonalCount >= upDiagonalCount) {
			System.out.println("GOING DOWN DIAGONAL");
			;
			for (int i = 0; i < MAXSIZE; i++) {
				if (board[i][i] == " ") {
					board[i][i] = "O";
					System.out.println("ROW " + (i+1) + " COLUMN " + (i+1));
					hadTurn = true;
					break;
				}
			}
		} else if (upDiagonalCount >= maxRowValue && upDiagonalCount >= maxColumnValue
				&& upDiagonalCount >= downDiagonalCount) {
			System.out.println("THE UP DIAGONAL.... INTERESTING!!!");
			j = 0;
			for (int i = MAXSIZE - 1; i >= 0; i--) {
				if (board[j][i] == " ") {
					board[j][i] = "O";
					System.out.println("ROW" + (j+1) + "," + (i+1));
					hadTurn = true;
					break;
				}
				j++;
			}
		}
		return hadTurn;
	}

	private boolean linesFree()
	{
		boolean fullLine = false;
		boolean hadTurn = false;
		Random rand = new Random();
			for (int i = 0; i < MAXSIZE; i++) {
			fullLine = true;
			for (int j = 0; j < MAXSIZE; j++) {
				if (board[i][j] != " ")
				{
					fullLine = false;
					break;
				}
			}
			if (fullLine)
			{
				int tempColumn = rand.nextInt(MAXSIZE);
				board[i][tempColumn] = "O"; 
				System.out.println("AN OPEN ROW, GREAT");
				System.out.println("FOR NOW ROW " + (i+1) + " COLUMN " + (tempColumn+1));
				hadTurn = true;
				break;
			}
                	}
            if (!hadTurn)
		{
			for (int i = 0; i < MAXSIZE; i++) {
				fullLine = true;
				for (int j = 0; j < MAXSIZE; j++) {
					if (board[j][i] != " ") {
						fullLine = false;
						break;
					}
				}
				if (fullLine) {
					int tempRow = rand.nextInt(MAXSIZE);
					board[tempRow][i] = "O";
					System.out.println("AHA, AN OPEN COLUMN");
					System.out.println("MOVING ON WITH ROW " + (tempRow+1) + " COLUMN " + (i+1));
					hadTurn = true;
					break;
				}
			}
		}
		
		if (!hadTurn)
		{
			fullLine = true;
			for (int i = 0; i < MAXSIZE; i++)
			{
				if (board[i][i] != " ")
				{
					fullLine = false;
				}
				if (fullLine)
                            {
                                int tempRowCol = rand.nextInt(MAXSIZE);
                                board[tempRowCol][tempRowCol] = "O";
                                System.out.println("THE RARE DOWN DIAGONAL!!!");
                                System.out.println("THIS SHOULD DO IT, ROW " + (tempRowCol+1) + " COLUMN " + (tempRowCol+1));
                                hadTurn = true;
				}
			}
		}

	if (!hadTurn)
		{
			int j = 0;
			fullLine = true;
			for (int i = MAXSIZE - 1; i >= 0; i--) {
                            if (board[j][i] != " ") {
                            fullLine = false;
                            break;
				}
				j++;
			}
			if (fullLine)
			{
			board[0][MAXSIZE] = "O";
                        System.out.println("Found up diagonal open");
                        System.out.println("ROW " + (0) + " COLUMN " + (MAXSIZE));
                        hadTurn = true;
			}
		}
		return hadTurn;
	}

	private boolean mediumMove(String checkingSymbol) 
	{
		boolean hadTurn = false;;
		hadTurn = checkRowForWin(checkingSymbol);
		if (!hadTurn)
		{
                    hadTurn = colWin(checkingSymbol);
		}
		if (!hadTurn)
		{
                    hadTurn = downDiagonal(checkingSymbol);
		}
		if (!hadTurn)
		{
                    hadTurn = topDiagonal(checkingSymbol);
		}
		return hadTurn;
	}

	private boolean hardMove()
	{
		boolean hadTurn = false;
		hadTurn = mediumMove("O");
		if (!hadTurn)
		{
                hadTurn = mediumMove("X");
                if (hadTurn)
                {
                System.out.println("CHANLLENGE ACCEPTED!!");
			}
		}
		if (!hadTurn)
		{
                hadTurn = leading("O");
		}
		if (!hadTurn)
		{
                hadTurn = linesFree();
		}
		if (!hadTurn)
		{
                hadTurn = leading("X");
                System.out.println("I THINK ITS GONNA BE A DRAW MATCH");
		}
		return hadTurn;
	}

	private void machinesMove(levels difficulty) {
		boolean hadTurn = false;
		if (difficulty == levels.easy) {
			simple();
		}
		else if (difficulty == levels.medium)
		{
			hadTurn = mediumMove("O");
			if (!hadTurn)
			{
				simple();
			}
		} else if (difficulty == levels.hard)
		{
			hadTurn = hardMove();
			if (!hadTurn)
			{
				simple();
			}
		}
	}

	public void playGame(int theDifficulty, int gameNumber) throws IOException {
		endGame = false;
		int firstTurn =gameNumber % 2;
		if (firstTurn == 1) {
			firstMove = true;
		} else {
			firstMove = false;
		}
		if (theDifficulty == 1) {
			difficulty = levels.easy;
		} else if (theDifficulty == 2) {
			difficulty = levels.medium;
		} else if (theDifficulty == 3) {
			difficulty = levels.hard;
		}

		while (!endGame) {
			if (firstMove) {
				humanTurn();
				displayGame();
				gameWinner();
				if (!endGame) {
					machinesMove(difficulty);
					displayGame();
					gameWinner();
				}
			} else {
				machinesMove(difficulty);
				displayGame();
				gameWinner();
				if (!endGame) {
					
					humanTurn();
					displayGame();
					gameWinner();
				}
			}
		}
	}

	private void displayWin(char winningSymbol) {
		if (winningSymbol == 'X') {
			System.out.println("Congratulations!!!!\n YOU WIN");
		} else {
			System.out.println("The computer wins!\n");
		}
	}

	private void gameWinner() {
		numOfTurns++;
		if (numOfTurns == MAXSIZE * MAXSIZE & numOfTurns != 1)
		{
			endGame = true;
			System.out.println("THE MATH IS A DRAW");
			return;
		}
		String rowMovetr = "";
		String colStr = "";
		String diagStr = "";
		String diagStrB = "";
		boolean winner = false;

		for (int i = 0; i < MAXSIZE; i++) {

			for (int j = 0; j < MAXSIZE; j++) {
				rowMovetr += board[i][j];
			}

			if (rowMovetr.contains(" ") || (rowMovetr.contains("X") && rowMovetr.contains("O"))) {
				winner = false;
			} else {
				winner = true;
				displayWin(rowMovetr.charAt(0));
				endGame = true;
				break;
			}
			rowMovetr = "";
		}
		for (int i = 0; i < MAXSIZE; i++) {
			for (int j = 0; j < MAXSIZE; j++) {
				colStr += board[j][i];
			}

			if (colStr.contains(" ") || (colStr.contains("X") && colStr.contains("O"))) {
				winner = false;
			}
                        else {
				winner = true;
				displayWin(colStr.charAt(0));
				endGame = true;
				break;
			}

			colStr = "";
		}

		for (int i = 0; i < MAXSIZE; i++) {

			diagStr += board[i][i];

		}

		if (diagStr.contains(" ") || (diagStr.contains("X") && diagStr.contains("O"))) {
			winner = false;
		} else {
			winner = true;
			displayWin(diagStr.charAt(0));
			endGame = true;
		}
		int j = 0;
		for (int i = MAXSIZE - 1; i >= 0; i--) {
			diagStrB += board[j][i];
			j++;

		}

		if (diagStrB.contains(" ") || (diagStrB.contains("X") && diagStrB.contains("O"))) {
			winner = false;
		} else {
			winner = true;
			displayWin(diagStrB.charAt(0));
			endGame = true;
		}
		
		
	
}

	public void displayGame() {
		System.out.print("  ");
		for (int i = 1; i <= MAXSIZE; i++) {
		System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < MAXSIZE; i++) {
			for (int j = 0; j < MAXSIZE; j++) {
				if (j == 0 && i == 0) {
				System.out.print(" ");
				for (int k = 0; k < MAXSIZE; k++) {
				System.out.print("+-");
				}
				System.out.println("+");
				}
                                if (j == 0) {
				System.out.print(i + 1);
				}
				System.out.print("|" + board[i][j]);

			}
			System.out.println("|");
			System.out.print(" ");
			for (int k = 0; k < MAXSIZE; k++) {
			System.out.print("+-");
			}
			System.out.println("+");
		}
	}
}
