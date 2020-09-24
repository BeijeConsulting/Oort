package it.beije.oort.kirolosmater;

public class MorraCinese {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String giocatore1 = "carta";
		String giocatore2 = "sasso";
		String[] giocate = {"carta", "forbice", "sasso"};
		for (int i = 0; i < giocate.length; i++) {
			if (giocatore1.equals(giocate[i])) {
				for(int j = 0; j < giocate.length; j++) {
					if (giocatore2.equals(giocate[j])){
						if (((i < j) || ((i == 2) && (j == 0))) && ((i != 0) && (j != 2))) {
							System.out.println(giocate[j] + " batte " + giocate[i]);
						}
						else {
							System.out.println(giocate[i] + " batte " + giocate[j]);
						}
					}
				}
			}
		}
		
		
		
//		switch (giocatore1) {
//		case "sasso":
//			switch(giocatore2) {
//			case "sasso":
//				System.out.println("pareggio");
//				break;
//			case "forbice":
//				System.out.println("sasso batte forbice");
//				break;
//			case "carta":
//				System.out.println("carta batte sasso");
//				break;
//			default:
//					System.out.println("I parametri inseriti non sono validi");
//					break;
//			}
//			break;
//		case "forbice":
//			switch(giocatore2) {
//			case "sasso":
//				System.out.println("sasso batte forbice");
//				break;
//			case "forbice":
//				System.out.println("pareggio");
//				break;
//			case "carta":
//				System.out.println("forbice batte carta");
//				break;
//			default:
//				System.out.println("I parametri inseriti non sono validi");
//				break;
//			}
//			break;
//		case "carta":
//			switch(giocatore2) {
//			case "sasso":
//				System.out.println("carta batte sasso");
//				break;
//			case "forbice":
//				System.out.println("forbice batte carta");
//				break;
//			case "carta":
//				System.out.println("pareggio");
//				break;
//			default:
//				System.out.println("I parametri inseriti non sono validi");
//				break;
//			}
//			break;
//		default:
//			System.out.println("I parametri inseriti non sono validi");
//		}
	}

}
