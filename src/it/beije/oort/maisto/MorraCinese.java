package it.beije.oort.maisto;

import java.util.Scanner;

public class MorraCinese {

  public static void main(String[] args) {
    
    System.out.println("Per giocare a morra cinese scegliete tra: sasso, carta, forbice");
    Scanner tastiera = new Scanner(System.in);
    System.out.print("Scelta del primo giocatore: ");
    String firstPlayer = tastiera.next();
    System.out.print("Scelta del secondo giocatore: ");
    String secondPlayer = tastiera.next();
    
    if (firstPlayer.equals(secondPlayer))
      System.out.println("Pareggio!");
    else if (firstPlayer.equals("sasso") && secondPlayer.equals("forbice")
             || firstPlayer.equals("carta") && secondPlayer.equals("sasso")
             || firstPlayer.equals("forbice") && secondPlayer.equals("carta"))
      System.out.println("Ha vinto il primo giocatore!");
    else
      System.out.println("Ha vinto il secondo giocatore!");


  }
}  