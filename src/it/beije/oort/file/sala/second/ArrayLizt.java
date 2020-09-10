package it.beije.oort.file.sala.second;

import java.util.ArrayList;
import java.util.List;

public class ArrayLizt {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("prova123");
		System.out.println(list);
		list.add("seconda prova ababab");
		System.out.println(list);
		List<String> list2 = list;
		System.out.println(list2);
		System.out.println(list2.remove(0));
		System.out.println(list);
		System.out.println(list.isEmpty());
		System.out.println(list.size());
	}

}
