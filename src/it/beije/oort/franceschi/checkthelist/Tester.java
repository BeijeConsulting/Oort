package it.beije.oort.franceschi.checkthelist;

import java.util.ArrayList;
import java.util.List;

public class Tester {

	public static void main(String[] args) {
		List<String> listOne = new ArrayList<>();
		List<String> listTwo = new ArrayList<>();
		List<String> listThree = new ArrayList<>();
		
		listOne.add("Doggo");
		listTwo.add("Doggo");
		listThree.add("Doggo");
		
		listOne.add("Catto");
		listTwo.add("Catto");
		listThree.add("Cat");
		
		System.out.println(CheckList.checkList(listOne, listTwo));
		System.out.println(CheckList.checkList(listOne, listThree));
	}

}
