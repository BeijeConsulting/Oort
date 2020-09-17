package it.beije.oort.gregori.dispositivi;

public class Stampante3D extends Automatico implements SistemaSicurezza{
	
	@Override
	public void emergencyStop() {		
	}
	
	@Override
	public boolean isStopped() {
		
		return false;
	}
	
	@Override
	public String getState() {
		return "In funzione";
	}

	public static void main(String[] args) {

		Stampante3D stampante = new Stampante3D();
		System.out.println(stampante.getState());
		stampante.setMarca("HP");
		stampante.setModello("PhotoSmart");
		System.out.println("Marca: " + stampante.getMarca());
		System.out.println("Modello: " + stampante.getModello());
	}

}
