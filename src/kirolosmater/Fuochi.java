package kirolosmater;

public class Fuochi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0) {
			System.out.println("Non hai inserito nulla");
		} else {
			int x = Integer.parseInt(args[0]);
			String simbolo = "*";
			int j = 0;
			for (int i = x; i >= 0; i--) {
				if(j < x) {
					System.out.print(simbolo);
					j++;
					i = x;
				} else {
					System.out.println("");
					j = 0;
					x--;
				}
			}
		}
	}
}
