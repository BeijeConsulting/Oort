package it.beije.oort.madonia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EsercizioList {

	public static void main(String[] args) {
		List<String> sList1 = new ArrayList<String>();
		List<String> sList2 = new ArrayList<String>();
		List<Integer> intList1 = new ArrayList<Integer>();
		List<Integer> intList2 = new ArrayList<Integer>();
		List<LocalDate> dateList1 = new ArrayList<LocalDate>();
		List<LocalDate> dateList2 = new ArrayList<LocalDate>();
		
		sList1.add("Lista 1");
		sList1.add("Lista 2");
		sList2.add(new String("Lista 1"));
		sList2.add("Lista 2");
		
		intList1.add(4);
		intList1.add(8);
		intList2.add(4);
		intList2.add(8);
		
		dateList1.add(LocalDate.of(2000, 1, 1));
		dateList1.add(LocalDate.of(2000, 1, 2));
		dateList2.add(LocalDate.of(2000, 1, 1));
		dateList2.add(LocalDate.of(2000, 1, 2));
		
		System.out.println(checkList(sList1, sList2));
		System.out.println(sList1.equals(sList2)); // print di controllo
		
		System.out.println(checkList(intList1, intList2));
		System.out.println(intList1.equals(intList2));
		
		System.out.println(checkList(dateList1, dateList2));
		System.out.println(dateList1.equals(dateList2));
	}
	
	public static boolean checkList(List<?> l1, List<?> l2) {
		boolean isEqual = false;
		
		if (l1.size() != l2.size()) {
			return false;
		}
		
		if (l1.isEmpty() && l2.isEmpty()) {
			return true;
		}
		
		for(int i = 0; i < l1.size(); i++) {
			isEqual = l1.get(i).equals(l2.get(i));
			
			if (!isEqual) {
				break;
			}
		}
		
		return isEqual;
	}
}
