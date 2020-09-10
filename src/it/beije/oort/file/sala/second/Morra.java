package it.beije.oort.file.sala.second;

public class Morra {

	public static void main(String[] args) {
		Morra m = new Morra();
		System.out.println(m.turno("forbice", "forbice"));
		System.out.println(m.turno("forbice", "sasso"));
		System.out.println(m.turno("forbice", "carta"));

	}
	
	public String turno(String p1, String p2) {
		/* forbice -> 0
		 * sasso -> 1
		 * carta -> 2
		 */
		int x = p1=="forbice" ? 0 : (p1=="sasso" ? 1 : 2);
		int y = p2=="forbice" ? 0 : (p2=="sasso" ? 1 : 2);
		String[][] table = {{" pareggia contro ", " perde contro ", " vince contro "},
							{" vince contro ", " pareggia contro ", " perde contro "},
							{" perde contro ", " vince contro ", " pareggia contro "}};
		return p1 + table[x][y] + p2;
	}
}
