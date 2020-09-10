package it.beije.oort.file.sala.first;
public class Main {
	public static void main(String[] args) {
		if(args.length!=0)	{
			System.out.println("For-each:");
			for(String s:args) {
				System.out.print(s+" ");
			}
			System.out.println();
			System.out.println("-------------");
			System.out.println("Standard For:");
			for(int i=0;i<args.length;i++) {
				System.out.print(args[i]+" ");
			}
			System.out.println();
			System.out.println("-------------");
			System.out.println("Do While:");
			int k=0;
			do {
				System.out.print(args[k]+" ");
				k++;
				} while (k<args.length);
		}
		System.out.println();
		System.out.println("-------------");
		System.out.println("While:");
		int j=0;
		while(j<args.length) {
			System.out.print(args[j]+" ");
			j++;
		}
	}
}
