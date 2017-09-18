package tic;



import java.io.IOException;
import java.util.Scanner;

public class App {
	static int difficulty = 1;
	public static void main(String[] args) {
		System.out.println("SET BOARD SIZE : (POSITIVE INTEGERS FROM 1 TO N) ");
		int line1;
		Scanner scanKey1 = new Scanner(System.in);
		line1 = Integer.parseInt(scanKey1.nextLine());
		System.out.println();
		System.out.println();
		Board game1 = new Board();
		game1.setSize(line1);
		

		int gameNumber = 1;
		boolean endGame = false;
		while (!endGame)
		{
			display();
			Board game = new Board();
				//game.setSize(line1);
				game.displayGame();
			
			try {
				game.playGame(difficulty, gameNumber);

			} catch (IOException e) {
				e.printStackTrace();
			}
			boolean isValid = false;
			while (!isValid)
			{
				System.out.println("NUMBER OF GAMES COMPLETE " + gameNumber + "\n");
				System.out.println("PLAY AGAIN: 1. YES  2. NO" );
				String line;
				Scanner scanKey = new Scanner(System.in);
				line = scanKey.nextLine();
				try {
					if (Integer.parseInt(line) == 2)
					{
						endGame = true;
						scanKey.close();
					}
					else
					{
						gameNumber++;
						
					}
					isValid = true;
				}
				catch (Exception e)
				{
					System.out.println("SORRY, INVALID CHOICE");
				}
			}
		}
		System.out.println("GREAT GAME!!! COME BACK SOON TO PLAY");
	}

	public static void display(){
		System.out.println("====================================================================");
                System.out.println("\t\t\tWELCOME TO TIC TAC TOE");
                System.out.println("====================================================================");
		boolean rightMove = false;
		while (!rightMove)
		{
			try {
				String line;
				Scanner scanKey = new Scanner(System.in); //ignore this warning. System.in is closed at the end of the program
				System.out.println("CHOOSE THE LEVEL 1. Easy    2. Medium   3. Hard");
				line = scanKey.nextLine();
				difficulty = Integer.parseInt(line);
				if (difficulty == 1)
				{
					System.out.println("Easy game selected.");
				} else if (difficulty == 2)
				{
					System.out.println("Medium game selected.");
				} else if (difficulty == 3)
				{
					System.out.println("Hard game selected.");
				}
				if (difficulty == 1 || difficulty == 2 || difficulty == 3)
				{
					rightMove = true;
					//scanKey.close();
				} else
				{
					System.out.println("INVALID CHOICE, Please choose between 1 and 3.");
				}


			} catch (Exception e)
			{
				System.out.println("Please choose only from 1, 2 or 3");
			}
		}
	}

}

