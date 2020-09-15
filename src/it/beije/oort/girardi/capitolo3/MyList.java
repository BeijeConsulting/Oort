package it.beije.oort.girardi.capitolo3;

import java.util.List;
import java.util.ArrayList;

public class MyList {
	
	public static boolean checkList(List list1, List list2) {
		if (list1.size() != list2.size())
			return false;
		else {
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i) != list2.get(i)) 
					return false;
			}
			return true;
		}
	}

	public static void main(String[] args) {		
		List list1 = new ArrayList();
		list1.add(89);
		list1.add("Carla");
		
		List list2 = new ArrayList();
		list2.add(89);
		list2.add("Carla");
		
		List list3 = list2;
		
		List list4 = new ArrayList();
		list4.add(89);
		list4.add("Carlitos");
		
		List list5 = new ArrayList();
		List list6 = new ArrayList();
		
		System.out.println(checkList(list1, list2));
		System.out.println(checkList(list2, list3));
		System.out.println(checkList(list3, list4));
		System.out.println(checkList(list6, list5));

	}

}
