package it.beije.oort.bm.exercises.figures;

public class WeirdFigure {
	
	public static void main(String[] args) {
		int lines = 6;
		int dim = 13;
		int low = 1;
		int high = 6;
		for(int i = 0; i<lines; i++) {
			for(int j = 0; j<dim; j++) {
				if(j<=dim/2) {
					System.out.print((j>=low && j<= high)? " " : (j + 1));
				}else {
					System.out.print((j>=low && j<= high)? " " : (dim - j));
				}
			}
			low++;
			high++;
			System.out.println();
		}
	}
}
//1      654321
//12      54321
//123      4321
//1234      321
//12345      21
//123456      1
