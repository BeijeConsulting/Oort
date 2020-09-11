package it.beije.oort;

import java.util.Random;
public class Dice
{  public static void main(String[] args)
   {  Random generator = new Random();
      // getta il dado dieci volte

      for (int i = 1; i <= 10; i++)
      {  int d = 1 + generator.nextInt(6);
         System.out.print(d + " ");
      }
      System.out.println();
   }
}