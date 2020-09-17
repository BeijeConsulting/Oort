package it.beije.oort.madonia.dispositivi.elettronici;

public class Printer3D extends DispositivoElettronicoIndustrialeAutomatico implements SistemaDiSicurezza {
	private String stato = "Off";
	
	public Printer3D() {
		this.start();
	}

	public String getState() {
		return stato;
	}
	
	public void start() {
		this.stato = "Working";
	}

	public void emergencyStop() {
		this.stato = "Stopped";
	}

	public boolean isStopped() {
		return stato.equals("Stopped");
	}

	public boolean haBisognoManutenzione() {
		return false;
	}

	public static void main(String[] args) {
		Printer3D printer = new Printer3D();
		System.out.println(printer.getState());
		printer.emergencyStop();
		System.out.println(printer.getState());
	}

}
