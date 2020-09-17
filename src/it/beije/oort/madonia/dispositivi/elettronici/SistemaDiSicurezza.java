package it.beije.oort.madonia.dispositivi.elettronici;

public interface SistemaDiSicurezza {
	public abstract String getState();
	public abstract void emergencyStop();
	public abstract boolean isStopped();
}
