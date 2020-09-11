package it.beije.oort.bassanelli.exercises.morracinese;

public class MorraCinese {

	final static int ROCK = 1;
	final static int PAPER = 2;
	final static int SCISSORS = 3;

	public static void main(String[] args) {

		MorraCinese morraCinese = new MorraCinese();
		morraCinese.result(MorraCinese.PAPER, MorraCinese.SCISSORS);
		
	}

	public void result(int playerOne, int playerTwo) {

		String playerOneName = "";
		String playerTwoName = "";

		switch (playerOne) {
		case 1:
			playerOneName = "SASSO";
			break;
		case 2:
			playerOneName = "CARTA";
			break;
		case 3:
		default:
			playerOneName = "FORBICE";
		}

		switch (playerTwo) {
		case 1:
			playerTwoName = "SASSO";
			break;
		case 2:
			playerTwoName = "CARTA";
			break;
		case 3:
		default:
			playerTwoName = "FORBICE";
		}

		System.out.println("Player ONE: " + playerOneName);
		System.out.println("Player TWO: " + playerTwoName);
		System.out.println();

		if (playerOne == playerTwo) {
			System.out.println(playerOneName + " pareggia con " + playerTwoName);
		} else if (playerOne - (playerTwo % 3) == 1) {
			System.out.println(playerOneName + " vince su " + playerTwoName);
		} else {
			System.out.println(playerOneName + " perde su " + playerTwoName);
		}
	}
}
