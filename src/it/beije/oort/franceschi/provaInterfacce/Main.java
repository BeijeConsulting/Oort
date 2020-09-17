package it.beije.oort.franceschi.provaInterfacce;

public class Main {

	public static void main(String[] args) {
		// Bici
		System.out.println("Test Bicicletta");
		Bicicletta bici = new Bicicletta();
		bici.pedala();
		bici.frena();
		System.out.println("Che cos'è? " + bici.getVeicolo());
		System.out.println("Ma è stabile? " + bici.isStabile());
		bici = null;
		// Auto
		System.out.println("Test Fiat Punto");
		Automobile punto = new FiatPunto();
		punto.avviaMotore();
		punto.frena();
		punto.fermaMotore();
		System.out.println("Che cos'è? " + punto.getVeicolo());
		System.out.println("Ma è stabile? " + punto.isStabile());
		System.out.println("Ma che modello è? " + punto.getModel());
		punto = null;
		// Auto Sportiva
		System.out.println("Test Auto Sportiva");
		AutoSportiva sportiva = new AutoSportiva();
		sportiva.avviaMotore();
		sportiva.frena();
		sportiva.fermaMotore();
		System.out.println("Che cos'è? " + sportiva.getVeicolo());
		System.out.println("Ma è stabile? " + sportiva.isStabile());
		System.out.println("Ma che modello è? " + sportiva.getModel());
		sportiva.tiraGiuLaCapote();
		System.out.println("Può volare? " + sportiva.canFly);
		sportiva = null;
	}

}
