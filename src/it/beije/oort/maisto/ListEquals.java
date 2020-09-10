package it.beije.oort.maisto;

import java.util.*;

public class ListEquals {
	
	

	public static boolean checkList(List lis1, List lis2) {
		
		
		 Object[] cont1 = lis1.toArray();
		 Object[] cont2 = lis2.toArray();
		  
		 
		 for(int i=0 , j=0; i<cont1.length & i<cont2.length ; i++, j++) {
			 if(cont1[i] == cont2[j])
				 System.out.println(cont1[i] + "  " + cont2[j] + " equals" + "\n" );
			  else {
				 System.out.println(cont1[i] + "  " + cont2[j] + " not equals");
			return false;
		 }			 
	}
		 return true;
	}

	public static void main(String[] args) {
		
		 List <Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] {2, 3, 7, 6}));
		 List <Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] {2, 3, 5, 6}));
		 
		 checkList(list1, list2);
		

	}

}
