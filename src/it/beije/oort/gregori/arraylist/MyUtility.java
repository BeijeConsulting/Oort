package it.beije.oort.gregori.arraylist;

import java.util.Arrays;
import java.util.List;

/**
 * public static boolean checkList(List list1, List list2) 
 * deve tornare esattamente il risultato di list1.equals(list2)
 * 
 * @author Luca Gregori
 *
 */
public class MyUtility {

	public static boolean checkList(List list1, List list2) {
		boolean returnValue = false;
		
		if(list1.size() == list2.size()) {
			for(int i = 0; i < list1.size(); i++) {
				if(list1.get(i).equals(list2.get(i))) returnValue = true;
				else return returnValue;
			}
		}
		
		return returnValue;
	}
	
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("Ciao","Mario");
		List<String> list2 = Arrays.asList("Ciao","Mario");
		System.out.println("Equals? " + MyUtility.checkList(list1, list2));
		
		List<Integer> list3 = Arrays.asList(1, 2);
		List<Integer> list4 = Arrays.asList(1, 2);
		System.out.println("Equals? " + MyUtility.checkList(list3, list4));

	}

}
