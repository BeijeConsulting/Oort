package it.beije.oort.gregori.dispositivi;

public interface SistemaSicurezza {
	
	public abstract String getState();
	public abstract boolean isStopped();
	public abstract void emergencyStop();

}
