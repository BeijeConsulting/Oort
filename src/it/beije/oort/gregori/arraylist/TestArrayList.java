package it.beije.oort.gregori.arraylist;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 1. Create a new array list, add some colors (String) and print out the collection.
 * 2. Iterate through all elements in an array list.
 * 3. Insert an element into the array list at the first position.
 * 4. Retrieve an element (at a specified index) from a given array list.
 * 5. Update a specific array element by given element. 
 * 6. Remove the third element from an array list.
 * 7. Search an element in an array list.
 * 8. Sort a given array list. 
 * 9. Shuffle elements in an array list.
 * 10. Reverse elements in an array list. 
 * 11. Swap two elements in an array list.
 * 12. Extract a portion of an array list.
 * 13. Replace the second element of an ArrayList with the specified element.
 * 14. Empty an array list. 
 * 15. Test an array list is empty or not.
 * 16. Copy one array list into another.
 * 17. Compare two array lists.
 * 18. Trim the capacity of an array list the current list size.
 * 19. Increase the size of an array list.
 * 20. Clone an array list to another array list.
 * 21. Join two array lists. 
 * 22. Print all the elements of an ArrayList using the position of the elements.
 * 
 * @author Luca Gregori
 *
 */
public class TestArrayList {

	public static void main(String[] args) {
		// Esercizio 1
		System.out.println("Esercizio 1");
		ArrayList<String> ar1 = new ArrayList<>();
		ar1.add("red");
		ar1.add("green");
		ar1.add("blue");
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 2
		System.out.println("Esercizio 2");
		for(String str : ar1) {
			System.out.println(str);
		}
		System.out.println();
		
		// Esercizio 3
		System.out.println("Esercizio 3");
		ar1.add(0, "yellow");
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 4
		System.out.println("Esercizio 4");
		System.out.println(ar1.get(1));
		System.out.println();
		
		// Esercizio 5
		System.out.println("Esercizio 5");
		System.out.println(ar1.set(0, "orange"));
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 6
		System.out.println("Esercizio 6");
		System.out.println(ar1.remove(2));
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 7
		System.out.println("Esercizio 7");
		System.out.println(ar1.contains("red"));
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 8
		System.out.println("Esercizio 8");
		Collections.sort(ar1);
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 9
		System.out.println("Esercizio 9");
		System.out.println(ar1);
		Collections.shuffle(ar1);
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 10
		System.out.println("Esercizio 10");
		ArrayList<String> ar2 = new ArrayList<>(ar1);
		System.out.println(ar1);
		for(int i = 0; i < ar1.size(); i++) {
			ar1.set(i, ar2.get(ar1.size() - i - 1));
		}
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 11
		System.out.println("Esercizio 11");
		System.out.println(ar1);
		String helper = ar1.get(0);
		ar1.set(0, ar1.get(1));
		ar1.set(1, helper);
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 12
		System.out.println("Esercizio 12");
		System.out.println(ar1);
		ArrayList<String> ar3 = new ArrayList<>(ar1.subList(0, 1));
		System.out.println(ar1);
		System.out.println(ar3);
		System.out.println();
		
		// Esercizio 13
		System.out.println("Esercizio 13");
		System.out.println(ar1);
		String str = "randomValue";
		ar1.set(1, str);
		System.out.println(ar1);		
		System.out.println();
		
		// Esercizio 14
		System.out.println("Esercizio 14");
		System.out.println(ar1);
		ar1.clear();
		System.out.println(ar1);
		System.out.println();
		
		// Esercizio 15
		System.out.println("Esercizio 15");
		System.out.println(ar1);
		System.out.println(ar1.isEmpty());
		System.out.println();
		
		// Esercizio 16
		System.out.println("Esercizio 16");
		System.out.println(ar1);
		ArrayList<String> ar4 = new ArrayList<>(ar1);
		System.out.println(ar4);
		System.out.println();
		
		// Esercizio 17
		System.out.println("Esercizio 17");
		System.out.println(ar1);
		System.out.println(ar4);
		System.out.println(ar1.equals(ar4));
		System.out.println();
		
		// Esercizio 18
		System.out.println("Esercizio 18");
		System.out.println(ar2);
		ar2.trimToSize();
		System.out.println(ar2);
		System.out.println();
		
		// Esercizio 19
		System.out.println("Esercizio 19");
		System.out.println(ar2);
		ar2.add("randomValue");
		System.out.println(ar2);
		System.out.println();
		
		// Esercizio 20
		System.out.println("Esercizio 20");
		System.out.println(ar2);
		ArrayList<String> ar5 = new ArrayList<>(ar2);
		System.out.println(ar5);
		System.out.println();
		
		// Esercizio 21
		System.out.println("Esercizio 21");
		System.out.println(ar2);
		ar5.addAll(ar2);
		System.out.println(ar5);
		System.out.println();
		
		// Esercizio 22
		System.out.println("Esercizio 22");
		System.out.println(ar2);
		for(int i = 0; i < ar2.size(); i++) {
			System.out.print(ar2.get(i) + " ");
		}
		System.out.println();
	}

}
